package ie.gmit.sw.Client;

import java.io.*;
import java.net.*;
import java.util.*;
import ie.gmit.sw.Drivers.*;
import ie.gmit.sw.Client.Configuration.Context;

public class ClientFileService implements DrivableClient{
	//declaring private class variables
	private Socket socket;
	private String hostIP;
	private int port;
	private String downloadDir;
	private String clientIP;
	private File[] files;
	
	public ClientFileService(Context ctx){
		this.hostIP = ctx.getServerHostAddress();
		this.port = ctx.getServerPortNumber();
		this.downloadDir = ctx.getDownloadDirectory();
	}//constructor 
	
	@Override
	public void serverConnection(){
		try{
			//create a socket
			socket = new Socket(hostIP, port);
			//get the clients ip address
			clientIP = socket.getLocalAddress().getHostAddress();
			//request to the server
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			//output stream
			out.writeObject(new ConnectionDriver(clientIP));
			//flushing buffers to make sure everything has been sent
			out.flush();
			//pausing thread for a while
			Thread.yield();
			//server handling the response
			ObjectInputStream serverResponse = new ObjectInputStream(socket.getInputStream());
			//deserializing the object
	        String response = (String)serverResponse.readObject();
			
	        //informing current client with the response
	        System.out.println(response);
		}//try
		
		catch(Exception e){
			e.printStackTrace();
		}//catch
	}//connectToServer

	@Override
	public void requestFiles(){
		try {
			socket = new Socket(hostIP, port);
			clientIP = socket.getLocalAddress().getHostAddress();
			
			//request to the server
	        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
	        //straming object
	        out.writeObject(new ListDriver(clientIP));
			//flushning buffer to make sure its been sent 
	        out.flush();
	        //pausing thread for a while
	        Thread.yield();
	        
	        //handling response from server
	        ObjectInputStream serverResponse = new ObjectInputStream(socket.getInputStream());
	        //deserialising object
	        files = (File[]) serverResponse.readObject();
	        //printing array objects
	        for(int i = 0; i < files.length; i++){
	        	//printing
	        	System.out.println(files[i].getName());
	        }//for
		}//try
		
		catch(Exception e){
			e.printStackTrace();
		}//catch
	}//requestListOfFiles

	@Override
	public void requestDownloadable(){
		//local variables
		Scanner console = new Scanner(System.in);
		System.out.print("\nPlease provide file name that you want to download: ");
		//user input
		String fileName = console.nextLine();
		
		//loop trough objects
		for(int i = 0; i < files.length; i++){
			//search the name
			if(fileName.equals(files[i].getName())){
				try {
					socket = new Socket(hostIP, port);
					clientIP = socket.getLocalAddress().getHostAddress();
				
					//request to the server
			        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			        //serialised stream
			        out.writeObject(new DownloadDriver(clientIP, fileName));
					//flushing buffers
			        out.flush();
			        //pause thread for a while
			        Thread.yield();
			        
			        //handling server response 
			        ObjectInputStream serverResponse = new ObjectInputStream(socket.getInputStream());
			        //object into an array
			        byte[] byteArray = (byte[])serverResponse.readObject();
			        
			        FileOutputStream output = new FileOutputStream(downloadDir + "/" + fileName);
			        output.write(byteArray);
			        output.close();
				}//try
				
				catch(Exception e){
					e.printStackTrace();
				}//catch
			}//if
		}//for
	}//requestDownload

	@Override
	public boolean quit(){
		//local variables
		boolean confirmation = true;
		String userInput;
		Scanner console = new Scanner(System.in);
		
		do{
			//warning message
			System.out.println("\tYou sure you want to quit? [y/n]");
			userInput = console.nextLine().toLowerCase();
			if(userInput.equals("n")){
			}//if
			
			else if(userInput.equals("y")){
				System.out.println("\tApplication terminated");
				confirmation = false;
			}//else if
			
			else{
				System.out.println("\n\tPlease enter y/Y to confirm or n/N to decline!\n\tTry again");
			}//else
		}while(userInput.equals('y') || userInput.equals('n')); //do/while
		
		return confirmation;
	}//quit
}//FileServer Class
