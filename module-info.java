module lockncharge {
	requires javafx.controls;
	requires json.simple;
	requires javafx.graphics;
	requires java.desktop;
	
	opens application to javafx.graphics, javafx.fxml;
}
