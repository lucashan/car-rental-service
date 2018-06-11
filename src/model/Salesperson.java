package model;
import javafx.beans.property.SimpleStringProperty;
public class Salesperson {
	private final SimpleStringProperty eid;
	private SimpleStringProperty sName;
	private SimpleStringProperty dobirth;
	private SimpleStringProperty employment_date;
	private final SimpleStringProperty commission_rate;

	public Salesperson(int eid, String sName, String dobirth, String employment_date, int commission_rate) {
		this.eid = new SimpleStringProperty(Integer.toString(eid));
		this.sName = new SimpleStringProperty(sName);
		this.dobirth = new SimpleStringProperty(dobirth);
		this.employment_date = new SimpleStringProperty(employment_date);
		this.commission_rate = new SimpleStringProperty(Integer.toString(commission_rate));
	}

	public String getEid(){
		return eid.get();
	}

	public String getSName(){
		return sName.get();
	}

	public String getDobirth(){
		return dobirth.get();
	}

	public String getEmployment_Date(){
		return employment_date.get();
	}

	public String getCommission_Rate(){
		return commission_rate.get();
	}

}