import java.util.*;
public class Dispatcher {
	
	public ArrayList<Process> allProcesses;
	public ArrayList<Process> readyQueue;
	public ArrayList<Process> blocked;
	public Process running;
	
	public Dispatcher(ArrayList<Process> ALLP){
		allProcesses=ALLP;
	}
	public void status(){
		System.out.println(running);
		System.out.println(readyQueue);
		System.out.println(blocked);
		
	}
	
	

}
