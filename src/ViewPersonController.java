import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Salesperson;

import java.io.BufferedReader;
import java.io.StringReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ViewPersonController {

	// Private Buttons
	@FXML
	private Button back;
	@FXML
	private Button search;
	@FXML
	private Button all;
	@FXML
	private TextField userInput;
	@FXML
	private ComboBox combobox;
	@FXML
	private TableColumn eid;
	@FXML
	private TableColumn name;
	@FXML
	private TableColumn dob;
	@FXML
	private TableColumn eDate;
	@FXML
	private TableColumn cRate;
	@FXML
	private TableView tv;
	private Connection connect;

	public ViewPersonController() {
	}
	@FXML
	private void viewAll(ActionEvent event) throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://ambari-head.csc.calpoly.edu/LKPD?user=LKPD&password=lkpd365");
		} catch (Exception e) {
			System.out.println(e);
		}

		ObservableList<Salesperson> listEmployee = viewEmployee("", "");

		eid.setCellValueFactory(new PropertyValueFactory<Salesperson, String>("Eid"));
		name.setCellValueFactory(new PropertyValueFactory<Salesperson, String>("SName"));
		dob.setCellValueFactory(new PropertyValueFactory<Salesperson, String>("Dobirth"));
		eDate.setCellValueFactory(new PropertyValueFactory<Salesperson, String>("Employment_Date"));
		cRate.setCellValueFactory(new PropertyValueFactory<Salesperson, String>("Commission_Rate"));

		tv.setItems(null);
		tv.setItems(listEmployee);
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

		ObservableList<Salesperson> listEmployee = viewEmployee(request, value);

		eid.setCellValueFactory(new PropertyValueFactory<Salesperson, String>("Eid"));
		name.setCellValueFactory(new PropertyValueFactory<Salesperson, String>("SName"));
		dob.setCellValueFactory(new PropertyValueFactory<Salesperson, String>("Dobirth"));
		eDate.setCellValueFactory(new PropertyValueFactory<Salesperson, String>("Employment_Date"));
		cRate.setCellValueFactory(new PropertyValueFactory<Salesperson, String>("Commission_Rate"));

		tv.setItems(null);
		tv.setItems(listEmployee);
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

	public ObservableList<Salesperson> viewEmployee(String req, String value) {
		ObservableList<Salesperson> list = FXCollections.observableArrayList();
		try {
			System.out.println(req);
			Statement statement = connect.createStatement();
			ResultSet rs;
			String query = "";
			if (req.compareTo("Employee ID") == 0) {
				query = "SELECT * FROM Salesperson WHERE eid = " + value;
			}
			else if (req.compareTo("Name") == 0) {
				query = "SELECT * FROM Salesperson WHERE sName = \"" + value + "\"";
			}
			else if (req.compareTo("Date of Birth") == 0) {
				query = "SELECT * FROM Salesperson WHERE dobirth = \"" + value + "\"";
			}
			else if (req.compareTo("Employment Date") == 0) {
				query = "SELECT * FROM Salesperson WHERE employment_date = \"" + value + "\"";
			}
			else if (req.compareTo("Commission Rate") == 0) {
				query = "SELECT * FROM Salesperson WHERE commission_rate = \"" + value + "\"";
			}
			else if (req.compareTo("") == 0) {
				query = "SELECT * FROM Salesperson";
			}
			rs = statement.executeQuery(query);

			while (rs.next()) {
				int eid = rs.getInt(1);
				String sName = rs.getString(2);
				Date dobirth2 = rs.getDate(3);
				Date employment_date2 = rs.getDate(4);

				String employment_date = employment_date2.toString();
				String dobirth = dobirth2.toString();

				int commission_rate = rs.getInt(5);

				Salesperson new_emp = new Salesperson(eid, sName, dobirth, employment_date, commission_rate);
				list.add(new_emp);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

}


