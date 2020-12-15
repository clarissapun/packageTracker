package controller; 

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import model.Packages;

public class AddPackageViewController implements Initializable {

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
    
    EntityManager manager;

    @FXML
    void addPkg(ActionEvent event) throws IOException {
        String track = trackingNum.getText();
        String company = companytext.getText();
        String toaddress = totext.getText();
        String fromaddress = fromtext.getText();
        System.out.println("tracking num: " + track);
        System.out.println("company: " + company);
        System.out.println("to: " + toaddress);
        System.out.println("from: " + fromaddress);

        Packages p = new Packages();
        p.setId(Integer.parseInt(track));
        p.setCompany(company);
        p.setFromaddress(fromaddress);
        p.setToaddress(toaddress);
        try{
        manager.getTransaction().begin();  
        manager.persist(p);
        manager.getTransaction().commit();
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error!");
            alert.setHeaderText("Could not add package");
            alert.setContentText("package was already added or information is incorrect, try again");
            alert.showAndWait();
       
        }
        goBack(event);
    }
    
    @FXML
    void goBack(ActionEvent event) throws IOException {
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
    public void setPreviousScene(Scene scene) {
        previousScene = scene;
        cancelButton.setDisable(false);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        manager = (EntityManager) Persistence.createEntityManagerFactory("PunFXMLPU").createEntityManager();    
    }


}
