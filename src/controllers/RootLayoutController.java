package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import loaders.RelationalDBLoader;
import models.db.RelationalDB;
import models.db.TextItem;
import models.query.DBSearch;
import models.session.Session;
import view.UI.UIUtil;

public class RootLayoutController extends VBox implements Initializable {

	/* vars */
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
	
	/* response panel stuff */
	@FXML
	private TextArea responsePanelTextArea;
	
	
	/* end vars */
	
	/* FXML onAction */
	
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
		System.out.print("Bringing up Add Source controller");
		
		System.out.print(" --> complete\n");
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
		if (result.isPresent()) {
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
		System.out.println("Exiting...");
		System.exit(0);
	}
}
