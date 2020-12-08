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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import model.Packages;

/**
 * FXML Controller class
 *
 * @author clarissapun
 */
public class ProfileController implements Initializable {

    private EntityManager manager;
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane usernameText;

    @FXML
    private Text nameField;

    @FXML
    private Text addressField;

    @FXML
    private Text phoneField;

    @FXML
    private Text emailField;

    @FXML
    private Button backButton;

    @FXML
    private Button editButton;

    @FXML
    private ImageView profileImage;

    Scene previousScene;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        manager = (EntityManager) Persistence.createEntityManagerFactory("PunFXMLPU").createEntityManager();
    }    
    
    @FXML
    public void editUser(ActionEvent event) {
        
                try {
            
            //Packages existingPkg = manager.find(Packages.class, userText.getId());

            //if (existingPkg != null) {
                // begin transaction
            //    manager.getTransaction().begin();

                // update all atttributes
            //    existingPkg.setId(user.getId());
            //    existingPkg.setCompany(user.getCompany());
             //   existingPkg.setToaddress(user.getToaddress());
            //    existingPkg.setFromaddress(user.getFromaddress());

                // end transaction
                manager.getTransaction().commit();
            ///}
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
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
        assert profileImage != null : "fx:id=\"userImage\" was not injected: check your FXML file 'ProfileView.fxml'.";
        assert nameField != null : "fx:id=\"nameText\" was not injected: check your FXML file 'ProfileView.fxml'.";
        assert addressField != null : "fx:id=\"addressText\" was not injected: check your FXML file 'ProfileView.fxml'.";
        assert phoneField != null : "fx:id=\"phoneText\" was not injected: check your FXML file 'ProfileView.fxml'.";
        assert emailField != null : "fx:id=\"emailText\" was not injected: check your FXML file 'ProfileView.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'ProfileView.fxml'.";
        assert editButton != null : "fx:id=\"editButton\" was not injected: check your FXML file 'ProfileView.fxml'.";

    }
}

