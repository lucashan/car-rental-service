
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CustomerController
{
    private Connection connect;

    @FXML
    private Button add;

    @FXML
    private Button viewInfo;

    @FXML
    private Button back;

    @FXML
    private TextField custName, custAddr, custNumb;

    private String nextView;
    public CustomerController()
    {
    }
    public CustomerController(String nextView)
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

        String name = custName.getText();
        String addr = custAddr.getText();
        String numb = custNumb.getText();

        addCustomer(name, addr, numb);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/Customer.fxml"));  //<- what should happen when add is presesd
        Stage stage = (Stage) add.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }

    @FXML
    private void viewInfo() throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/viewCustomer.fxml"));
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
    public void addCustomer(String name, String phoneNum, String address) {
        try {
            connect.setAutoCommit(false);
            Statement statement = connect.createStatement();

            String query = "INSERT INTO Car_Customer(cName, phone, address) VALUES (\"" + name + "\", \"" + phoneNum + "\", \"" + address + "\")";
            statement.executeUpdate(query);
            connect.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
//		prompt();
    }
}
