/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author clarissapun
 */
public class ProfileController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text usernameText;

    @FXML
    private ImageView userImage;

    @FXML
    private Text nameText;

    @FXML
    private Text addressText;

    @FXML
    private Text phoneText;

    @FXML
    private Text emailText;

    @FXML
    private Button backButton;

    @FXML
    private Button editButton;
    
    Scene previousScene;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    @FXML
    void editUser(ActionEvent event) {
        
    }

    public void setPreviousScene(Scene scene) {
        previousScene = scene;
        backButton.setDisable(false);

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

    @FXML
    void initialize() {
        assert usernameText != null : "fx:id=\"usernameText\" was not injected: check your FXML file 'ProfileView.fxml'.";
        assert userImage != null : "fx:id=\"userImage\" was not injected: check your FXML file 'ProfileView.fxml'.";
        assert nameText != null : "fx:id=\"nameText\" was not injected: check your FXML file 'ProfileView.fxml'.";
        assert addressText != null : "fx:id=\"addressText\" was not injected: check your FXML file 'ProfileView.fxml'.";
        assert phoneText != null : "fx:id=\"phoneText\" was not injected: check your FXML file 'ProfileView.fxml'.";
        assert emailText != null : "fx:id=\"emailText\" was not injected: check your FXML file 'ProfileView.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'ProfileView.fxml'.";
        assert editButton != null : "fx:id=\"editButton\" was not injected: check your FXML file 'ProfileView.fxml'.";

    }
}

