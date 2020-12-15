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
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import model.Packages;

/**
 * FXML Controller class
 *
 * @author clarissapun
 */
public class EditPackageController implements Initializable {
    private Scene previousScene;
    private Packages p;
    private EntityManager manager;

    @FXML
    private ImageView image;
    @FXML
    private Button backButton;
    @FXML
    private TextField pkgId;
    @FXML
    private TextField pkgCompany;
    @FXML
    private TextField pkgTo;
    @FXML
    private TextField pkgFrom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        manager = (EntityManager) Persistence.createEntityManagerFactory("PunFXMLPU").createEntityManager();

    }    

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PackageDetailView.fxml"));
        Parent detailedModelView = loader.load();
        Scene profileViewScene = new Scene(detailedModelView);
        PackageDetailViewController detailedController = loader.getController();
        Packages pkg = manager.find(Packages.class, p.getId());

        detailedController.initData(pkg);
        
        Scene currentScene = ((Node) event.getSource()).getScene();
        detailedController.setPreviousScene(currentScene);

        Stage stage = (Stage) currentScene.getWindow();
        stage.setScene(profileViewScene);
        stage.show();
    }

    @FXML
    private void saveChanges(ActionEvent event) {
        try {

            Packages pkg = manager.find(Packages.class, p.getId());

            if (pkg != null) {
                manager.getTransaction().begin();

                // update all atttributes
                pkg.setId(Integer.parseInt(pkgId.getText()));
                pkg.setCompany(pkgCompany.getText());
                pkg.setToaddress(pkgTo.getText());
                pkg.setFromaddress(pkgFrom.getText());
                // end transaction
                manager.getTransaction().commit();
            }
            //load profile again
            goBack(event);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void initData(Packages pkg){
        p = pkg;
        pkgId.setText("" + p.getId());
        pkgCompany.setText(p.getCompany());
        pkgTo.setText(p.getToaddress());
        pkgFrom.setText(p.getFromaddress());
        
        try {
            // path points to /resource/images/
            String imagename = "/resource/images/box_PNG27.png";
            Image box = new Image(getClass().getResourceAsStream(imagename));
            image.setImage(box);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    void setPreviousScene(Scene scene) {
        previousScene = scene;
        backButton.setDisable(false);   
    }
    
}
