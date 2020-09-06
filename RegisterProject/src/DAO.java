import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

/**
 * @author Nathan Mingoube
 * @company PleaseGiveMeAJob Inc.
 * @date 22/07/2020
 * @Title Dish class
 * @version 0.1
 *
 */

public class DAO {
	Connection conn;
	private String adress;
	private String username;
	private String password;
	public DAO(String argAd, String argUname, String argPwrd) {
		this.adress=argAd;
		this.username = argUname;
		this.password = argPwrd;
		try {
			conn = DriverManager.getConnection(adress,username,password);
		}catch(Exception e) {e.getMessage();}
		
	
	
	}
	
	
	public ArrayList<Dish> requestDish(String req){
		ArrayList<Dish> res = new ArrayList<Dish>();
		
	}
	
	public ArrayList<Item> requestItem(String req){
		ArrayList<Item> res = new ArrayList<Item>();
	}
	
	public ArrayList<Order> requestOrder(String req){
		ArrayList<Order> res = new ArrayList<Order>();
	}
	
	public ArrayList<Delivery> requestDelivery(String req){
		ArrayList<Delivery> res = new ArrayList<Delivery>();
	}
	
	public ArrayList<Quantity> requestQuantity(String req){
		ArrayList<Quantity> res = new ArrayList<Quantity>();
	}
	
}
