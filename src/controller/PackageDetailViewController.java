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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import model.Packages;

/**
 * FXML Controller class
 *
 * @author clarissapun
 */
public class PackageDetailViewController implements Initializable {
    private Packages pkg;
    @FXML
    private Label pkgId;
    @FXML
    private Label pkgDelivery;
    @FXML
    private Label pkgTo;
    @FXML
    private Label pkgFrom;
    @FXML 
    private ImageView image;   
    @FXML
    private Button backButton; 
    Scene previousScene;
    private EntityManager manager;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        manager = (EntityManager) Persistence.createEntityManagerFactory("PunFXMLPU").createEntityManager();

    }    
    public void initData(Packages pkgs) {
        pkg = pkgs;
        pkgId.setText(pkg.getId().toString());
        pkgDelivery.setText(pkg.getCompany());
        pkgTo.setText(pkg.getToaddress());
        pkgFrom.setText(pkg.getFromaddress());


        try {
            // path points to /resource/images/
            String imagename = "/resource/images/box_PNG27.png";
            Image profile = new Image(getClass().getResourceAsStream(imagename));
            image.setImage(profile);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
     public void setPreviousScene(Scene scene) {
        previousScene = scene;
        backButton.setDisable(false);

    }
    @FXML
    public void goBack(ActionEvent event) throws IOException {
        FXMLLoader root = new FXMLLoader(getClass().getResource("/view/PackageListView.fxml"));
        Parent detailedModelView = root.load();
        Scene scene = new Scene(detailedModelView);
        FXMLDocumentController detailedController = root.getController();

        Scene currentScene = ((Node) event.getSource()).getScene();
        //detailedController.setPreviousScene(currentScene);

        Stage stage = (Stage) currentScene.getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void editPackage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EditPackageView.fxml"));
        Parent detailedModelView = loader.load();
        Scene profileViewScene = new Scene(detailedModelView);
        EditPackageController detailedController = loader.getController();
        
        detailedController.initData(pkg);
        
        Scene currentScene = ((Node) event.getSource()).getScene();
        detailedController.setPreviousScene(currentScene);

        Stage stage = (Stage) currentScene.getWindow();
        stage.setScene(profileViewScene);
        stage.show();
    }
}
  
