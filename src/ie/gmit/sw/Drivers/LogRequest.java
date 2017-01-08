package ie.gmit.sw.Drivers;

import java.io.*;
import java.util.concurrent.BlockingQueue;

public class LogRequest implements Runnable{
	//private variables
	private BlockingQueue<RequestDriver> queue;
	private FileWriter out;
	private boolean running = true;
	
	public LogRequest(BlockingQueue<RequestDriver> queue) throws IOException {
		this.queue = queue;
		out = new FileWriter(new File("log.txt"));
	}//LogRequest Constructor
	
	@Override
	public void run(){
		//keep the log of every request
		while(running){
			try{
				RequestDriver request = queue.take();
				System.out.println(request.toString());
				out.write(request.toString() + "\n");
			}//try
			
			catch (InterruptedException e) {
				e.printStackTrace();
			}//catch
			
			catch (IOException e) {
				e.printStackTrace();
			}//catch
		}//while
	}//run	
}//logRequest Class