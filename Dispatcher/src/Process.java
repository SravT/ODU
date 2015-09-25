
public class Process {
	public int processID;
	public int priority;
	public int status;
	
	public Process(int pid, int prior, int stat){
		
		processID = pid;
		priority = prior;
		status = stat;
	}
	
	public void block(){
		status = 0;
	}
	public void ready (){
		status = 1;
	}
	public void running (){
		status = 2;
	}
	public int getPriority(){
		return priority;
	}
	public int getStatus(){
		return status;
	}
	public int getPID(){
		return processID;
	}
	public void setPriority (int x){
		priority = x;
	}
}
