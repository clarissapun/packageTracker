package controller; 
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class packageViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker orderDate;

    @FXML
    private TextField trackingNum;

    @FXML
    private TextArea additionalInfo;
    
    @FXML
    private Button addPackageButton;

    @FXML
    void addPackage(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert orderDate != null : "fx:id=\"orderDate\" was not injected: check your FXML file 'AddPackageView.fxml'.";
        assert trackingNum != null : "fx:id=\"trackingNum\" was not injected: check your FXML file 'AddPackageView.fxml'.";
        assert additionalInfo != null : "fx:id=\"additionalInfo\" was not injected: check your FXML file 'AddPackageView.fxml'.";
        assert addPackageButton != null : "fx:id=\"addPackageButton\" was not injected: check your FXML file 'AddPackageView.fxml'.";
    }
}
