package hq.lib.struct;

import java.util.Enumeration;
import java.util.Vector;

import hq.lib.event.QueueEvent;
import hq.lib.listener.QueueListener;
import hq.lib.model.QueueNode;


public class LinkedQueue <T>{
	/**
	 * 队头指针
	 */
	private QueueNode<T> front = null;
	
	/**
	 * 队尾指针
	 */
	private QueueNode<T> tail = null;
	
	/**
	 * 队列满事件容器
	 */
	private Vector<QueueListener> topfulEventRepository = null;
	
	/**
	 * 队列空事件容器
	 */
	private Vector<QueueListener> emptyEventRepository = null;
	
	/**
	 * 队列当前已有元素的数量
	 */
	private long length = 0;
	
	/**
	 * 队列的最大容量
	 */
	private long MAX_SIZE;
	
	/**
	 * 是否会触发队满容器
	 * 指定了队列的长度，则会触发，否则不会
	 */
	private final boolean isTrigger;
	
	/**
	 * The constructor without parameter
	 */
	public LinkedQueue() {
		this.init();
		
		this.isTrigger = false;
	}
	
	/**
	 * The constructor with point a max size.<br>
	 * Integer type number.
	 * @param maxSize The max size
	 */
	public LinkedQueue(int maxSize) {
		this.init();
		
		this.MAX_SIZE = maxSize;
		
		this.isTrigger = true;
	}
	
	/**
	 * The constructor with point a max size.<br>
	 * Long type number.
	 * @param maxSize The max size
	 */
	public LinkedQueue(long maxSize) {
		this.init();
		
		this.MAX_SIZE = maxSize;
		
		this.isTrigger = true;
	}
	
	/**
	 * Initialize
	 */
	private void init(){
		//1.	初始化队头尾部指针
		front = new QueueNode<T>();
		tail = new QueueNode<T>();
		
		//2.	初始化事件容器
		this.topfulEventRepository = new Vector<QueueListener>();
		this.emptyEventRepository = new Vector<QueueListener>();
	}
	
	/**
	 * Calculate the queue's length
	 * @return {long}
	 * The queue's length
	 */
	public long size(){
		return this.length;
	}
	
	/**
	 * Judge the queue is empty or not
	 * @return {boolean}
	 * The queue is null, return true, or return false.
	 */
	public boolean isEmpty(){
		return this.length <= 0;
	}
	
	/**
	 * Judge the queue is full or not
	 * @return {boolean}
	 * The queue is full, return true, or return false.
	 */
	public boolean isFull(){
		return this.length == this.MAX_SIZE;
	}
	
	
	/**
	 * A queue node add to the queue.<br>
	 * Added at the tail.
	 * @param node The being added node.
	 * @return {boolean}
	 * Added okay or not.
	 */
	public boolean enqueue(QueueNode<T> node){
		boolean enqueueOkay = false;
		//IF not full, enqueue
		if(!this.isFull()){
			if(this.isEmpty()){
				tail.next = node;
				front.next = node;
			}else{
				tail.next.next = node;
				tail.next = node;
			}
			this.length++;
			enqueueOkay = true;
		}
		//After added, if is full, trigger the queue full event.
		if(this.isTrigger && this.isFull()){
			this.notifyTopful(new QueueEvent(this));
		}
		return enqueueOkay;
	}
	
	/**
	 * The last queue note out of the queue.
	 * Dequeue at the front.
	 * @return {QueueNode}
	 * The dequeued node.
	 */
	public QueueNode<T> dequeue(){
		QueueNode<T> node = null;
		if(!this.isEmpty()){
			node = front.next;
			front.next = node.next;
			this.length--;
		}
		if(this.isEmpty()){
			this.notifyEmpty(new QueueEvent(this));
		}
		return node;
	}
	
	
	
	
	/**
	 * Register the queue full event listener.
	 * @param myListener The event listener.
	 */
	public void addQueFullListener(QueueListener myListener){
		topfulEventRepository.addElement(myListener);
	}
	
	/**
	 * Register the queue null event listener
	 * @param myListener The event listener.
	 */
	public void addQueNullListener(QueueListener myListener){
		emptyEventRepository.addElement(myListener);
	}
	
	
	/**
	 * Trigger the queue full event
	 * @param event The source of the event
	 */
	private void notifyTopful(QueueEvent event) {
	     Enumeration<QueueListener> enm = topfulEventRepository.elements();//这步要注意同步问题
	     while(enm.hasMoreElements()){
	    	 QueueListener listener = enm.nextElement();
	    	 listener.topful(event);
	     }
	}
	
	/**
	 * Trigger the queue null event
	 * @param event The source of the event
	 */
	private void notifyEmpty(QueueEvent event){
		Enumeration<QueueListener> enm = emptyEventRepository.elements();//这步要注意同步问题
	     while(enm.hasMoreElements()){
	    	 QueueListener listener = enm.nextElement();
	    	 listener.empty(event);
	     }
	}
}
