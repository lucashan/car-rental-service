import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Car_Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ViewCustomerController
{
    @FXML
    private Button back;
    @FXML
    private Button search;
    @FXML
    private ComboBox combobox;
    @FXML
    private TextField userInput;
    @FXML
    private TableColumn custID;
    @FXML
    private TableColumn name;
    @FXML
    private TableColumn address;
    @FXML
    private TableColumn phone;
    @FXML
    private TableView tv;

    private Connection connect;


    public ViewCustomerController()
    {

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
    @FXML
    private void viewAll() throws Exception
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://ambari-head.csc.calpoly.edu/LKPD?user=LKPD&password=lkpd365");
        } catch (Exception e) {
            System.out.println(e);
        }
        ObservableList<Car_Customer> listCustomer = viewCustomer("", "");
        custID.setCellValueFactory(
                new PropertyValueFactory<Car_Customer, String>("CustID"));
        name.setCellValueFactory(
                new PropertyValueFactory<Car_Customer, String>("CName"));
        address.setCellValueFactory(
                new PropertyValueFactory<Car_Customer, String>("Address"));
        phone.setCellValueFactory(
                new PropertyValueFactory<Car_Customer, String>("Phone"));


        tv.setItems(null);
        tv.setItems(listCustomer);
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

        ObservableList<Car_Customer> listCustomer = viewCustomer(request, value);
        custID.setCellValueFactory(
                new PropertyValueFactory<Car_Customer, String>("CustID"));
        name.setCellValueFactory(
                new PropertyValueFactory<Car_Customer, String>("CName"));
        address.setCellValueFactory(
                new PropertyValueFactory<Car_Customer, String>("Address"));
        phone.setCellValueFactory(
                new PropertyValueFactory<Car_Customer, String>("Phone"));


        tv.setItems(null);
        tv.setItems(listCustomer);

    }

    public ObservableList<Car_Customer> viewCustomer(String req, String value) {
        ObservableList<Car_Customer> list = FXCollections.observableArrayList();
        try {
            System.out.println(req);
            Statement statement = connect.createStatement();
            ResultSet rs;
            String query = "";
            if(req.compareTo("Customer ID") == 0) {
                query = "SELECT * FROM Car_Customer WHERE custID = " + value;
            }
            else if(req.compareTo("Name") == 0) {
                query = "SELECT * FROM Car_Customer WHERE cName = \"" + value +"\"";
            }
            else if(req.compareTo("Address") == 0) {
                query = "SELECT * FROM Car_Customer WHERE address = \"" + value +"\"";
            }
            else if(req.compareTo("Phone") == 0) {
                query = "SELECT * FROM Car_Customer WHERE phone = \"" + value +"\"";
            }
            else if(req.compareTo("") == 0) {
                query = "SELECT * FROM Car_Customer";
            }
            System.out.println(query);

            rs = statement.executeQuery(query);

            while (rs.next()) {
                int custID = rs.getInt(1);
                String cName = rs.getString(2);
                String phone = rs.getString(3);
                String address = rs.getString(4);
                Car_Customer new_customer = new Car_Customer(custID, cName, phone, address);
                list.add(new_customer);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    }


