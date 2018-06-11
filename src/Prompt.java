import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Scanner;
import java.sql.*;
import java.util.*;
public class Prompt extends Application {
	public Connection connect;
	private Scanner scan = new Scanner(System.in);
	
	public Prompt() {}

	public static void main(String[] args) {
		Prompt p = new Prompt();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            p.connect = DriverManager.getConnection("jdbc:mysql://ambari-head.csc.calpoly.edu/LKPD?user=LKPD&password=lkpd365");
        } catch (Exception e) {
            System.out.println(e);
        }
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainMenu.fxml"));
		Parent root = loader.load();
		MainMenuController controller = loader.getController();

		Scene scene = new Scene(root, 700, 700);
		stage.setTitle("Payroll");
		stage.setScene(scene);
		stage.show();
	}
    public List<String> viewCar(String req, String value) {
        try {
        	System.out.println(req);        	
            Statement statement = connect.createStatement();
            ResultSet rs;
            String query = "";
            if(req.compareTo("Inventory ID") == 0) {
            	query = "SELECT * FROM Car_Inventory WHERE inventoryID = " + value;
            }
            System.out.println(query);

            rs = statement.executeQuery(query);
            List<String> retList = new ArrayList<String>();
            //String resultStr = "";

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
                retList.add(inventoryID + " ");
                retList.add(year + " ");
                retList.add(make + " ");
                retList.add(model + " ");
                retList.add(car_condition + " ");
                retList.add(colors + " ");
                retList.add(miles + " ");
                retList.add(price + " ");
                retList.add(car_type + " ");
            }
            return retList;

        } catch (Exception e) {
            System.out.println(e);
        }
        return new ArrayList<String>();
    }
}