import java.util.NoSuchElementException;

public class ArrayUnbndQueue<T> implements UnboundedQueueInterface<T>{
	protected final int DEFCAP = 100;
	protected T[] queue;
	protected int origCap; //original Capacity
	protected int numElements = 0;
	protected int front = 0;
	protected int rear;
	
	public ArrayUnbndQueue() {
		queue = (T[])new Object[DEFCAP];
		rear = DEFCAP - 1;
		origCap = DEFCAP;
	}
	
	public ArrayUnbndQueue(int origCap) {
		queue =(T[]) new Object[origCap];
		rear = origCap - 1;
		this.origCap = origCap;
	}
	
	private void enlarge() {
		//Increments the capacity of the queue by an amount
		//equal to the original capacity
		
		T[] larger = (T[])new Object[queue.length + origCap]; //create larger array
		
		//copy the contents from smaller to larger array
		int currSmaller = front;
		for(int currLarger =0; currLarger < numElements; currLarger++) {
			larger[currLarger] = queue[currSmaller];
			currSmaller =(currSmaller + 1)%queue.length;
		}
		
		//update instance variable
		queue = larger;
		front = 0;
		rear = numElements - 1;
	}
	
	public void enqueue (T element) { //add elements
		if(numElements == queue.length) {
			enlarge();
		}
		rear =(rear + 1) % queue.length;
		queue[rear] = element;
		numElements = numElements + 1;
	}
	
	public T dequeue() throws QueueUnderFlowException {//removes and throw exception if empty
		if(isEmpty()) {
			throw new QueueUnderFlowException();
		}
		else {
			T toReturn = queue[front];
			queue[front] = null;
			front =(front +1)%queue.length;
			numElements = numElements - 1;
			return toReturn;
		}
	}
	
	public boolean isEmpty() {
		return(numElements == 0);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public T peek() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UnboundedQueueInterface<T> reversed() {
		// TODO Auto-generated method stub
		return null;
	}
}
