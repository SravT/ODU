
public class Process {
	public int processID;
	public int priority;
	public int status;
	
	public Process(int pid, int prior, int stat){
		
		processID = pid;
		priority = prior;
		status = 1;
	}
	
	public void block(){
		status = -1;
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
/*	public void setResource (int x){
		resource = x;
	}
	public int getResource(){
		return resource;
	}*/
	public String toStringOne(){
		String pstatus = "";
		if(getStatus() < 0)
			pstatus += "blocked";
		if(getStatus() ==1)
			pstatus+="ready";
		if(getStatus() ==2)
			pstatus+="running";
		
		
		String print = "Process with PID: "+getPID() + " is currently " + pstatus +". Its priority is: " +getPriority()+".\n";
		/*if(getStatus()<0){
			print+=" Process is waiting on resource "+getResource()+" to be released";
		}*/
		return print;
	}
	public String toString(){
		String print = "Process with PID: "+getPID();
		return print;
		
	}
	public String toStringTwo(){
		String pstatus = "";
		if(getStatus() < 0)
			pstatus += "blocked";
		if(getStatus() ==1)
			pstatus+="ready";
		if(getStatus() ==2)
			pstatus+="running";
		
		
		String print = "Process with PID: "+getPID() + " is now " + pstatus +". Its priority is: " +getPriority()+".\n";
		/*if(getStatus()<0){
			print+=" Process is waiting on resource "+getResource()+" to be released";
		}*/
		return print;
	}
}
