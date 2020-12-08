/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Packages;

//current error showing up in FXMLDocumentController.java and PackageListcontroller.java
//package javax.persistence does not exist
public class PackageListController implements Initializable {

    @FXML
    private ImageView upsImage;
    @FXML
    private ImageView amazonImage;

    @FXML
    private ScrollBar scrollBar;

    @FXML
    private Button addPackageButton;

    @FXML
    private ImageView userProfile;

    @FXML
    private Button profileButton;
    
    Scene profileScene;

    @FXML
    private ListView<Packages> pacakgeList;

    private EntityManager manager;
    private TextField txtAddItem = new TextField();

    @FXML
    void goToProfile(ActionEvent event) {
        displayData();
    }
        public void setPreviousScene(Scene scene) {
        profileScene = scene;
        profileButton.setDisable(false);

    }
    @FXML
    void goBack(ActionEvent event) {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        //  option 2: get current stage -- from backbutton        
        // Stage stage = (Stage)backButton.getScene().getWindow();
        
        if (profileScene != null) {
            stage.setScene(profileScene);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        manager = (EntityManager) Persistence.createEntityManagerFactory("PunFXMLPU").createEntityManager();
        displayData();
    }
    private void displayData(){
        ObservableList<Packages> data = FXCollections.observableArrayList();
        List<Packages> pkgs = readAll();
        pkgs.forEach(pkg -> data.add(pkg));
        pacakgeList = new ListView<Packages>(data);
        pacakgeList.setItems(data);
        pacakgeList.setCellFactory(pkg -> new PackageListViewCell());
        addListeners();
    }

    public List<Packages> readAll() {
        Query query = manager.createNamedQuery("Packages.findAll");
        List<Packages> pkgs = query.getResultList();

        for (Packages pkg : pkgs) {
            System.out.println(pkg.getId() + " " + pkg.getCompany() + " " + pkg.getToaddress() + " " + pkg.getFromaddress());
        }

        return pkgs;
    }

    public void addListeners() {
        // Add a ChangeListener to TextField to look for change in focus
        txtAddItem.focusedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (txtAddItem.isFocused()) {
                    //BtnAdd.setDisable(false);
                }
            }
        });

        // Add a ChangeListener to ListView to look for change in focus
        pacakgeList.focusedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (pacakgeList.isFocused()) {
                    //BtnDelete.setDisable(false);
                }
            }
        });
    }
///////////to reset stage/scene

    public void ss(Stage primaryStage) {
/*
        StackPane root = new StackPane();
        root.getChildren().add(pacakgeList);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();*/
    }

    private class PackageListViewCell extends ListCell<Packages> {

        @FXML
        private HBox content;
        @FXML
        private Text id;
        @FXML
        private Text from;
        @FXML
        private Text expected;

        public PackageListViewCell() {
            super();
            id = new Text();
            from = new Text();
            expected = new Text();
        }

        @Override
        protected void updateItem(Packages item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null && !empty) { // <== test for null item and empty parameter
                VBox vBox = new VBox(id, from, expected);
                ImageView icon = new ImageView();
                System.out.println(item.getCompany().toLowerCase());
                icon.setImage(new Image("resource/images/" + item.getCompany().toLowerCase() + ".png"));
                icon.setFitWidth(100);
                icon.setPreserveRatio(true);
                icon.setSmooth(true);
                icon.setCache(true);
                content = new HBox(icon, vBox);
                content.setSpacing(10);
                setGraphic(content);
            } else {
                setGraphic(null);
            }
        }
    }
}
