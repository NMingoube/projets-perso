import java.util.ArrayList;

/**
 * @author Nathan Mingoube
 * @company PleaseGiveMeAJob Inc.
 * @date 22/07/2020
 * @Title Item class
 * @version 0.1
 *
 */

public class Item {
	public int id;
	public String name;
	
	protected int price;
	protected Quantity qty;
	protected Delivery source;
	protected ArrayList<Quantity> qtyHistory;
	
	public Item(String argName, int argPrice, Delivery argSource) {
		this.name = argName;
		this.price = argPrice;
		this.source = argSource;
		
		attributeId();
		
	}
	
	protected void attributeId() {/** #TODO*/}
	
	public int getPrice() {
		return this.price;
	}
	
	public Quantity getQuantity() {
		return this.qty;
	}
	
	public Delivery getSource() {
		return this.source;
	}
}
