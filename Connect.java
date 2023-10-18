package application;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.FutureTask;

/**
 * Connect class - Define and implements the Post and Request HTTP methods used for API connection 
 */
public final class Connect{
	
	
	public static class Conn {
		
		private static String response = ""; //response from the server is stored in this final instance variable
		
		/**
		 * Post method - This method does a Post request to the server. 
		 * @param source - The URL where the Post request is made to
		 * @param body - The message, data, or contents sent from this application to the API server
		 * @return - Returns the server's response if any in JSON format.
		 * @throws Exception
		 */
		public final static String post(String source, String body) throws Exception{ 
			
			clearResponse();
			//body contents
			byte[] jsonBody = body.getBytes(StandardCharsets.UTF_8); //Transform data into bytes and stores the result in a bytes array		
			int length = jsonBody.length; //length of the array or body
			
			
			//Establishes the POST method parameters
			URI uri = new URI(source);
			URL url = uri.toURL();
			URLConnection conn = url.openConnection();
			HttpURLConnection http = (HttpURLConnection) conn;
			http.setRequestMethod("POST");
			http.setDoOutput(true);
			http.setFixedLengthStreamingMode(length);
			http.setRequestProperty("Content-type", "application/json; charset=UTF-8");
			http.connect();
			try(OutputStream out = http.getOutputStream()){
				out.write(jsonBody);
			}
			
			
			if(http.getResponseCode() >= 200 && http.getResponseCode() < 300)
			{
				//Declares and initializes a LockResponse object where the connection is passed as an argument 
				//Creates a runnable where the application waits for the server response
				//Creates a new Thread to run this process and not block the main thread
				LockResponse input = new LockResponse(conn); 
				FutureTask<String> futureTask = new FutureTask<String>(input);
				Thread t1 = new Thread(futureTask);
				t1.start(); //starts new thread
				
				response = futureTask.get(); //Stored response from the server
				
			}
			else
			{
				System.out.println("Error");
			}
			
			return response;
		}
		
		/**
		 * Get method - This method does a GET request to the server.
		 * @param source - The URL where the GET request is made from
		 * @param tempCode - Authentication token
		 * @return - Response for the server as a String
		 * @throws Exception
		 */
		public final static String get(String source, String tempCode) throws Exception{
			
			clearResponse();
			
			//Establishes the GET method parameters
			URI uri = new URI(source);
			URL url = uri.toURL();
			URLConnection conn = url.openConnection();
			HttpURLConnection http = (HttpURLConnection) conn;
			http.setRequestMethod("GET");
			http.setRequestProperty("Authorization", "Bearer " + tempCode);
			http.setRequestProperty("Content-type", "application/json; charset=UTF-8");
			http.connect();
			
			if(http.getResponseCode() == HttpURLConnection.HTTP_OK)
			{
				//Declares and initializes a LockResponse object where the connection is passed as an argument 
				//Creates a runnable where the application waits for the server response
				//Creates a new Thread to run this process and not block the main thread
				LockResponse input = new LockResponse(conn);
				FutureTask<String> futureTask2 = new FutureTask<String>(input);
				Thread t2 = new Thread(futureTask2);
				t2.start(); //Starts a new thread
				
				response = futureTask2.get();
			}
			return response;
		}
		
		//Clear any server response stored in the instance named response before using any of the POST or GET methods
		public final static void clearResponse() { response = ""; }
		
	}

}
