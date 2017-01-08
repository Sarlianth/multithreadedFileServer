package ie.gmit.sw.Server;

public class ServerRunner{
	public static void main(String[] args) {
		//variables
		int port;
		String filePath;
		
		//assign user input to appropriate variables
		try{
			//Displaying console message providing instructions for user
			System.out.println("\nPlease provide:" 
					+ "\nPort Number[Integer] and File Path[String]");
			port = Integer.parseInt(args[0]);
			filePath = args[1];
		}//try
		
		catch(Exception e) {
			//instructions
			System.out.println("\nSystem couldn't process data"
					+ "\nPlease provide:" 
					+ "\nPort Number[Integer] and File Path[String]");
			return;
		}//catch
		
		/**
		 * Calling upon a FileServer Class &
		 * feeding it with user input 
		 */
		new FileServer(port, filePath);
	}//main
}//serverRunner Class