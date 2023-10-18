package application;


import javafx.application.Application;
import javafx.stage.Stage;



public class Main extends Application 
 {
	@Override
	public void start(Stage primaryStage) {
		
		try {
			
			//Token newToken = new Token();
			//LockBays tower = new LockBays(newToken.getToken());
			//UserBay users = new UserBay(newToken.getToken(), tower.getUsers());
			LocknChargeUI controller = new LocknChargeUI(primaryStage);
			//Scheduler timer = new Scheduler(primaryStage);
			//timer.start();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	

	
}
