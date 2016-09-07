package hq.lib.listener;

import hq.lib.event.QueueEvent;

import java.util.EventListener;

/**
 * For listener the queue's change.<br>
 * If the queue is null, will trigger the queNull event.<br>
 * If the queue is full, will trigger the queFull event.
 * @author Danny.Huo
 * @version 1.0
 *
 */
public interface QueueListener extends EventListener{

	/**
	 * The queue is full, trigger this event.
	 * @param me The source of the event.
	 */
	void topful(QueueEvent me);
	
	/**
	 * The queue is null, trigger this event.
	 * @param me The source of the event.
	 */
	void empty(QueueEvent me);
}
