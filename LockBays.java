package lockncharge;

import java.io.StringReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import lockncharge.Connect.Conn;

public class LockBays {
	
	private final String url = "https://api.lockncharge.io/v1/bays/";
	private String accessToken;
	private String tower;
	private JSONArray bay;
	private String[] users;
	
	public LockBays(String token) throws Exception
	{
		accessToken = token;
		tower = Conn.get(url, accessToken);
		
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(new StringReader(tower));
		bay = (JSONArray) obj.get("items");
		users = storeBayUserId();
		
	}
	
	public String getBayByIndex(int i)
	{
		return bay.get(i).toString();
	}
	
	public String getBays()
	{
		String str = "";
		for(int i = 0; i < bay.size(); i++)
		{
			str += bay.get(i).toString() + "\n";
		}
		return str;
	}
	
	private String[] storeBayUserId()
	{
		String[] str = {"","","","",""};
		JSONObject temp;
		JSONParser parser1 = new JSONParser();
		
		for(int i = 0; i < bay.size(); i++)
		{
			try {
				temp = (JSONObject) parser1.parse(new StringReader(bay.get(i).toString()));
				str[i] = temp.get("assignedUserId").toString(); 
			} catch (Exception e) {
				str[i] = "";
			}
			
		}
		
		return str;
	}
	
	public String getBayUserId()
	{
		String str = "";
		for(int i = 0; i < bay.size(); i++)
		{
			str += users[i] + "\n";
		}
		return str;
	}
	
	public String[] getUsers() {return users;}
	
	public void setUsers(String[] newUsers) {users = newUsers;}
	

}
