import java.util.Scanner;

public class SimulationApp {

	
	public static void main(String[]args) {
		int minIAT = 30;
		int maxIAT = 45;
		int minST = 120;
		int maxST = 600;
		int numQueues;
		int numCust;
		int numOfServer;
		
		String skip; //skip end of line after read an int
		String more = null; // used to stop or continue processing
		
		Scanner sc = new Scanner(System.in);
		
		Simulation sim = new Simulation(minIAT, maxIAT, minST, maxST);
		
		System.out.print("Enter number of Server :" );
		numOfServer = sc.nextInt();
		System.out.print("Enter the number of Queue : ");
		numQueues = sc.nextInt();
		System.out.print("Enter number of Customers : ");
		numCust = sc.nextInt();
			
			sim.simulate(numQueues, numCust, numOfServer);
			System.out.println();
			System.out.println("Program Completed");
			
	}
}
