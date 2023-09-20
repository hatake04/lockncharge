package lockncharge;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.FutureTask;

public final class Connect{
	
	public static class Conn {
		private static String response = ""; //response from the server
		
		public final static String post(String source, String body) throws Exception{ 
			
			clearResponse();
			//body contents
			byte[] jsonBody = body.getBytes(StandardCharsets.UTF_8);		
			int length = jsonBody.length;
			
			
			
			@SuppressWarnings("deprecation")
			URL url = new URL(source);
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
				LockResponse input = new LockResponse(conn);
				FutureTask<String> futureTask = new FutureTask<String>(input);
				Thread t1 = new Thread(futureTask);
				t1.start();
				
				response = futureTask.get();
				
			}
			else
			{
				System.out.println("Error");
			}
			
			return response;
		}
		
		public final static String get(String source, String tempCode) throws Exception{
			
			clearResponse();
			
			@SuppressWarnings("deprecation")
			URL url = new URL(source);
			URLConnection conn = url.openConnection();
			HttpURLConnection http = (HttpURLConnection) conn;
			http.setRequestMethod("GET");
			http.setRequestProperty("Authorization", "Bearer " + tempCode);
			http.setRequestProperty("Content-type", "application/json; charset=UTF-8");
			http.connect();
			
			if(http.getResponseCode() == http.HTTP_OK)
			{
				LockResponse input = new LockResponse(conn);
				FutureTask<String> futureTask2 = new FutureTask<String>(input);
				Thread t2 = new Thread(futureTask2);
				t2.start();
				
				response = futureTask2.get();
				
			}
			return response;
		}
		
		public final static void clearResponse() { response = ""; }
		
	}

}
