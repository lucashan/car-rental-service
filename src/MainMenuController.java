import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.application.Platform;
import javafx.stage.Window;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;

public class MainMenuController
{
    @FXML
    private Button addToInventory;

    @FXML
    private Button addEmployee;

    @FXML
    private Button addCustomer;

    @FXML
    private Button recordSale;

    @FXML
    private Button exitMenu;

    // Add a public no-args constructor
    public MainMenuController()
    {
    }

    @FXML
    private void initialize()
    {
    }

    @FXML
    private void addToInventory() throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Inventory.fxml"));
        //loader.setController(new InventoryController("/views/Inventory.fxml"));
        Stage stage = (Stage) addToInventory.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }

    @FXML
    private void addEmployee() throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/Employee.fxml"));
        Stage stage = (Stage) addEmployee.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }

    @FXML
    private void addCustomer() throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/Customer.fxml"));
        Stage stage = (Stage) addCustomer.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }

    @FXML
    private void recordSale() throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/Record.fxml"));
        Stage stage = (Stage) recordSale.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }

    @FXML
    private void exit()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Goodbye!");
        alert.showAndWait();
        Platform.exit();
    }
}
