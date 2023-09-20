package lockncharge;

import java.util.Date;
import java.util.Locale;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class FormatToken {
	
	private String access_token;
	private String expiration;
	private String svr_Response;
	
	
	/***
	 * FormatToken constructor - 
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
	 * formatAccess method - 
	 * @param parm
	 * @return
	 */
	public String formatAccess(String parm)
	{
		String[] str = parm.split(":");	
		String noQuotes = str[1].substring(1, (str[1].length()-1));
		return noQuotes;
	}
	
	/***
	 * formatExpiration method -
	 * @param parm
	 * @return
	 */
	public String formatExpiration(String parm)
	{
		String[] str = parm.split(":");
		String str2 = str[1].substring(0, 10);
		return str2;
	}
	
	/***
	 * getExpirationTime method -
	 * @return
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
