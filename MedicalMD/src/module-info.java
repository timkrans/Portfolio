module TrackMD {
	requires javafx.controls;
	requires javafx.fxml;
	
	exports Controllers;
	
	opens Controllers to javafx.graphics, javafx.fxml;
	
	opens application to javafx.graphics, javafx.fxml;
	
}
