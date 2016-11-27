package controllers.controlPanel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.db.Category;
import models.db.Quote;
import models.db.RelationalDB;
import models.db.RelationalDBUtil;
import models.db.Source;
import models.db.TextItem;

public class AddSourceController extends AnchorPane implements Initializable {

	@FXML
	private TextArea quoteTextArea;
	
	@FXML
	private ChoiceBox<String> categoryChoiceBox;
	
	@FXML
	private TextField sourceTextField;
	
	private RelationalDB rdb;
	private Stage addSourceControllerStage;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) { assertFXMLNodes(); }
	
	public AddSourceController(RelationalDB rdb, final Stage stage) {
		super();
		
		this.rdb = rdb;
		this.addSourceControllerStage = stage;
	}
	
	public void initChoiceBoxItems() {
		if (categoryChoiceBox != null) {
			categoryChoiceBox.getItems().addAll("Mean", "Virtue", "Morality", "Negative Theology",
					"Biomedical Ethics", "Imitatio Dei", "Knowledge",
					"Lashon Hara", "Soul", "Veils", "Human Potential",
					"Choice", "Image of God", "Social", "Political",
					"Commandments", "System Justification", "Vice", "N/A");
		}
	}
	
	private void assertFXMLNodes() {
		assert(quoteTextArea != null);
		assert(categoryChoiceBox != null);
		assert(sourceTextField != null);
	}
	
	@FXML
	public void completeAddSourceButton() {
		String quote = quoteTextArea.getText();
		String category = categoryChoiceBox.getValue();
		String source = sourceTextField.getText();
		
		// only proceed if quote field is valid
		if (quote != null && quote.length() != 0) {
			
			if (source == null || source.length() == 0) {
				source = "";	
			}
			
			rdb.addTextItem(new TextItem(new Quote(quote), (Category)RelationalDBUtil.catDescr2idx2Cat.get(category).get(RelationalDBUtil.CATEGORYVALUE_IDX), new Source(source)));
			
			quoteTextArea.clear();
			categoryChoiceBox.setValue("N/A");
			sourceTextField.clear();
			
			addSourceControllerStage.hide();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Incomplete Fields");
			alert.setContentText("A quote must be entered to add to the database.");
			alert.showAndWait();
		}
	}
}