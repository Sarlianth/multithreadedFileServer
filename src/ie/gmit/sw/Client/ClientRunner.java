package ie.gmit.sw.Client;

import java.util.Scanner;
import ie.gmit.sw.Client.Configuration.*;

public class ClientRunner{
	public static void main(String[] args) throws Throwable{
		//declaring variables
		Scanner console = new Scanner(System.in);
		int option;
		
		//configuring XML and file parser
		Context context = new Context();
		ContextParser parser = new ContextParser(context);
		parser.parse();
		
		//declaring and instantiating userInterface object
		UserInterface UI = new UserInterface();
		
		ClientFileService service = new ClientFileService(context);
		while(UI.isRunning()){
			//display the menu for user
			UI.userMenu();
			//take users input
			option = console.nextInt();
			
			//switch as an actual menu
			switch(option){
				case 1:
					service.serverConnection();
					break;
				case 2:
					service.requestFiles();
					break;
				case 3:
					service.requestDownloadable();
					break;
				case 4:
					UI.quit(service.quit());
					break;
				default:
					System.out.println("\n\tPlease try again\n");
					break;
			}//switch
		}//while loop
		//termination
		console.close();
	}//main
}//ClientRunner
