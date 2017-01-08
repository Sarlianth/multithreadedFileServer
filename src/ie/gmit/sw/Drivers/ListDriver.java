package ie.gmit.sw.Drivers;

import java.io.*;

public class ListDriver extends RequestDriver{
	//private static final variables
	/**
	 * The serialVersionUID is a universal version identifier
	 * for a Serializable class. Deserialization uses this number
	 * to ensure that a loaded class corresponds exactly
	 * to a serialized object. If no match is found,
	 * then an InvalidClassException is thrown.
	 */
	private static final long serialVersionUID = 1L;
	private static final String queryString = "Listing";
	//private variable
	private String filePath;
	
	public ListDriver(String clientIP){
		super(clientIP);
	}//ListDriver Constructor

	@Override
	public void run(){
		try {
			//new file path
			File fileFolder = new File(filePath);
			//arrayList to take in all files in the directory
			File[] files = fileFolder.listFiles();
		
			ObjectOutputStream output = new ObjectOutputStream(super.getSocketNumber().getOutputStream());
			output.writeObject(files);
			//flushing buffers
			output.flush();
			output.close();
		}//try
		
		catch (IOException e) {
			e.printStackTrace();
		}//catch
	}//run

	public String getFilePath(){
		return filePath;
	}//getFilePath

	public void setFilePath(String filePath){
		this.filePath = filePath;
	}//setFilePath
	
	@Override
	public String toString(){
		return "<" + queryString + "> " + " | Requester: " + super.getClientIpAddress() + " | Date: " + super.getDate().toString() + " |";
	}//toString
}//RequestDriver Class