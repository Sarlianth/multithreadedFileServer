package ie.gmit.sw.Server;

import java.io.*;
import java.net.*;
import ie.gmit.sw.Drivers.*;
import java.util.concurrent.*;

public class FileServer{
	//static constant
	private static final int SIZE = 6;
	//private variables
	private ServerSocket socket;
	private int port;
	private String filePath;
	/**
	 * The boolean value keepRunning is used to control the while loop
	 * in the inner class called Listener. The volatile keyword tells  
	 * the JVM not to cache the value of keepRunning during Optimization,
	 * but to check it's value in memory before using it.
	 */
	private volatile boolean running = true;
	//blocking queue
	BlockingQueue<RequestDriver> queue = new ArrayBlockingQueue<>(SIZE);
	
	public FileServer(int portNumber, String filePath){
		/**Try the following. If anything goes wrong,
		 * the error will be passed to the catch block
		 */	
		try{
			//acquired user input
			this.port = portNumber;
			this.filePath = filePath;
			
			//server socket listening on port 
			//previously specified by user
			socket = new ServerSocket(portNumber);
			
			//server thread
			Thread server = new Thread(new Listener(), "File server listener launching");
			//priority for thread 
			server.setPriority(Thread.MAX_PRIORITY);
			server.start();
			
			//new instance of LogRequest
			new Thread(new LogRequest(queue), "Log request").start();
			
			System.out.println("Server: started successfully, on port:" + portNumber);
		}//try
		
		catch(IOException e) {
			System.out.println("Error - " + e.getMessage());
		}//catch
	}//fileServer Constructor
	
	/* The inner class Listener is a Runnable, i.e. a job that can be given to a Thread. The job that
	 * the class has been given is to intercept incoming client requests and farm them out to other
	 * threads. Each client request is in the form of a socket and will be handled by a separate new thread.
	 * This Listener class IS-A Runnable
	 */
	private class Listener implements Runnable{
		
		public void run(){
			//variables
			int counter = 0;
			
			while(running){
				try{
					//blocking method for incoming threads,
					Socket soc = socket.accept();
					// Entry point for a request
	            	ObjectInputStream in = new ObjectInputStream(soc.getInputStream());
	            	//request Object
	                RequestDriver request = (RequestDriver)in.readObject();
	                
	                //setting of file path
	                if(request instanceof ListDriver){
	                	((ListDriver) request).setFilePath(filePath);
	                }//if
	                
	                else if (request instanceof DownloadDriver){
	                	((DownloadDriver) request).setFilePath(filePath);
	                }//else
	                
	                //request as its own thread
	                request.setSocketNumber(soc);
	                //number for the thread
	                new Thread(request, "RequestNo - " + counter).start();
	                
					queue.put(request);	
					counter++;
				}//try
				
				catch(Exception e){
					//user error
					System.out.println("Error has occured - " + e.getMessage());
				}//catch
			}//while
		}//run
	}//Listener Class
}//FileServer Class