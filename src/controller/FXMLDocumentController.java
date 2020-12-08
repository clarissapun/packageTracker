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
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Packages;
import model.Users;

/**
 *
 * @author clarissapun
 */
public class FXMLDocumentController implements Initializable {

    private EntityManager manager;

    @FXML
    private Button button;

    @FXML
    private Label label;

    @FXML
    private Button updatePackages;

    @FXML
    private Button readPackages;

    @FXML
    private Button createPackages;

    @FXML
    private Button deletePackages;

    @FXML
    private Button readToAndFromAddress;

    @FXML
    private Button readCompanyAndToAddress;

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
    
    @FXML
    private TableView<Users> userTable;
    
    @FXML 
    private TableColumn<Users, String> tableUsername = new TableColumn<>("username");
    
    @FXML
    private TableColumn<Users, String> tableName = new TableColumn<>("name");
    
    @FXML
    private TableColumn<Users, String> tableAddress = new TableColumn<>("address");
    
    @FXML
    private TableColumn<Users, String> tablePhoneNum = new TableColumn<>("phoneNum");
    
    @FXML
    private TableColumn<Users, String> tableEmail = new TableColumn<>("email");

    @FXML
    private TextField findPackage;

    @FXML
    private Button searchPackage;

    @FXML
    private Button searchAdvanced;

    @FXML
    private TextField findCompany;

    @FXML
    private Button showDetailsP;

    @FXML
    private Button showDetailsB;
    
    @FXML
    private Button profileButton;

    Scene profileScene;
    private ObservableList<Packages> pkgData;
    private ObservableList<Users> user;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("clicked");
        ///label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        manager = (EntityManager) Persistence.createEntityManagerFactory("PunFXMLPU").createEntityManager();
        tableID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        tableToAddress.setCellValueFactory(new PropertyValueFactory<>("toaddress"));
        tableFromAddress.setCellValueFactory(new PropertyValueFactory<>("fromaddress"));
    }
    /*nvm I think I got it*/
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
    Implementing CRUD operations
     */

    // Create operation
    public void create(Packages pkg) {
        try {
            // begin transaction
            manager.getTransaction().begin();

            // sanity check
            if (pkg.getId() != null) {

                // create package
                manager.persist(pkg);

                // end transaction
                manager.getTransaction().commit();

                System.out.println(pkg.toString() + " is created");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Read Operations
    public List<Packages> readAll() {
        Query query = manager.createNamedQuery("Packages.findAll");
        List<Packages> pkgs = query.getResultList();

        for (Packages pkg : pkgs) {
            System.out.println(pkg.getId() + " " + pkg.getCompany() + " " + pkg.getToaddress() + " " + pkg.getFromaddress());
        }

        return pkgs;
    }

    public Packages readById(int id) {
        Query query = manager.createNamedQuery("Packages.findById");
        query.setParameter("id", id);
        Packages pkg = null;

        try {
            pkg = (Packages) query.getSingleResult();
            if (pkg != null) {
                System.out.println(pkg.getId() + " " + pkg.getCompany() + " " + pkg.getToaddress() + " " + pkg.getFromaddress());
            }
        } catch (NoResultException e) {
            return null;
        }

        return pkg;
    }

    public Packages readByTracking(String trackingNum) {
        Query query = manager.createNamedQuery("Packages.findByTrackingNumber");

        // setting query parameter
        query.setParameter("trackingNumber", trackingNum);

        // execute query
        Packages pkg = (Packages) query.getSingleResult();
        if (pkg != null) {
            System.out.println(pkg.getId() + " " + pkg.getCompany() + " " + pkg.getToaddress() + " " + pkg.getFromaddress());
        }

        return pkg;
    }

    public List<Packages> readByCompany(String name) {
        Query query = manager.createNamedQuery("Packages.findByCompany");

        // setting query parameter
        query.setParameter("company", name);

        // execute query
        List<Packages> pkgs = query.getResultList();
        for (Packages pkg : pkgs) {
            System.out.println(pkg.getId() + " " + pkg.getCompany() + " " + pkg.getToaddress() + " " + pkg.getFromaddress());
        }

        return pkgs;
    }

    public List<Packages> readByToFromAddress(String Toaddress, String Fromaddress) {
        Query query = manager.createNamedQuery("Packages.findByToaddressAndFromaddress");

        // setting query parameter
        query.setParameter("Toaddress", Toaddress);
        query.setParameter("Fromaddress", Fromaddress);

        // execute query
        List<Packages> pkgs = query.getResultList();
        for (Packages pkg : pkgs) {
            System.out.println(pkg.getId() + " " + pkg.getCompany() + " " + pkg.getToaddress() + " " + pkg.getFromaddress());
        }

        return pkgs;
    }

    public List<Packages> readByCompanyToAddress(String company, String toaddress) {
        Query query = manager.createNamedQuery("Packages.findByCompanyAndFromaddress");

        // setting query parameter
        query.setParameter("company", company);
        query.setParameter("Toaddress", toaddress);

        // execute query
        List<Packages> pkgs = query.getResultList();
        for (Packages pkg : pkgs) {
            System.out.println(pkg.getId() + " " + pkg.getCompany() + " " + pkg.getToaddress() + " " + pkg.getFromaddress());
        }

        return pkgs;
    }

    // Update operation
    public void update(Packages model) {
        try {

            Packages existingPkg = manager.find(Packages.class, model.getId());

            if (existingPkg != null) {
                // begin transaction
                manager.getTransaction().begin();

                // update all atttributes
                existingPkg.setId(model.getId());
                existingPkg.setCompany(model.getCompany());
                existingPkg.setToaddress(model.getToaddress());
                existingPkg.setFromaddress(model.getFromaddress());

                // end transaction
                manager.getTransaction().commit();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Delete operation
    public void delete(Packages pkg) {
        try {
            Packages exisitingPkg = manager.find(Packages.class, pkg.getId());

            // sanity check
            if (exisitingPkg != null) {

                // begin transaction
                manager.getTransaction().begin();

                //remove Package
                manager.remove(exisitingPkg);

                // end transaction
                manager.getTransaction().commit();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void createPackages(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddPackageView.fxml"));
        Parent addPackageView = loader.load();
        Scene addPackageScene = new Scene(addPackageView);
        //AddPackageViewController viewController = loader.getController();

        //viewController.initialize();

        // create a new state
        Stage stage = new Stage();
        stage.setScene(addPackageScene);
        stage.show();
        
        
      /*  Scanner input = new Scanner(System.in);

        // read input from command line
        System.out.println("Enter ID:");
        int id = input.nextInt();

        System.out.println("Enter Company:");
        String company = input.next();

        System.out.println("Enter To Address:");
        String toAddress = input.nextLine();

        System.out.println("Enter From Address:");
        String fromAddress = input.nextLine();
        // create a package instance
        Packages pkg = new Packages();

        // set properties
        pkg.setId(id);
        pkg.setCompany(company);
        pkg.setToaddress(toAddress);
        pkg.setFromaddress(fromAddress);

        // save this package to database by calling Create operation        
        create(pkg);
        */
    }

    @FXML
    void deletePackages(ActionEvent event) {
        Scanner input = new Scanner(System.in);

        // read input from command line
        System.out.println("Enter ID:");
        int id = input.nextInt();

        Packages p = readById(id);
        System.out.println("we are deleting this package: " + p.toString());
        delete(p);

    }

    @FXML
    void readByID(ActionEvent event) {
        Scanner input = new Scanner(System.in);

        // read input from command line
        System.out.println("Enter ID:");
        int id = input.nextInt();

        Packages p = readById(id);
        System.out.println(p.toString());

    }

    @FXML
    void readByCompany(ActionEvent event) {
        Scanner input = new Scanner(System.in);

        // read input from command line
        System.out.println("Enter Company:");
        String name = input.next();

        List<Packages> p = readByCompany(name);
        System.out.println(p.toString());

    }

    @FXML
    void readPackages(ActionEvent event) {
        List<Packages> pkgs = readAll();
        alert(pkgs);
    }

    @FXML
    void updatePackages(ActionEvent event) {
        Scanner input = new Scanner(System.in);

        // read input from command line
        System.out.println("Enter ID:");
        int id = input.nextInt();

        System.out.println("Enter Company:");
        String company = input.next();

        System.out.println("Enter To Address:");
        String toAddress = input.nextLine();

        System.out.println("Enter From Address:");
        String fromAddress = input.nextLine();

        // create a package instance
        Packages pkg = new Packages();

        // set properties
        pkg.setId(id);
        pkg.setCompany(company);
        pkg.setToaddress(toAddress);
        pkg.setFromaddress(fromAddress);

        // save this package to database by calling Create operation        
        update(pkg);
    }

    @FXML
    void readByToFromAddress(ActionEvent event) {
        // name and cpga

        Scanner input = new Scanner(System.in);

        // read input from command line
        System.out.println("Enter To Address:");
        String to = input.nextLine();

        System.out.println("Enter From Address:");
        String from = input.nextLine();

        // create a student instance      
        List<Packages> p = readByToFromAddress(to, from);
        System.out.println(p.toString());

    }

    @FXML
    void readByCompanyToAddress(ActionEvent event) {
        // name and cpga

        Scanner input = new Scanner(System.in);

        // read input from command line
        System.out.println("Enter Company:");
        String company = input.nextLine();

        System.out.println("Enter To Address:");
        String to = input.nextLine();

        // create a student instance      
        List<Packages> p = readByToFromAddress(company, to);
        System.out.println(p.toString());

    }

    @FXML
    void searchPackage(ActionEvent event) {
        System.out.println("clicked");
        int packageToFind = Integer.valueOf(findPackage.getText());
        Packages pkg = readById(packageToFind);
        if(pkg == null){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error!");
            alert.setHeaderText("enter a different value.");
            alert.setContentText("No package(s)");
            alert.showAndWait();
        }else{
            packageTable.getItems().clear();
            packageTable.getItems().add(pkg);
            packageTable.refresh();
        }
        //packageTable.getColumns().addAll(tableID, tableCompany, tableToAddress, tableFromAddress);
    }

    @FXML
    void searchAdvanced(ActionEvent event) {
        System.out.println("advanced search");
        String packageToFind = findCompany.getText();
        List<Packages> pkgs = searchForIdAdvanced(packageToFind);
        /*tableID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        tableToAddress.setCellValueFactory(new PropertyValueFactory<>("toaddress"));
        tableFromAddress.setCellValueFactory(new PropertyValueFactory<>("fromaddress"));
        for(Packages p : pkgs){
             packageTable.getItems().add(p);
        }*/
        alert(pkgs);
    }

    private List<Packages> searchForIdAdvanced(String company) {
        Query query = manager.createNamedQuery("Packages.findByCompanyAdvanced");
        query.setParameter("company", company);

        List<Packages> pkgs = query.getResultList();
        for (Packages pkg : pkgs) {
            System.out.println(pkg.getId() + " " + pkg.getCompany() + " " + pkg.getToaddress() + " " + pkg.getFromaddress());
        }

        return pkgs;
    }

    @FXML
    void showDetails(ActionEvent event) throws IOException {
        Packages selected = packageTable.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PackageDetailView.fxml"));
        Parent detailedModelView = loader.load();
        Scene tableViewScene = new Scene(detailedModelView);
        PackageDetailViewController detailedController = loader.getController();

        detailedController.initData(selected);

        // create a new state
        Stage stage = new Stage();
        stage.setScene(tableViewScene);
        stage.show();
    }

    @FXML
    void showDetailsPlace(ActionEvent event) throws IOException {
        Packages selected = packageTable.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DetailedModelView.fxml"));

        Parent detailedModelView = loader.load();
        Scene tableViewScene = new Scene(detailedModelView);
        PackageDetailViewController detailedControlled = loader.getController();
        detailedControlled.initData(selected);

        Scene currentScene = ((Node) event.getSource()).getScene();
        detailedControlled.setPreviousScene(currentScene);

        Stage stage = (Stage) currentScene.getWindow();
        stage.setScene(tableViewScene);
        stage.show();
    }

    void alert(List<Packages> pkgs) {
        if (pkgs == null || pkgs.isEmpty()) {

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error!");
            alert.setHeaderText("enter a different value.");
            alert.setContentText("No package(s)");
            alert.showAndWait();
        } else {
            setTableData(pkgs);
        }
    }

    private void setTableData(List<Packages> pkgs) {
        pkgData = FXCollections.observableArrayList();

        pkgs.forEach(pkg -> {
            pkgData.add(pkg);
        });

        packageTable.setItems(pkgData);
        packageTable.refresh();
    }
}
