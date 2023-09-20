package lockncharge;

import lockncharge.Connect.Conn;

public class UserBay {
	
	private User[] users = new User[5];
	private final String url = new String("https://api.lockncharge.io/v1/station-users/");
	private String accessToken;
	private String response;
	
	public UserBay(String token, String[] usersID) throws Exception
	{
		response = "";
		accessToken = token;
		iniUserArray();
		getUser(usersID);
	}

	private String getUser(String[] usersID) throws Exception
	{
		
		for(int i = 0; i < usersID.length; i++)
		{
			if(!checkNullUsers(usersID, i))
			{
				
				String[] userJson = response.split(",");
				String userNameJson = userJson[0];
				String[] userName = userNameJson.split(":");
				String tempFullName = userName[1];
				String fullName = tempFullName.substring(2,(tempFullName.length()-1));
				users[i] = new User(fullName);
				System.out.println(users[i].toString());
				
				
			}
			
		}
		return "";
	}
	
	private void iniUserArray()
	{
		for(int i = 0; i < users.length; i++)
		{
			users[i] = new User("", "");
		}
	}
	
	private boolean checkNullUsers(String[] userID, int index) throws Exception
	{
		if(userID[index].equals("")) {
			users[index] = new User("","");
			return true;
		}
		else {
			requestUser(userID, index);
			return false;
		}
		
	}
	
	private String requestUser(String[] usersID, int index) throws Exception
	{
		response = Conn.get(url + usersID[index],accessToken);
		return response;
	}
}
