package hq.lib.struct;

import hq.lib.event.QueueEvent;
import hq.lib.listener.QueueListener;

import java.util.Enumeration;
import java.util.Vector;

public class Queue<T> {
	/**
	 * 队列的最大容量
	 */
	private int MAX_SIZE = 0;
	
	/**
	 * 队列满事件容器
	 */
	private Vector<QueueListener> topfulEventRepository = null;
	
	/**
	 * 队列空事件容器
	 */
	private Vector<QueueListener> emptyEventRepository = null;
	
	/**
	 * 当前队列的大小
	 */
	private int length = 0;
	
	/**
	 * 存储数据的数组
	 */
	private Object[] storage = null;
	
	/**
	 * 构造函数
	 * @param maxSize
	 */
	public Queue(int maxSize){
		MAX_SIZE = maxSize;
		storage = new Object[maxSize];
		
		this.init();
	}
	
	/**
	 * Initialize
	 */
	private void init(){
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
	public boolean enqueue(T data){
		boolean enqueueOkay = false;
		//IF not full, enqueue
		if(!this.isFull()){
			this.storage[this.length] = data;
			this.length++;
			enqueueOkay = true;
		}
		//After added, if is full, trigger the queue full event.
		if(this.isFull()){
			this.notifyTopful(new QueueEvent(this));
		}
		return enqueueOkay;
	}
	
	/**
	 * The last queue note out of the queue.
	 * Dequeue at the front.
	 * @return {T}
	 * The dequeued node.
	 */
	@SuppressWarnings("unchecked")
	public T dequeue(){
		T data = null;
		if(!this.isEmpty()){
			data = (T) this.storage[this.length-1];
			this.length--;
		}
		if(this.isEmpty()){
			this.notifyEmpty(new QueueEvent(this));
		}
		return data;
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
