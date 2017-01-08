package ie.gmit.sw.Client.Configuration;

public class Context{
	public static final String CONF_FILE = "Conf.xml";
	private String username;
	private String hostIP;
	private int portIP;
	private String downloadDir;
	
	public Context(){
		super();
	}
	
	public String getUsername(){
		return username;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public String getServerHostAddress(){
		return hostIP;
	}
	
	public void setServerHostAddress(String serverHost){
		this.hostIP = serverHost;
	}
	
	public int getServerPortNumber(){
		return portIP;
	}
	
	public void setServerPortNumber(int serverPort){
		this.portIP = serverPort;
	}
	
	public String getDownloadDirectory(){
		return downloadDir;
	}
	
	public void setDownloadDirectory(String downloadDirectory){
		this.downloadDir = downloadDirectory;
	}
	
	@Override
	public String toString(){
		return "Context (Username: " + username 
				+ "| Server Host Address: " + hostIP 
				+ "| Server Port Number: " + portIP
				+ "| Download Directory: " + downloadDir + ")";
	}//toString
}//Context Class
