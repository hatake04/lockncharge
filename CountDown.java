package lockncharge;

import java.util.Timer;
import java.util.TimerTask;

import lockncharge.Connect.Conn;

public class CountDown extends TimerTask {
	
	int seconds;
	Timer timer;
	int temp;
	
	public CountDown(Timer input, int secs)
	{
		timer = input;
		seconds = temp = secs;
		
	}
	public void run() {
		
		switch(seconds)
		{
			case 600:
			{
				break;
			}
			case 300:
			{
				break;
			}
		}
		
		if(seconds > 0)
		{
			seconds--;
			System.out.println(seconds);
		}
		else
		{
			
			try {
				String response;
				response = Conn.post("https://api.lockncharge.io/v1/token","{\"client_id\": \"f76fe190-cbca-44ff-9987-386323b93446\",\"client_secret\": \"Titan69!\"}");
				FormatToken format = new FormatToken(response);
				System.out.println(format.getExpirationTime());
				System.out.println(format.getAccessToken());
				LockBays tower = new LockBays(format.getAccessToken());
				UserBay users = new UserBay(format.getAccessToken(), tower.getUsers());
				
				seconds = temp;
		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
				
	}

}
