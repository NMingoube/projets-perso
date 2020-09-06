import java.util.ArrayList;

/**
 * @author Nathan Mingoube
 * @company PleaseGiveMeAJob Inc.
 * @date 22/07/2020
 * @Title Dish class
 * @version 0.1
 *
 */
public class Dish extends Item{
	protected int numIngredient;
	protected ArrayList<Item> ingredientList;
	protected ArrayList<Quantity> ingredientQty;
	
	public Dish(String argName, int argPrice ) {
		super(argName, argPrice, null);
		
		ingredientList =  new ArrayList<Item>();
		ingredientQty = new ArrayList<Quantity>();
	}
	
	public int remaining() {/** #TODO */ return 0;}
}
