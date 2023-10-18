package application;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.Duration;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LocknChargeUI {

	private Token token;
	LockBays tower;
	UserBay users;
	private Stage mainStage;
	private Scene mainScene;
	BorderPane mainLayout;
	private HBox topBox, bottomBox;
	private VBox centerBox, rightBox, leftBox;
	private Label heading;
	private Image footerIMG;
	private ImageView footer;
	private Label showData;
	
	
	public LocknChargeUI(Stage stage) throws Exception
	{
		
		token = new Token();
		mainStage = stage;
		setStageTitleIcon(mainStage);
		topBox = new HBox(); 
		bottomBox = new HBox();
		centerBox = new VBox();
		rightBox = new VBox();
		leftBox = new VBox();
		mainLayout = new BorderPane(centerBox, topBox, rightBox, bottomBox, leftBox);
		mainScene = new Scene(mainLayout);
		showData = new Label("");
		
		
		
		setFooternTitle();
		idStyles();
		update();
		addUI();
	}
	
	private void addUI() {
		
		addUINodes();
		mainLayout.setTop(topBox);
		mainLayout.setCenter(centerBox);
		mainLayout.setRight(rightBox);
		mainLayout.setLeft(leftBox);
		mainLayout.setBottom(bottomBox);
		mainScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		mainStage.setScene(mainScene);
		mainStage.setFullScreen(true);
		mainStage.show();

	}
	
	private void addUsersUI() {
		
		Thread updateUsers = new Thread(()->{
			while(true)
			{
				try {
					
					Runnable updateLabel = () -> {
							
							try {
								showData.setText(formatUsers());
								System.out.println("Users updated");
							} catch (Exception e) {
								e.printStackTrace();
							}	
					};
					
					Platform.runLater(updateLabel);
					Thread.sleep(Duration.of(2, ChronoUnit.MINUTES));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			
		});
		
		updateUsers.setDaemon(true);
		updateUsers.start();
	}
	private void addUINodes()
	{
		topBox.getChildren().add(heading);
		bottomBox.getChildren().add(footer);
		centerBox.getChildren().add(showData);
	}
	
	public void setStageTitleIcon(Stage stage)
	{
		stage.setTitle("MTSD-LocknCharge");
		stage.getIcons().add(new Image("https://cfunity-school-logos.nfhsnetwork.com/v1/vt_logo_milton.png"));
	}
	
	public void setFooternTitle()
	{
		heading = new Label("IT Tower");
		footerIMG = new Image("https://mhs.mtsd-vt.org/pics/school_logo.png");
		footer = new ImageView();
		footer.setImage(footerIMG);
	}
	
	public void idStyles() 
	{
		topBox.getStyleClass().add("topbox");
		heading.getStyleClass().add("heading");
		centerBox.getStyleClass().add("centerbox");
		rightBox.getStyleClass().add("rightbox");
		leftBox.getStyleClass().add("leftbox");
		bottomBox.getStyleClass().add("bottombox");
	}

	private String formatUsers() throws Exception 
	{	
		String temp = "";
	
		tower = new LockBays(token.getToken());
		UserBay temp1 = new UserBay(token.getToken(), tower.getUsers());
		
		for(int i = 0; i < temp1.getUsers().length; i++)
		{
			temp += "Bay " + (i+1) + " - " + (temp1.getUsers()[i]) + "\n";
		}
		return temp;
	}
	
	private void update() {
		
		Thread updateToken = new Thread(()->
		{
			while(true)
			{
				try {
					addUsersUI();
					Thread.sleep(Duration.of(19, ChronoUnit.MINUTES));
					token = new Token();
					System.out.println(Instant.now().toEpochMilli() + "\nToken updated");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		updateToken.start();
	}
}
