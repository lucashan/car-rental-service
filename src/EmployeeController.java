import java.io.IOException;
import java.net.URL;
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

public class EmployeeController
{
    private Connection connect;
    @FXML
    private Button add;

    @FXML
    private Button viewInfo;

    @FXML
    private Button back;

    @FXML
    private TextField emplName, emplDOB, emplDate, emplRate;

    private String nextView;
    public EmployeeController() {

    }
    public EmployeeController(String nextView) {
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

        String name = emplName.getText();
        String dob = emplDOB.getText();
        String date = emplDate.getText();
        String rate = emplRate.getText();
        int rateInt = Integer.parseInt(rate);

        addEmployee(name, dob, date, rateInt);   //addEmployee(name, dob, date, rate);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/Employee.fxml"));  //<- what should happen when add is presesd
        Stage stage = (Stage) add.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }


    @FXML
    private void viewInfo() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/viewEmployee.fxml"));
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
    public void addEmployee(String name, String dob, String empDate, int rate) {
        try {
            connect.setAutoCommit(false);
            Statement statement = connect.createStatement();

            String query = "INSERT INTO Salesperson(sName, dobirth, employment_date, commission_rate) VALUES (\""
                    + name + "\",\"" + dob + "\", \"" + empDate + "\", " + rate +  ")";
            statement.executeUpdate(query);
            connect.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
//		start();
    }
}
