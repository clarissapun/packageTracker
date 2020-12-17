/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import model.Users;

/**
 * FXML Controller class
 *
 * @author clarissapun
 */
public class EditProfileController implements Initializable {

    @FXML
    private AnchorPane usernameText;
    @FXML
    private Button backButton;
    @FXML
    private ImageView profileImage;
    @FXML
    private TextField username;
    @FXML
    private TextField address;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;

    Scene previousScene;

    private Users user;
    
    private EntityManager manager;
    @FXML
    private Text phoneField;
    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private Button editButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //set text fields to current user
        manager = (EntityManager) Persistence.createEntityManagerFactory("PunFXMLPU").createEntityManager();

    }    

    @FXML
    private void goBack(ActionEvent event) {
        //go back to profile view
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        //  option 2: get current stage -- from backbutton        
        // Stage stage = (Stage)backButton.getScene().getWindow();
        
        if (previousScene != null) {
            stage.setScene(previousScene);
        }
    }

    @FXML
    private void saveChanges(ActionEvent event) {
        //update user
        try {

            Users existingUser = manager.find(Users.class, user.getUsername());

            if (existingUser != null) {
                manager.getTransaction().begin();

                // update all atttributes
                existingUser.setFirstname(fname.getText());
                existingUser.setLastname(lname.getText());
                existingUser.setAddress(address.getText());
                existingUser.setPhoneNum(phone.getText());
                existingUser.setEmail(email.getText());

                // end transaction
                manager.getTransaction().commit();
            }
            //load profile again
            toProfile(event);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void toProfile(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ProfileView.fxml"));
        Parent detailedModelView = loader.load();
        Scene profileViewScene = new Scene(detailedModelView);
        ProfileController detailedController = loader.getController();
        Users existingUser = manager.find(Users.class, user.getUsername());

        detailedController.initData(existingUser);
        
        Scene currentScene = ((Node) event.getSource()).getScene();
        detailedController.setPreviousScene(currentScene);

        Stage stage = (Stage) currentScene.getWindow();
        stage.setScene(profileViewScene);
        stage.show();
    }

    public void initData(Users u){
        user = u;
        username.setText(user.getUsername());
        fname.setText(user.getFirstname());
        lname.setText(user.getLastname());
        address.setText(user.getAddress());
        phone.setText(user.getPhoneNum());
        email.setText(user.getEmail());
        
        try {
            // path points to /resource/images/
            String imagename = "/resource/images/profileStock.png";
            Image profile = new Image(getClass().getResourceAsStream(imagename));
            profileImage.setImage(profile);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void setPreviousScene(Scene scene) {
        previousScene = scene;
        backButton.setDisable(false);    
    }
}
