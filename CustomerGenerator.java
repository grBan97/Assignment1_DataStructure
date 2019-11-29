import java.util.Random;

public class CustomerGenerator {
	protected int minIAT; 
	protected int maxIAT;
	protected int minServiceTime;
	protected int maxServiceTime;
	
	protected int currentTime = 0;
	
	Random rd = new Random();
	
	public CustomerGenerator(int minIAT2, int maxIAT2, int minST, int maxST) {
		this.minIAT = minIAT;
		this.maxIAT = maxIAT;
		minServiceTime = minST;
		maxServiceTime = maxST;
	}
	
	public Customer nextCustomer() {
		int IAT; //for next IAT
		int serviceTime; //for next ServiceTime
		
		IAT= minIAT + rd.nextInt(maxIAT - minIAT+1);
		serviceTime = minServiceTime + rd.nextInt(maxServiceTime - minServiceTime +1);
		currentTime = currentTime + IAT;
		
		Customer next = new Customer(currentTime, serviceTime);
		return next;
	}
}
