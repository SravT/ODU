import java.util.*;
public class Dispatcher {
	
	public ArrayList<Process> readyQueue;
	public ArrayList<Process> blocked;
	public Process running;
	
	public Dispatcher(ArrayList<Process> rq, ArrayList<Process> blk, Process run){
		readyQueue = rq;
		blocked = blk;
		running = run;
	}

}
