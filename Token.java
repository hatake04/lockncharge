package application;
import application.Connect.Conn;

/**
 * Represents an access token to establish server to software connection.
 */
public class Token{
	
	private String token; //raw token received from the server
	private FormatToken format; //Creates a token formatter 
	
	public Token() throws Exception
	{
		//Sends a post request to the server using the client id and a client secret in order to receive a token from the server.
		token = Conn.post("https://api.lockncharge.io/v1/token","{\"client_id\": \"f76fe190-cbca-44ff-9987-386323b93446\",\"client_secret\": \"Titan69!\"}");
		format = new FormatToken(token);
		
	}
	/**
	 * getToken method - calls the getAccessToken method defined in the FormatToken class on a FormatToken instance
	 * @return the value of the token. 
	 */
	public String getToken() {return format.getAccessToken();} 
	/**
	 * getTokenExpiration - calls the getExpirationTime method defined in the FormatToken class on a FormatToken instance
	 * @return the token expiration time.
	 */
	public String getTokenExpiration() {return format.getExpirationTime();}

	public String getEpochExpiration() {return format.getExpiration();}
	
	
	
}
