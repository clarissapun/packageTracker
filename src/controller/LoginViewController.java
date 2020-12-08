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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Packages;
import model.Users;

public class LoginViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label username;

    @FXML
    private Label password;

    @FXML
    private Button loginButton;
    
    @FXML
    private TableView<Packages> packageTable;

    @FXML
    private TableColumn<Packages, Integer> tableID = new TableColumn<>("id");

    @FXML
    private TableColumn<Packages, String> tableCompany = new TableColumn<>("company");

    @FXML
    private TableColumn<Packages, String> tableToAddress = new TableColumn<>("to address");

    @FXML
    private TableColumn<Packages, String> tableFromAddress = new TableColumn<>("from address");
    
    
    Scene homepageScene;

    @FXML
    void login(ActionEvent event) throws IOException {
        Packages selected = packageTable.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PackageListView.fxml"));
        Parent loginView = loader.load();
        Scene homepageScene = new Scene(loginView);
        LoginViewController detailedController = loader.getController();
        
        detailedController.initData(selected);
        
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
    }
}
