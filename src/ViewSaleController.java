import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Sale;

import java.io.BufferedReader;
import java.io.StringReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ViewSaleController {
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
    private TableColumn s_id;
    @FXML
    private TableColumn sale_date;
    @FXML
    private TableColumn amount;
    @FXML
    private TableColumn e_id;
    @FXML
    private TableColumn cid;
    @FXML
    private TableColumn commission_amount;
    @FXML
    private TableColumn cust_id;

    @FXML
    private TableView tv;

    private Connection connect;


    public ViewSaleController()
    {

    }
    @FXML
    private void viewAll(ActionEvent event) throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://ambari-head.csc.calpoly.edu/LKPD?user=LKPD&password=lkpd365");
        } catch (Exception e) {
            System.out.println(e);
        }

        ObservableList<Sale> listSale = viewSale("", "");
        s_id.setCellValueFactory(
                new PropertyValueFactory<Sale,String>("S_ID"));
        sale_date.setCellValueFactory(
                new PropertyValueFactory<Sale,String>("Sale_Date"));
        amount.setCellValueFactory(
                new PropertyValueFactory<Sale,String>("Amount"));
        e_id.setCellValueFactory(
                new PropertyValueFactory<Sale,String>("Eid"));
        cid.setCellValueFactory(
                new PropertyValueFactory<Sale,String>("Cid"));
        commission_amount.setCellValueFactory(
                new PropertyValueFactory<Sale,String>("Commission_Amount"));
        cust_id.setCellValueFactory(
                new PropertyValueFactory<Sale,String>("Cust_ID"));


        tv.setItems(null);
        tv.setItems(listSale);
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

        ObservableList<Sale> listSale = viewSale(request, value);
        s_id.setCellValueFactory(
                new PropertyValueFactory<Sale,String>("S_ID"));
        sale_date.setCellValueFactory(
                new PropertyValueFactory<Sale,String>("Sale_Date"));
        amount.setCellValueFactory(
                new PropertyValueFactory<Sale,String>("Amount"));
        e_id.setCellValueFactory(
                new PropertyValueFactory<Sale,String>("Eid"));
        cid.setCellValueFactory(
                new PropertyValueFactory<Sale,String>("Cid"));
        commission_amount.setCellValueFactory(
                new PropertyValueFactory<Sale,String>("Commission_Amount"));
        cust_id.setCellValueFactory(
                new PropertyValueFactory<Sale,String>("Cust_ID"));


        tv.setItems(null);
        tv.setItems(listSale);
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

    public ObservableList<Sale> viewSale(String req, String value) {
        ObservableList<Sale> list = FXCollections.observableArrayList();
        try {
            System.out.println(req);
            Statement statement = connect.createStatement();
            ResultSet rs;
            String query = "";
            if(req.compareTo("Sale ID") == 0) {
                query = "SELECT * FROM Sale WHERE s_id = " + value;
            }
            else if(req.compareTo("Sale Date") == 0) {
                query = "SELECT * FROM Sale WHERE sale_date = \"" + value + "\"";
            }
            else if(req.compareTo("Amount") == 0) {
                query = "SELECT * FROM Sale WHERE amount = " + value;
            }
            else if(req.compareTo("Emp ID") == 0) {
                query = "SELECT * FROM Sale WHERE e_id = " + value;
            }
            else if(req.compareTo("Car ID") == 0) {
                query = "SELECT * FROM Sale WHERE cid = " + value;
            }
            else if(req.compareTo("Commission Amount") == 0) {
                query = "SELECT * FROM Sale WHERE commission_amount = " + value;
            }
            else if(req.compareTo("Customer ID") == 0) {
                query = "SELECT * FROM Sale WHERE cust_id = " + value;
            }
            else if(req.compareTo("") == 0) {
                query = "SELECT * FROM Sale";
            }
            System.out.println(query);

            rs = statement.executeQuery(query);

            while (rs.next()) {
                int s_id = rs.getInt(1);
                Date sale_date2 = rs.getDate(2);
                int amount = rs.getInt(3);
                int e_id = rs.getInt(4);
                int cid = rs.getInt(5);
                int commission_amount = rs.getInt(6);
                int cust_id = rs.getInt(7);
                String sale_date = sale_date2.toString();
                Sale new_sale = new Sale(s_id, sale_date, amount, e_id, cid, commission_amount, cust_id);
                System.out.println(s_id);
                list.add(new_sale);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

}
