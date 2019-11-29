
public class QueueUnderFlowException extends Exception{
	public QueueUnderFlowException(){
		super("Dequeue attempted on empty queue.");
	}
}
