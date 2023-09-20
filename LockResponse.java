package lockncharge;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;

import java.util.concurrent.Callable;

public class LockResponse implements Callable<String> {
	
	
	private BufferedReader br; //reads line of string from the response sent by the server
	private String reader; //stores server response 
	private URLConnection conn; //connection resources

	/***
	 * LockResponse construstor - Initialize the URLConnection, String and BufferedReader instance variables.
	 * @param input is the URL connection to the API server where the response from/ 
	 */
	public LockResponse(URLConnection input)//Constructor
	{
		conn = input; //URLconnection pass through arguments
		br = null;
		reader = "";
	}
	
	/***
	 * Call method - Implements the call method from the Callable interface. This method gets the input stream
	 * from the response, reads it and stores the reference in the variable named reader.  
	 * return reader the variable that has the complete response string sent by the server.
	 */
	public String call() throws Exception {
		
		InputStream in = conn.getInputStream(); //Gets response from the server as an InpuStream
		String str = "";
		br = new BufferedReader(new InputStreamReader(in));
		while((str= br.readLine()) != null) 
		{
			reader += str;
		}
		return reader;
	}
	
	public URLConnection getConn()
	{
		return conn;
	}
	
	public String getReader()
	{
		return reader;
	}

}
