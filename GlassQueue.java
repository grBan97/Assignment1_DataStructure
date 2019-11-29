
public class GlassQueue extends ArrayUnbndQueue{
	
	public GlassQueue() {
		super();
	}
	
	public GlassQueue(int origCap) {
		super(origCap);
	}
	
	public int size() {
		return numElements;
	}
	
	public Object PeekFront() {
		return queue[front];
	}
	
	public Object PeekRear() {
		return queue[rear];
	}
}
