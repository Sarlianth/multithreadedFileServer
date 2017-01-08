package ie.gmit.sw.Client;

public class UserInterface{
	//variables
	boolean running = true;
	
	public UserInterface(){
	}//interface
	
	public boolean isRunning(){
		return running;
	}//isRunning
	
	public void userMenu(){
		//user menu
		System.out.println("\nSelect the option [1-4] to continue");
		System.out.println("\t[1] Connect to the Server");
		System.out.println("\t[2] List the files");
		System.out.println("\t[3] Download the file");
		System.out.println("\t[4] Quit");
	}//userMenu
	
	public void quit(boolean confirmation){
		this.running = confirmation;
	}//quit
}//UserInterface Class