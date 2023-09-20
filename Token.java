package lockncharge;

import lockncharge.Connect.Conn;

public class Token {
	
	private String token;
	private FormatToken format;
	
	public Token() throws Exception
	{
		token = Conn.post("https://api.lockncharge.io/v1/token","{\"client_id\": \"f76fe190-cbca-44ff-9987-386323b93446\",\"client_secret\": \"Titan69!\"}");
		format = new FormatToken(token);
	}
	
	public String getToken() {return format.getAccessToken();}
	public String getTokenExpiration() {return format.getExpirationTime();}

}
