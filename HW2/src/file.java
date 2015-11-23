
public class file {
	public String name;
	public String ext;
	public String content;
	
	public file(String n, String e, String c){
		name = n;
		ext = e;
		content = c;
	}
	public String toString(){
		return name +"." + ext;
	}
	public String toString2(){
		return "Contents of " + name +"." + ext + ": " + content;
	}
}
