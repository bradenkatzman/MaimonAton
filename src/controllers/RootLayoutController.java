package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class RootLayoutController extends BorderPane implements Initializable {

	private Stage mainStage;
	
	public void setStage(final Stage stage) {
		mainStage = stage;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// set up everything here
		
	}
	
	
	public void closeApplication() {
		System.out.println("Exiting...");
		System.exit(0);
	}
}
