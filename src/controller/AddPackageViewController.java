package controller; 

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Packages;

public class AddPackageViewController {

    @FXML
    private TextField trackingNum;

    @FXML
    private Button addpkgButton;
    
    @FXML
    private Button cancelButton;

    @FXML
    private TextField companytext;

    @FXML
    private TextField fromtext;

    @FXML
    private TextField totext;
    
    Scene previousScene;
    
    @FXML
    void addPkg(ActionEvent event) {
        String track = trackingNum.getText();
        String company = companytext.getText();
        String toaddress = totext.getText();
        String fromaddress = fromtext.getText();
        Packages p = new Packages();
        p.setId(Integer.getInteger(track));
        p.setCompany(company);
        
        p.setFromaddress(fromaddress);
        p.setToaddress(toaddress);
    }
    
    @FXML
    void goBack(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        //  option 2: get current stage -- from backbutton        
        // Stage stage = (Stage)backButton.getScene().getWindow();
        
        if (previousScene != null) {
            stage.setScene(previousScene);
        }
    }
    public void setPreviousScene(Scene scene) {
        previousScene = scene;
        cancelButton.setDisable(false);

    }


}
