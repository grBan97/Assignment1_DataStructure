public class Simulation {
	final int MAXTIME = Integer.MAX_VALUE;
	
	CustomerGenerator custGen;
	float avgWaitTime = 0.0f;
	
	public Simulation(int minIAT, int maxIAT, int minST, int maxST) {
		custGen = new CustomerGenerator(minIAT,maxIAT,minST,maxST);
	}

	public float getAvgWaitTime() {
		return avgWaitTime;
	}
	
	public void simulate(int numQueues, int numCustomers, int numServers) {
		
		GlassQueue[] queues = new GlassQueue[numQueues];
		int[] timeLeftToServe = new int[numServers];
		
		Customer nextCust; //next cust generator
		Customer cust = null; //hold cust for temporary use
		
		int totalWaitTime = 0; //total wait time
		int custInCount = 0; // count of customers started
		int custOutCount = 0; //count of customers finished
		
		int nextArrTime;
		int nextDepTime;
		int nextQueue;
		
		int shortest;
		int shortestSize;
		Customer rearCust;
		int finishTime;
		
		for(int i =0; i<numQueues; i++) {
			queues[i] = new GlassQueue();
		}
		
		nextCust = custGen.nextCustomer();
		
		for(int h=0; h<numServers; h++) {
			
			while(custOutCount < numCustomers) {
				if(custInCount != numCustomers) {
					nextArrTime = nextCust.getArrivalTime();
				}
				else {
					nextArrTime = MAXTIME;
				}
			
				nextDepTime = MAXTIME;
				nextQueue = 1;
				for(int i=0; i<numQueues; i++) {
					if(queues[i].size()!=0) {
						cust =(Customer)queues[i].PeekFront();
						if(cust.getFinishTime() < nextDepTime) {
							nextDepTime = cust.getFinishTime();
							nextQueue = i;
						}
					}
				}
			
				if(nextArrTime < nextDepTime) {
				//handle customer arriving
					shortest = 0;
					shortestSize = queues[0].size();
					for(int  i= 0; i<numQueues; i++) {
						if (queues[i].size()>shortestSize) {
							shortest = i;
							shortestSize = queues[i].size();
						}
					}
				//determine finish time
					if(shortestSize ==0) {
						finishTime = nextCust.getArrivalTime()+ nextCust.getServiceTime();
					}
					else {
					rearCust =(Customer)queues[shortest].PeekRear();
					finishTime = rearCust.getFinishTime()+ nextCust.getServiceTime();
					}
				
					//set finish time and enqueue customer
					nextCust.setFinishTime(finishTime);
					queues[shortest].enqueue(nextCust);
					custInCount = custInCount +1;
				
					//get next Customer to enqueue
					
				
				if (timeLeftToServe[h] > 0) {
					timeLeftToServe[h]--;
					
				if(custInCount <numCustomers)
					nextCust = custGen.nextCustomer();
				
	                }
				}
			
				else {
				//handle customer leaving
				
					try {
						cust =(Customer)queues[nextQueue].dequeue();
					} catch (QueueUnderFlowException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					totalWaitTime = totalWaitTime + cust.getWaitTime();
					timeLeftToServe[h] = totalWaitTime;
					custOutCount = custOutCount+1;
				}
			}
		}
		
		avgWaitTime = totalWaitTime/(float)numCustomers;
		System.out.println(avgWaitTime);
		
	}
}
