import java.util.NoSuchElementException;

public interface UnboundedQueueInterface<T> {
	public boolean isEmpty();
	public int size();
	public void enqueue(T element); //Stores element at front
	public T dequeue() throws NoSuchElementException, QueueUnderFlowException; // removes the front element in the queue
	public T peek() throws NoSuchElementException; // return the front element and not remove
	public UnboundedQueueInterface<T> reversed(); //return a reversed copy of the queue
}
