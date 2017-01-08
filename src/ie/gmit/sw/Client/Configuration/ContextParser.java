package ie.gmit.sw.Client.Configuration;

import org.w3c.dom.*;
import javax.xml.parsers.*;

public class ContextParser implements Parser{
	//private variables
	private Context context;
	
	public ContextParser(Context context){
		super();
		this.context = context;
	}//ContextParser
	
	@Override
	public void parse() throws Throwable{
		/* These three lines are part of JAXP (Java API for XML Processing) and are designed to
		 * completely encapsulate how a DOM node tree is manufactured. The concrete classes that
		 * are doing the actual work are part of the Apache Xerces project. JAXP shields us from
		 * having to know and understand the complexity of Xerces through encapsulating the
		 * process.
		 */
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(Context.CONF_FILE);
		
		/* The type Document in the line above is an org.w3c.dom.Document. From this
		 * point on in the method, we will only use the DOM "STANDARD" to do the
		 * processing. DOM is language neutral and completely abstract. As a result,
		 * it is completely stable! The DOM standard hasn't changed much since the
		 * year 2000...! Abstractions are highly stable and can be relied upon. 
		 */
		//root of the node tree
		Element root = doc.getDocumentElement();
		//child node of the root
		NodeList children = root.getChildNodes();
		
		//"username" from root
		context.setUsername(root.getAttribute("username"));
		
		//child nodes
		for(int i = 0; i < children.getLength(); i++){
			//next child
			Node next = children.item(i);

			if (next instanceof Element){
				//cast general node to element node
				Element e = (Element) next;
				
				//element <server-host>
				if (e.getNodeName().equals("server-host")){
					context.setServerHostAddress(e.getFirstChild().getNodeValue());
				}// End of if
				
				//element <server-port>
				else if (e.getNodeName().equals("server-port")){
					context.setServerPortNumber(Integer.parseInt(e.getFirstChild().getNodeValue()));
				}//else if
				
				//element <download-dir>
				else if (e.getNodeName().equals("download-dir")){
					context.setDownloadDirectory(e.getFirstChild().getNodeValue());
				}//else if
			}//if
		}//for
	}//Parse
	
	public Context getContext() {
		return context;
	}//getConfiguration

	public void setContext(Context context) {
		this.context = context;
	}//setConfiguration
}//ContextParser Class