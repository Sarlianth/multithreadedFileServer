package ie.gmit.sw.Drivers;

import java.io.*;

public class DownloadDriver extends RequestDriver{
	//private static final variables
	/**
	 * The serialVersionUID is a universal version identifier
	 * for a Serializable class. Deserialization uses this number
	 * to ensure that a loaded class corresponds exactly
	 * to a serialized object. If no match is found,
	 * then an InvalidClassException is thrown.
	 */
	private static final long serialVersionUID = 1L;
	private static final String queryString = "Download";
	//private variables
	private String filePath;
	private String fileName;
	
	public DownloadDriver(String clientIP, String fileName){
		super(clientIP);
		this.fileName = fileName;
	}// End of DownloadDriver Constructor

	@Override
	public void run(){
		File file = new File(filePath + "/" + fileName);
		byte[] byteArray = new byte[(int)file.length()];
		
		try{
			//input file for loading
			FileInputStream fileInput = new FileInputStream(file);
			//input stream
			BufferedInputStream bufferFile = new BufferedInputStream(fileInput);
			//copy of the file into byteArray
			bufferFile.read(byteArray,0,byteArray.length);
			
			ObjectOutputStream output = new ObjectOutputStream(super.getSocketNumber().getOutputStream());
			output.writeObject(byteArray);
			//flushing buffers
			output.flush();
			output.close();
		}//try
		
		catch (FileNotFoundException e){
			e.printStackTrace();
		}//catch
		
		catch(IOException e){
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
}//DownloadDriver Class
