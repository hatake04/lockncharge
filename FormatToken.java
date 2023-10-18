package application;


import java.util.Locale;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class FormatToken {
	
	private String access_token; //token received
	private String expiration; //token expiration
	private String svr_Response; //raw response from the server
	
	
	/***
	 * FormatToken constructor - Initializes the svr_Response instance variable with the server response. Additionally, it extracts the access token value and the expiration from the server response
	 * @param response
	 * @param time
	 */
	public FormatToken(String response)
	{
		svr_Response = response;
		String[] jsonArray = svr_Response.split(",");
		
		access_token = formatAccess(jsonArray[0]);
		expiration = formatExpiration(jsonArray[1]);
		
	}
	
	/**
	 * formatAccess method - Eliminates the quotes in the json format response to get the actual string of the token
	 * @param parm raw token
	 * @return value of token
	 */
	public String formatAccess(String parm)
	{
		String[] str = parm.split(":");	
		String noQuotes = str[1].substring(1, (str[1].length()-1));
		return noQuotes;
	}
	
	/***
	 * formatExpiration method - Gets the expiration value of the token from json
	 * @param parm JSON object with attribute and value pair of the token expiration
	 * @return epoch expiration time
	 */
	public String formatExpiration(String parm)
	{
		String[] str = parm.split(":");
		String str2 = str[1].substring(0, 10);
		return str2;
	}
	
	/***
	 * getExpirationTime method - Translate epoch time to month/day/year hour/minute/second format for better readability.
	 * @return expiration date and time of the token
	 */
	public String getExpirationTime()
	{
		String tenDigits = expiration.substring(0, 10);
		
		Instant time = Instant.ofEpochSecond(Integer.parseInt(tenDigits));
		ZonedDateTime timezone = time.atZone(ZoneId.of("America/New_York"));
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/uuuu HH:mm:ss", Locale.ENGLISH);	
		return dtf.format(timezone); 
	}
	
	//GETTERS
	public String getAccessToken() {return access_token;}
	public String getExpiration() {return expiration;}
	public String getServerResponse() {return svr_Response;}
	
}
