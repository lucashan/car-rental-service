import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Car_Inventory;

import java.io.BufferedReader;
import java.io.StringReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ViewInvController
{
    @FXML
    private Button unsold;
    @FXML
    private Button sold;
    @FXML
    private Button all;
    @FXML
    private Button back;
    @FXML
    private Button search;
    @FXML
    private ComboBox combobox;
    @FXML
    private TextField userInput;
    @FXML
    private TableColumn carID;
    @FXML
    private TableColumn model;
    @FXML
    private TableColumn make;
    @FXML
    private TableColumn year;
    @FXML
    private TableColumn car_condition;
    @FXML
    private TableColumn colors;
    @FXML
    private TableColumn miles;
    @FXML
    private TableColumn price;
    @FXML
    private TableColumn car_type;
    @FXML
    private TableView tv;

    private Connection connect;


    public ViewInvController()
    {

    }

//    @FXML
//    private void viewInfo() throws Exception
//    {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("/views/viewCar.fxml"));
//        Stage stage = (Stage) viewCar.getScene().getWindow();
//        Scene scene = new Scene(loader.load());
//        stage.setScene(scene);
//    }
//
//    @FXML
//    private void backToMain() throws Exception
//    {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("/views/MainMenu.fxml"));
//        Stage stage = (Stage) back.getScene().getWindow();
//        Scene scene = new Scene(loader.load());
//        stage.setScene(scene);
//    }

    @FXML
    private void viewAll(ActionEvent event) throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://ambari-head.csc.calpoly.edu/LKPD?user=LKPD&password=lkpd365");
        } catch (Exception e) {
            System.out.println(e);
        }

        ObservableList<Car_Inventory> listCars = viewCar("", "");
        carID.setCellValueFactory(
                new PropertyValueFactory<Car_Inventory,String>("InventoryId"));
        model.setCellValueFactory(
                new PropertyValueFactory<Car_Inventory,String>("model"));
        make.setCellValueFactory(
                new PropertyValueFactory<Car_Inventory,String>("make"));
        year.setCellValueFactory(
                new PropertyValueFactory<Car_Inventory,String>("year"));
        car_condition.setCellValueFactory(
                new PropertyValueFactory<Car_Inventory,String>("CarCondition"));
        colors.setCellValueFactory(
                new PropertyValueFactory<Car_Inventory,String>("colors"));
        miles.setCellValueFactory(
                new PropertyValueFactory<Car_Inventory,String>("miles"));
        price.setCellValueFactory(
                new PropertyValueFactory<Car_Inventory,String>("price"));
        car_type.setCellValueFactory(
                new PropertyValueFactory<Car_Inventory,String>("CarType"));


        tv.setItems(null);
        tv.setItems(listCars);

        // TODO: add onAction="#search" to search buttons in every view FXML files (viewCustomer, viewEmployee, viewSale)
        // TODO: create table columns
        // TODO: parse result string, populate tableviews (look at the link in fb messenger)

    }
    @FXML
    private void search(ActionEvent event) throws Exception {
    	try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://ambari-head.csc.calpoly.edu/LKPD?user=LKPD&password=lkpd365");
        } catch (Exception e) {
            System.out.println(e);
        }

        String request = combobox.getValue().toString();
        String value = userInput.getText();
        
        ObservableList<Car_Inventory> listCars = viewCar(request, value);
        carID.setCellValueFactory(
                new PropertyValueFactory<Car_Inventory,String>("InventoryId"));
        model.setCellValueFactory(
                new PropertyValueFactory<Car_Inventory,String>("model"));
        make.setCellValueFactory(
                new PropertyValueFactory<Car_Inventory,String>("make"));
        year.setCellValueFactory(
                new PropertyValueFactory<Car_Inventory,String>("year"));
        car_condition.setCellValueFactory(
                new PropertyValueFactory<Car_Inventory,String>("CarCondition"));
        colors.setCellValueFactory(
                new PropertyValueFactory<Car_Inventory,String>("colors"));
        miles.setCellValueFactory(
                new PropertyValueFactory<Car_Inventory,String>("miles"));
        price.setCellValueFactory(
                new PropertyValueFactory<Car_Inventory,String>("price"));
        car_type.setCellValueFactory(
                new PropertyValueFactory<Car_Inventory,String>("CarType"));


        tv.setItems(null);
        tv.setItems(listCars);

        // TODO: add onAction="#search" to search buttons in every view FXML files (viewCustomer, viewEmployee, viewSale)
        // TODO: create table columns 
        // TODO: parse result string, populate tableviews (look at the link in fb messenger)

    }

    @FXML
    private void backToMain() throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/MainMenu.fxml"));
        Stage stage = (Stage) back.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }

    public ObservableList<Car_Inventory> viewCar(String req, String value) {
        ObservableList<Car_Inventory> list = FXCollections.observableArrayList();
        try {
        	System.out.println(req);        	
            Statement statement = connect.createStatement();
            ResultSet rs;
            String query = "";
            if(req.compareTo("Inventory ID") == 0) {
                query = "SELECT * FROM Car_Inventory WHERE inventoryID = " + value;
            }
            else if(req.compareTo("Model") == 0) {
                query = "SELECT * FROM Car_Inventory WHERE Model = \"" + value + "\"";
            }
            else if(req.compareTo("Make") == 0) {
                query = "SELECT * FROM Car_Inventory WHERE Make = \"" + value + "\"";
            }
            else if(req.compareTo("Year") == 0) {
                query = "SELECT * FROM Car_Inventory WHERE Year = " + value;
            }
            else if(req.compareTo("Colors") == 0) {
                query = "SELECT * FROM Car_Inventory WHERE Colors = \"" + value + "\"";
            }
            else if(req.compareTo("Miles") == 0) {
                query = "SELECT * FROM Car_Inventory WHERE Miles = " + value;
            }
            else if(req.compareTo("Price") == 0) {
                query = "SELECT * FROM Car_Inventory WHERE Price = " + value;
            }
            else if(req.compareTo("") == 0) {
                query = "SELECT * FROM Car_Inventory";
            }
            System.out.println(query);

            rs = statement.executeQuery(query);

            while (rs.next()) {
                int inventoryID = rs.getInt(1);
                String model = rs.getString(2);
                String make = rs.getString(3);
                int year = rs.getInt(4);
                String car_condition = rs.getString(5);
                String colors = rs.getString(6);
                int miles = rs.getInt(7);
                int price = rs.getInt(8);
                String car_type = rs.getString(9);
                Car_Inventory new_car = new Car_Inventory(inventoryID, model, make, year,
                        car_condition, colors, miles, price, car_type, 0);
                System.out.println(car_condition);
                list.add(new_car);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
}
