package model;
import javafx.beans.property.SimpleStringProperty;

public class Sale {
	private final SimpleStringProperty s_id;
	private final SimpleStringProperty sale_date;
	private final SimpleStringProperty amount;
	private final SimpleStringProperty eid;
	private final SimpleStringProperty cid;
	private final SimpleStringProperty commission_amount;
	private final SimpleStringProperty cust_id;



	public Sale (int s_id, String sale_date, int amount, int eid, int cid, int commission_amount, int cust_id) {
		this.s_id = new SimpleStringProperty(Integer.toString(s_id));
		this.sale_date = new SimpleStringProperty(sale_date);
		this.amount = new SimpleStringProperty(Integer.toString(amount));
		this.eid = new SimpleStringProperty(Integer.toString(eid));
		this.cid = new SimpleStringProperty(Integer.toString(cid));
		this.commission_amount = new SimpleStringProperty(Integer.toString(commission_amount));
		this.cust_id = new SimpleStringProperty(Integer.toString(cust_id));
	}

	public String getS_ID() {
		return s_id.get();
	}

	public String getSale_Date() {
		return sale_date.get();
	}

	public String getAmount() {
		return amount.get();
	}

	public String getEid() {
		return eid.get();
	}

	public String getCid() {
		return cid.get();
	}

	public String getCommission_Amount() {
		return commission_amount.get();
	}

	public String getCust_ID() {
		return cust_id.get();
	}

}