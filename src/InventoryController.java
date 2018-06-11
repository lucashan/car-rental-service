import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.control.ComboBox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class InventoryController
{
    private Connection connect;

    @FXML
    private Button add;

    @FXML
    private Button viewInfo;

    @FXML
    private Button back;

    @FXML
    private TextField year, model, make, condition, color, type, miles, price;
    
    @FXML
    private ComboBox combobox;
    
    

    private String nextView;
    public InventoryController()
    {
    }
    public InventoryController(String nextView)
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

        String inYear = year.getText();
        String inModel = model.getText();
        String inMake = make.getText();
        String inCondition = condition.getText();
        String inColor = color.getText();
        String inType = type.getText();
        String inMiles = miles.getText();
        String inPrice = price.getText();

        int intYear = Integer.parseInt(inYear);
        int intMiles = Integer.parseInt(inMiles);
        int intPrice = Integer.parseInt(inPrice);

        //String model, String make, int year, String condition, String color, int miles, int price, String type);
        addInventory(inModel, inMake, intYear, inCondition, inColor, intMiles, intPrice, inType);

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
        loader.setLocation(getClass().getResource("/views/viewCar.fxml"));
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
    public void addInventory(String model, String make, int year, String condition, String color, int miles, int price, String type){
        try {
            connect.setAutoCommit(false);
            Statement statement = connect.createStatement();

            String query = "INSERT INTO Car_Inventory(Model, Make, Year, Car_Condition, Colors, Miles, Price, Car_Type) VALUES (\"" +
                    model + "\", \"" + make + "\", " + year + ", \"" + condition + "\", \"" + color + "\", " + miles + ", " + price + ", \"" + type + "\")";
            statement.executeUpdate(query);
            connect.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
