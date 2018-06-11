import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.application.Platform;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;

public class RecordController
{
    private Connection connect;
    @FXML
    private Button add;

    @FXML
    private Button viewInfo;

    @FXML
    private Button back;

    @FXML
    private TextField recDate, recPrice, emplID, custID, carID;

    private String nextView;
    public RecordController()
    {
    }
    public RecordController(String nextView)
    {
        this.nextView = nextView;
    }

    @FXML
    private void add() throws Exception
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://ambari-head.csc.calpoly.edu/LKPD?user=LKPD&password=lkpd365");
        } catch (Exception e) {
            System.out.println(e);
        }
        Window owner = add.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        String date = recDate.getText();
        String inPrice = recPrice.getText();
        String inEID = emplID.getText();
        String inCustID = custID.getText();
        String inCarID = carID.getText();

        int intPrice = Integer.parseInt(inPrice);
        int intEID = Integer.parseInt(inEID);
        int intCustID = Integer.parseInt(inCustID);
        int intCarID = Integer.parseInt(inCarID);

        //String date, int price, int eid, int carID, int custID
        record(date, intPrice, intEID, intCustID, intCarID);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/Inventory.fxml"));  //<- what should happen when add is presesd
        Stage stage = (Stage) add.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }

    @FXML
    private void viewInfo() throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/viewSale.fxml"));
        Stage stage = (Stage) viewInfo.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
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
    public void record(String date, int price, int eid, int carID, int custID) {
        try {
            connect.setAutoCommit(false);
            Statement statement = connect.createStatement();

            String query = "INSERT INTO Sale(sale_Date, amount, e_id, cid, cust_id) VALUES (\""
                    + date + "\", " + price + ", " + eid + ", " + carID + ", " + custID + ")";
            statement.executeUpdate(query);
            connect.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //start();
    }
}
