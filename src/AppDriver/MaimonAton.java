package AppDriver;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;

import controllers.RootLayoutController;

import java.time.Instant;
import static java.time.Duration.between;
import static java.time.Instant.now;

import java.io.IOException;

/**
 * Driver class for the MaimonAton application
 * 
 * @author braden
 *
 */
public class MaimonAton extends Application {

	private static Stage primaryStage;
	private Scene scene;
	private VBox rootLayout;
	private RootLayoutController controller;
	
	
	private final static String rootLayoutFXMLPath = "/view/UI/RootLayout.fxml";
	
	public static void main(String[] args) { launch(args); }
	
	@Override
	public void start(final Stage primaryStage) {
		System.out.println("Starting MaimonAton application");
		
		MaimonAton.primaryStage = primaryStage;
		MaimonAton.primaryStage.setTitle("MaimonAton");
		
		final Instant start = now();
		initRootLayout();
		final Instant end = now();
		System.out.println("Root layout initialized in "
				+ between(start, end).toMillis()
				+ "ms");
		
		primaryStage.setResizable(true);
		primaryStage.show();
		
		primaryStage.setOnCloseRequest(event -> {
			event.consume();
			if (controller != null) {
				controller.closeApplication();
			}
		});
		
	}
	
	public void initRootLayout() {
		// load the root layout from the FXML file
		final FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(rootLayoutFXMLPath));
		
		controller = new RootLayoutController();
		controller.setStage(primaryStage);
		loader.setController(controller);
//		loader.setRoot(controller);
		
		try {
			rootLayout = loader.load();
			
			scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.setResizable(true);
			primaryStage.centerOnScreen();
			
			final Parent root = scene.getRoot();
            for (Node node : root.getChildrenUnmodifiable()) {
                node.setStyle("-fx-focus-color: -fx-outer-border; -fx-faint-focus-color: transparent;");
            }
		} catch (IOException e) {
			System.out.println("Could not initialize root layout");
			e.printStackTrace();
		}
	}
}
