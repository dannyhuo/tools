package hq.lib.event;

import java.util.EventObject;

/**
 * The event of the queue
 * @author Danny.Huo
 *
 */
public class QueueEvent extends EventObject{

	private static final long serialVersionUID = 1L;
	
	public QueueEvent(Object obj){
		super(obj);
	}
}
