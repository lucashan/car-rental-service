package model;

import javafx.beans.property.SimpleStringProperty;

public class Car_Inventory {
	private final SimpleStringProperty inventoryID;
	private final SimpleStringProperty model;
	private final SimpleStringProperty make;
	private final SimpleStringProperty year;
	private final SimpleStringProperty car_condition;
	private final SimpleStringProperty colors;
	private final SimpleStringProperty miles;
	private final SimpleStringProperty price;
	private final SimpleStringProperty car_type;
	private final SimpleStringProperty sold_bit;

	public Car_Inventory(int inventoryID, String model, String make, int year, String car_condition,
						  String colors, int miles, int price, String car_type, int sold_bit) {
		this.inventoryID = new SimpleStringProperty(Integer.toString(inventoryID));
		this.model = new SimpleStringProperty(model);
		this.make = new SimpleStringProperty(make);
		this.year = new SimpleStringProperty(Integer.toString(year));
		this.car_condition = new SimpleStringProperty(car_condition);
		this.colors = new SimpleStringProperty(colors);
		this.miles = new SimpleStringProperty(Integer.toString(miles));
		this.price = new SimpleStringProperty(Integer.toString(price));
		this.car_type = new SimpleStringProperty(car_type);
		this.sold_bit = new SimpleStringProperty(Integer.toString(sold_bit));
	}

	public String getInventoryId() {
		return inventoryID.get();
	}
	public String getModel() {
		return model.get();
	}
	public String getMake() {
		return make.get();
	}
	public String getYear() {
		return year.get();
	}
	public String getCarCondition() {
		return car_condition.get();
	}
	public String getColors() {
		return colors.get();
	}
	public String getMiles() {
		return miles.get();
	}
	public String getPrice() {
		return price.get();
	}
	public String getCarType() {
		return car_type.get();
	}
	public String getSoldBit() {
		return sold_bit.get();
	}


}