package ie.gmit.sw.Drivers;

import java.net.*;
import java.util.Date;
import java.io.Serializable;

public abstract class RequestDriver implements Serializable, Runnable{
	//private static variable
	/**
	 * The serialVersionUID is a universal version identifier
	 * for a Serializable class. Deserialization uses this number
	 * to ensure that a loaded class corresponds exactly
	 * to a serialized object. If no match is found,
	 * then an InvalidClassException is thrown.
	 */
	private static final long serialVersionUID = 1L;
	//private variables
	private Date date;
	private Socket socket;
	private String username;
	private String query;
	private String clientIP;
	private String hostAddress;
	private int portNumber;
	
	public RequestDriver(String clientIP){
		date = new Date();
		this.clientIP = clientIP;
	}
	
	public Date getDate(){
		return date;
	}
	
	public void setDate(Date date){
		this.date = date;
	}
	
	public Socket getSocketNumber(){
		return socket;
	}
	
	public void setSocketNumber(Socket socketNumber){
		this.socket = socketNumber;
	}
	
	public String getUsername(){
		return username;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public String getQuery(){
		return query;
	}
	
	public void setQuery(String query){
		this.query = query;
	}
	
	public String getClientIpAddress(){
		return clientIP;
	}
	
	public void setClientIpAddress(String clientIpAddress){
		this.clientIP = clientIpAddress;
	}
	
	public String getHostAddress(){
		return hostAddress;
	}
	
	public void setHostAddress(String hostAddress){
		this.hostAddress = hostAddress;
	}
	
	public int getPortNumber(){
		return portNumber;
	}
	
	public void setPortNumber(int portNumber){
		this.portNumber = portNumber;
	}
}