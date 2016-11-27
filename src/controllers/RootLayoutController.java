package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import AppDriver.MaimonAton;
import controllers.controlPanel.AddSourceController;
import loaders.RelationalDBLoader;
import models.db.RelationalDB;
import models.query.DBSearch;
import models.session.Session;
import view.UI.UIUtil;

public class RootLayoutController extends VBox implements Initializable {

	/* VARS */
	
	/* stages */
	private Stage addSourceControllerStage; 
	private Stage mainStage;
	
	/* db stuff */
	private RelationalDB rdb;
	
	/* search stuff */
	@FXML
    private TextField textInputField;
	private DBSearch dbSearch;
	
	/* session stuff */
	@FXML
	private TextArea interactionTextArea;
	private Session session;
	
	/* control panel stuff */
	private AddSourceController addSourceController;
	@FXML
	private TextArea responsePanelTextArea;
	
	
	/* end VARS */
	
	/* FXML onActions */
	@FXML
	public void saveSession() {
		System.out.print("Saving session");
		
		System.out.print(" --> complete\n");
	}
	
	@FXML
	public void loadSession() {
		System.out.print("Loading session");
		
		System.out.print(" --> complete\n");
	}
	
	@FXML
	public void exitApplication() {
		closeApplication();
	}
	
	@FXML
	public void initTutorial() {
		System.out.print("Bringing up tutorial");
		
		System.out.print(" --> complete\n");
	}
	
	@FXML
	public void initDeveloperInfo() {
		System.out.print("Bringing up developer info");
		
		System.out.print(" --> complete\n");
	}
	
	@FXML
	public void initAddSource() {
		if (addSourceControllerStage == null) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MaimonAton.class.getResource("/view/UI/AddSourceLayout.fxml"));
			
			addSourceControllerStage = new Stage();
			
			if (addSourceController == null) {
				if (this.rdb == null) {
					initRelationalDatabase();
				}
				addSourceController = new AddSourceController(this.rdb, addSourceControllerStage);
			}
			
			loader.setController(addSourceController);
			
			try {
				addSourceControllerStage.setScene(new Scene(loader.load()));
				
				addSourceControllerStage.setTitle("Add Source Controller");
				addSourceControllerStage.initOwner(mainStage);
				addSourceControllerStage.initModality(Modality.NONE);
				addSourceControllerStage.setResizable(false);
			} catch (IOException e) {
				System.out.println("Error initializating Add Source Controller");
				e.printStackTrace();
			}
			
			addSourceController.initChoiceBoxItems();
		}
		
		// call the method in AddSourceController that clears all of the fields
		addSourceControllerStage.show();
		addSourceControllerStage.toFront();
	}
	
	@FXML
	public void speechToText() {
		System.out.println("Initializing speech to text");
		
		System.out.println("--> complete");
	}
	
	@FXML
	public void openTrainingPanel() {
		System.out.print("Bringing up Training Panel");
		
		System.out.print(" --> complete\n");
	}
	
	@FXML
	public void moreInfo() {
		System.out.print("Bringing up More Info window");
		
		System.out.print(" --> complete\n");
	}
	
	@FXML
	public void editResponse() {
		System.out.print("Bringing up Edit Response Panel");
		
		System.out.print(" --> complete\n");
	}
	
	
	/* end FXML onAction */
	
	/* check FXML nodes */
	
	
	public void setStage(final Stage stage) {
		mainStage = stage;
	}
	
	private void initRelationalDatabase() {
		if (rdb == null) {
			rdb = new RelationalDB(RelationalDBLoader.buildRelationalDB());
		}
		
//		for (ArrayList<TextItem> list : rdb.getDB()) {
//			System.out.println(list.size());
//		}
	}
	
	private void initSearch() {
		if (rdb == null) {
			initRelationalDatabase();
		}
		
		this.dbSearch = new DBSearch(this.rdb);	
	}
	
	/**
	 * TODO
	 * Update this to handle returning users
	 * 
	 */
	private void initSession() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Enter your Name");
		dialog.setContentText("Please enter your name:");
		
		String name = "User";
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent() && result.get().length() > 0) {
			name = result.get();
		}
		this.session = new Session(name);
		
		
	}
	
	private void addComponentListeners() {

		/**
		 * This listener performs the search by listening for the enter key pressed, then passing the Session object the 
		 * query and the result of the db search
		 */
		textInputField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				if (ke.getCode().equals(KeyCode.ENTER)) {
					facilitateSearchAndUpdate(textInputField.textProperty().get());
					textInputField.clear();
				}
			}
			
		});
//		textInputField.textProperty().addListener((observable, oldValue, newValue) -> {
//            System.out.println(newValue);
//        });
		
	}
	
	private void facilitateSearchAndUpdate(String query) {
		String result = dbSearch.maxMatchSearch(query).getQuote().getQuoteAsStr();
		
		session.addInteraction(query, result);
		
		responsePanelTextArea.setText(UIUtil.formatRespPanelStr(result));
		
		refreshScene();
	}
	

	private void refreshScene() {
		interactionTextArea.setText(UIUtil.formatConvPanelStr(session.getConversation()));
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initRelationalDatabase();
		
		initSearch();
		
		initSession();
		
		//initSearchPanel
		
		//initControlPanel
		
		// addComponentListeners
		addComponentListeners();
	}
	
	
	public void closeApplication() {
		// check if sources have been added
		if (rdb != null && rdb.addedItems()) {
			// prompt the user to see if they would like to save the added results to the permanent databse file
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Save Added Sources to Permanent Database");
			alert.setContentText("The sources you've added this session have not been saved to the permanent database.\n"
					+ "Would you like to add them now to be used in later sessions?");
			
			ButtonType yes = new ButtonType("Yes");
			ButtonType no = new ButtonType("No");
			alert.getButtonTypes().setAll(yes, no);
			
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == yes) {
				// save the added sources to the database file
				System.out.println("Adding the sources to the permanent database");
				
			}
		}
		
		
		System.out.println("Exiting...");
		System.exit(0);
	}
}
