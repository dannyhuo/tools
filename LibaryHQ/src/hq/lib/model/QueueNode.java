package hq.lib.model;

/**
 * An item of the queue.
 * @author Danny.Huo
 * @version 1.0
 * @param <T> The type of the data
 */
public class QueueNode <T> {
	
	/**
	 * Point the next node.<br>
	 * The last node's next is null.
	 */
	public QueueNode<T> next;
	
	/**
	 * The data of this node
	 */
	public T data;
	
	/**
	 * The no parameter constructor
	 */
	public QueueNode() {
		this.data = null;
		this.next = null;
	}
	
	/**
	 * The constructor include one parameter
	 * @param data The value of the property data
	 */
	public QueueNode(T data) {
		this.data = data;
		this.next = null;
	}

}
