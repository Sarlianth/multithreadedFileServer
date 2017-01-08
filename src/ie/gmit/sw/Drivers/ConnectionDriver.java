package ie.gmit.sw.Drivers;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class ConnectionDriver extends RequestDriver{
	//private static final variables
	/**
	 * The serialVersionUID is a universal version identifier
	 * for a Serializable class. Deserialization uses this number
	 * to ensure that a loaded class corresponds exactly
	 * to a serialized object. If no match is found,
	 * then an InvalidClassException is thrown.
	 */
	private static final long serialVersionUID = 1L;
	private static final String queryString = "Connection";
	
	public ConnectionDriver(String clientIP) {
		super(clientIP);
	}// End of ConnectionDriver Constructor

	@Override
	public void run() {
		try{
			//local variables
			String message = "Connection successful";
			ObjectOutputStream stream = new ObjectOutputStream(super.getSocketNumber().getOutputStream());
			//stream
			stream.writeObject(message);
			//flushing buffers
			stream.flush();
			stream.close();
		}//try
		
		catch(IOException e){
			e.printStackTrace();
		}//catch
	}//run
	
	@Override
	public String toString(){
		return "<" + queryString + "> " + " | Requester: " + super.getClientIpAddress() + " | Date: " + super.getDate().toString() + " |";
	}//toString
}//RequestDriver Class