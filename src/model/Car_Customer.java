package model;

import javafx.beans.property.SimpleStringProperty;

public class Car_Customer {
	private final SimpleStringProperty custID;
	private final SimpleStringProperty cName;
	private final SimpleStringProperty phone;
	private final SimpleStringProperty address;

	public Car_Customer(int custID, String cName, String phone, String address) {
		this.address = new SimpleStringProperty(address);
		this.phone = new SimpleStringProperty(phone);
		this.cName = new SimpleStringProperty(cName);
		this.custID = new SimpleStringProperty(Integer.toString(custID));
	}

	public String getAddress() {
		return address.get();
	}


	public String getPhone() {
		return phone.get();
	}

	public String getCName() {
		return cName.get();
	}


	public String getCustID() {
		return custID.get();
	}
}