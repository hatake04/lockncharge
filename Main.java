package lockncharge;


import java.time.Instant;
import java.time.LocalTime;
import java.util.Timer;



public class Main {

		public static void main(String[] args) throws Exception{
			
			//Gets access token
			Token newToken = new Token();
			System.out.println(newToken.getTokenExpiration());
			System.out.println(newToken.getToken());
			LockBays tower = new LockBays(newToken.getToken());
			UserBay users = new UserBay(newToken.getToken(), tower.getUsers());
			
			
			//Timer tokenTimer = new Timer();
			//tokenTimer.schedule(new CountDown(tokenTimer, 600), 0, 1000);
			//Timer timerRefresh = new Timer();
			//timerRefresh.schedule(new CountDown(timerRefresh, 300), 0, 1000);
			
			
			
			
			
	
			
		}
}
