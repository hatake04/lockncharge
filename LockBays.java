package application;

import java.io.StringReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import application.Connect.Conn;

/**
 * LockBays class - Creates a Tower object with a JSON array of the bays it contains and the users ID assigned to any bay if available. 
 */
public class LockBays {
	
	private final String url = "https://api.lockncharge.io/v1/bays/"; 
	private String accessToken;
	private String tower; //Where the server response is stored
	private JSONArray bay; //All the bays the current tower has
	private String[] users; //All the users id assigned to a bay
	
	/**
	 * LockBays constructor - Initializes the instance variables accessToken with the access token received from the server. It also
	 * initiates a GET request to the server using the URL and access token as argument and storing the response in the tower. Additionally,
	 * it formats the JSON response from the server to extract the Bays and the Users ID from the bays.
	 * 
	 * @param token
	 * @throws Exception
	 */
	public LockBays(String token) throws Exception
	{
		accessToken = token;
		tower = Conn.get(url, accessToken);
		
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(new StringReader(tower));
		bay = (JSONArray) obj.get("items");
		users = storeBayUserId();
		
	}
	
	/**
	 * getBayByIndex method - It returns the JSON object of a bay based on the number specified in the argument 
	 * @param i is the number of the bay to be selected
	 * @return JSON object of the bay selected
	 */
	public String getBayByIndex(int i)
	{
		return bay.get(i).toString();
	}
	
	/**
	 * getBays method - loops through the whole JSON array and return all the bays as a String
	 * @return
	 */
	public String getBays()
	{
		String str = "";
		for(int i = 0; i < bay.size(); i++)
		{
			str += bay.get(i).toString() + "\n";
		}
		return str;
	}
	
	/**
	 * storeBayUserId method - Stores all the user ids assigned to a bay in a String array by looping through the JSON array of bays and
	 * parsing the bay object obtaining the user id value by specifying its key. If there is no user id assigned to a bay the array stored an empty string.
	 * @return an array of string where all user ids are stored.
	 */
	private String[] storeBayUserId()
	{
		String[] str = {"","","","",""}; //declares and initializes and empty String array
		JSONObject temp;
		JSONParser parser1 = new JSONParser();
		
		for(int i = 0; i < bay.size(); i++)
		{
			try {
				//Gets the bay from the bay array as a string in which is passed to a StringReader object as a character
				//stream and then parsed as a JSONObject. The value(user id) pair of the JSON object is found by its key "assignedUserId"
				//and stored in a cell of the array.
				temp = (JSONObject) parser1.parse(new StringReader(bay.get(i).toString()));
				str[i] = temp.get("assignedUserId").toString(); 
			} catch (Exception e) {
				str[i] = ""; //empty if the value of assignedUserId is null
			}
			
		}
		
		return str;
	}
	
	/**
	 * getBayUserId method - finds the user id by the bay given
	 * @return user id
	 */
	public String getBayUserId()
	{
		String str = "";
		for(int i = 0; i < bay.size(); i++)
		{
			str += users[i] + "\n";
		}
		return str;
	}
	
	/**
	 * getUsers method - Returns all the users id
	 * @return the array of string where all users id are stored
	 */
	public String[] getUsers() {return users;}
	
	public void setUsers(String[] newUsers) {users = newUsers;}
	

}
