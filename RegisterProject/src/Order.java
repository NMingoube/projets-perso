import java.util.ArrayList;

/**
 * @author Nathan Mingoube
 * @company PleaseGiveMeAJob Inc.
 * @date 22/07/2020
 * @Title Order class
 * @version 0.1
 *
 */
public class Order {
	protected int table;
	protected int peopleCount;
	protected ArrayList<Dish> dishes;
	
	public Order(int argTable, int argPplCount) {
		this.table = argTable;
		this.peopleCount = argPplCount;
		
		dishes = new ArrayList<Dish>();
		
	}
	
	public int addDish(Dish newDish) {
		dishes.add(newDish);
		
		return getPrice();
	}
	
	public int removeDish(Dish oldDish) {
		if(dishes.contains(oldDish)) {
			dishes.remove(oldDish);
			return getPrice();
		}
		else {
			return -1;
		}
		
	}
	
	public int getPrice() {
		int total = 0;
		for(Dish dish : dishes) {
			total+=dish.getPrice();
			
		}
		return total;
	}
}
