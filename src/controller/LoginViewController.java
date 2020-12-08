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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import model.Packages;
import model.Users;

public class LoginViewController {

    private EntityManager manager;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Button loginButton;

    private TableView<Packages> packageTable;

    Scene homepageScene;
    @FXML
    private Button createButton;

    @FXML
    void login(ActionEvent event) throws IOException {
        // Packages selected = packageTable.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PackageListView.fxml"));
        Parent loginView = loader.load();
        Scene homepageScene = new Scene(loginView);
        LoginViewController detailedController = loader.getController();

        //detailedController.initData(selected);
        Stage stage = new Stage();
        stage.setScene(homepageScene);
        stage.show();
    }

    /*
        @FXML
    void goToProf(ActionEvent event) throws IOException{
        Users selected = userTable.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ProfileView.fxml"));
        Parent detailedModelView = loader.load();
        Scene profileViewScene = new Scene(detailedModelView);
        ProfileController detailedController = loader.getController();
        
        detailedController.initData(selected);
        
        Stage stage = new Stage();
        stage.setScene(profileViewScene);
        stage.show();
        
    }
    /* 
     */

    public void initData(Packages pkgs) {
        manager = (EntityManager) Persistence.createEntityManagerFactory("PunFXMLPU").createEntityManager();

    }

    @FXML
    private void createUser(ActionEvent event) {
        //get information
        String user = username.getText();
        String pass = password.getText();

        Users person = new Users();
        person.setUsername(user);
        person.setPassword(pass);
        try {
            //add user to database
            manager.getTransaction().begin();
            manager.persist(person);
            manager.getTransaction().commit();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error!");
            alert.setHeaderText("Could not create user");
            alert.setContentText("User was already created or missing information, try again");
            alert.showAndWait();

        }
        //go to profile view to add profile information
        //go to package list
    }
}
