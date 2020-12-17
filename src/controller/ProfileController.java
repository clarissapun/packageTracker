/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Users;

/**
 * FXML Controller class
 *
 * @author clarissapun
 */
public class ProfileController implements Initializable {

    private EntityManager manager;
    Scene previousScene;
    private Users user;
    
    @FXML
    private Text phoneField;
    private Text nameField;
    private Text addressField;
    private Text emailField;
    @FXML
    private AnchorPane usernameText;
    @FXML
    private Button backButton;
    @FXML
    private Button editButton;
    @FXML
    private ImageView profileImage;
    @FXML
    private Label address;
    @FXML
    private Label phoneNum;
    @FXML
    private Label email;
    @FXML
    private Label username;
    @FXML
    private Label fname;
    @FXML
    private Label lname;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        manager = (EntityManager) Persistence.createEntityManagerFactory("PunFXMLPU").createEntityManager();
    }
    public void initData(Users user) {
        username.setText(user.getUsername().toString());
        fname.setText(user.getFirstname().toString());
        lname.setText(user.getLastname().toString());
        address.setText(user.getAddress().toString());
        phoneNum.setText(user.getPhoneNum().toString());
        email.setText(user.getEmail().toString());

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
    
    @FXML
    void goBack(ActionEvent event) throws IOException {
        //to package list
        FXMLLoader root = new FXMLLoader(getClass().getResource("/view/PackageListView.fxml"));
        Parent detailedModelView = root.load();
        Scene scene = new Scene(detailedModelView);
        PackageListController detailedController = root.getController();

        Scene currentScene = ((Node) event.getSource()).getScene();
        //detailedController.setPreviousScene(currentScene);

        Stage stage = (Stage) currentScene.getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void editUser(ActionEvent event) throws IOException {

        Query query = manager.createNamedQuery("Users.findByUsername");
        query.setParameter("username", "clarissapun");
        Users selected = (Users) query.getSingleResult();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EditProfileView.fxml"));
        Parent detailedModelView = loader.load();
        Scene profileViewScene = new Scene(detailedModelView);
        EditProfileController detailedController = loader.getController();
        
        detailedController.initData(selected);
        
        Scene currentScene = ((Node) event.getSource()).getScene();
        detailedController.setPreviousScene(currentScene);

        Stage stage = (Stage) currentScene.getWindow();
        stage.setScene(profileViewScene);
        stage.show();
        
    }

    

}
