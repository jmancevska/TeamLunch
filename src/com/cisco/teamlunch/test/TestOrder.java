/**
 * 
 */
package com.cisco.teamlunch.test;

import static org.junit.Assert.*;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cisco.teamlunch.app.FoodDescriptions;
import com.cisco.teamlunch.app.FoodType;
import com.cisco.teamlunch.app.Order;
import com.cisco.teamlunch.app.Restaurant;

/**
 * @author Jasminka Mancevska
 *
 */
public class TestOrder {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testScenario1() {
		System.out.println("Test - Scenario 1");
		
		Order order = new Order();
		
		// Team needs: Total of 50 meals including 5 vegetarians and 7 gluten free.
		FoodType foodToOrder1 = new FoodType(FoodDescriptions.VEGETARIAN.toString(), 5);
		FoodType foodToOrder2 = new FoodType(FoodDescriptions.GLUTEN_FREE.toString(), 7);
		FoodType foodToOrder3 = new FoodType(FoodDescriptions.OTHER.toString(), 38);
		order.addFoodSelection(foodToOrder1);
		order.addFoodSelection(foodToOrder2);
		order.addFoodSelection(foodToOrder3);
		
		// Restaurant A has a rating of 5/5 and can serve 40 meals including 4 vegetarians, 
		// Restaurant B has a rating of 3/5 and can serve 100 meals including 20 vegetarians, and 20 gluten free.
		Restaurant a = new Restaurant("A", 5);
		a.addFoodTypes(new FoodType(FoodDescriptions.VEGETARIAN.toString(), 4));
		a.addFoodTypes(new FoodType(FoodDescriptions.OTHER.toString(), 36));
		
		Restaurant b = new Restaurant("B", 3);
		b.addFoodTypes(new FoodType(FoodDescriptions.VEGETARIAN.toString(), 20));
		b.addFoodTypes(new FoodType(FoodDescriptions.GLUTEN_FREE.toString(), 20));
		b.addFoodTypes(new FoodType(FoodDescriptions.OTHER.toString(), 60));
		
		order.addRestaurant(a);
		order.addRestaurant(b);
		
		System.out.println(order);
		
		Hashtable<String, Restaurant> restaurantMatch = order.findBestMatch();
		
	    Set<String> keys = restaurantMatch.keySet();
	    
	    Iterator<String> itr = keys.iterator();
	 
	    // Expected meal orders: 
	    // Restaurant A (4 vegetarian + 36 others), 
	    // Restaurant B (1 vegetarian + 7 gluten free + 2 others)

	    System.out.println("Result: ");
	    while (itr.hasNext()) { 
	       String restaurantName = itr.next();
	       Restaurant aRestaurant = restaurantMatch.get(restaurantName);
	       System.out.println(aRestaurant);
	       if (restaurantName.equals("A")) {
	    	   assertEquals(4, aRestaurant.getQuantityForFoodType(FoodDescriptions.VEGETARIAN.toString()));
	    	   assertEquals(36, aRestaurant.getQuantityForFoodType(FoodDescriptions.OTHER.toString()));
	       }
	       if (restaurantName.equals("B")) {
	    	   assertEquals(1, aRestaurant.getQuantityForFoodType(FoodDescriptions.VEGETARIAN.toString()));
	    	   assertEquals(7, aRestaurant.getQuantityForFoodType(FoodDescriptions.GLUTEN_FREE.toString()));
	    	   assertEquals(2, aRestaurant.getQuantityForFoodType(FoodDescriptions.OTHER.toString()));
	       }
		}

	}


	@Test
	public void testScenario2() {
		System.out.println("Test - Scenario 2");
		
		Order order = new Order();
		
		// Team needs: Total of 50 meals including 5 vegetarians and 5 gluten free and 5 fish free.
		FoodType foodToOrder1 = new FoodType(FoodDescriptions.VEGETARIAN.toString(), 5);
		FoodType foodToOrder2 = new FoodType(FoodDescriptions.GLUTEN_FREE.toString(), 5);
		FoodType foodToOrder3 = new FoodType(FoodDescriptions.FISH_FREE.toString(), 5);
		FoodType foodToOrder4 = new FoodType(FoodDescriptions.OTHER.toString(), 35);
		order.addFoodSelection(foodToOrder1);
		order.addFoodSelection(foodToOrder2);
		order.addFoodSelection(foodToOrder3);
		order.addFoodSelection(foodToOrder4);
		
		// Restaurant A has a rating of 5/5 and can serve 30 meals including 4 vegetarians, 
		// Restaurant B has a rating of 3/5 and can serve 100 meals including 20 vegetarians, and 20 gluten free.
		// Restaurant C has a rating of 3/5 and can serve 20 meals including 10 fish free.
		Restaurant a = new Restaurant("A", 5);
		a.addFoodTypes(new FoodType(FoodDescriptions.VEGETARIAN.toString(), 4));
		a.addFoodTypes(new FoodType(FoodDescriptions.OTHER.toString(), 26));
		
		Restaurant b = new Restaurant("B", 3);
		b.addFoodTypes(new FoodType(FoodDescriptions.VEGETARIAN.toString(), 20));
		b.addFoodTypes(new FoodType(FoodDescriptions.GLUTEN_FREE.toString(), 20));
		b.addFoodTypes(new FoodType(FoodDescriptions.OTHER.toString(), 60));
		
		Restaurant c = new Restaurant("C", 3);
		c.addFoodTypes(new FoodType(FoodDescriptions.FISH_FREE.toString(), 10));
		c.addFoodTypes(new FoodType(FoodDescriptions.OTHER.toString(), 10));
		
		order.addRestaurant(a);
		order.addRestaurant(b);
		order.addRestaurant(c);
		
		System.out.println(order);
		
		Hashtable<String, Restaurant> restaurantMatch = order.findBestMatch();
		
	    Set<String> keys = restaurantMatch.keySet();
	    
	    Iterator<String> itr = keys.iterator();
	 
	    // Expected meal orders: 
	    // Restaurant A (4 vegetarian + 26 others), 
	    // Restaurant B (1 vegetarian + 5 gluten free + 9 others)
	    // Restaurant C (5 fish free)

	    System.out.println("Result: ");
	    while (itr.hasNext()) { 
	       String restaurantName = itr.next();
	       Restaurant aRestaurant = restaurantMatch.get(restaurantName);
	       System.out.println(aRestaurant);
	       if (restaurantName.equals("A")) {
	    	   assertEquals(4, aRestaurant.getQuantityForFoodType(FoodDescriptions.VEGETARIAN.toString()));
	    	   assertEquals(26, aRestaurant.getQuantityForFoodType(FoodDescriptions.OTHER.toString()));
	       }
	       if (restaurantName.equals("B")) {
	    	   assertEquals(1, aRestaurant.getQuantityForFoodType(FoodDescriptions.VEGETARIAN.toString()));
	    	   assertEquals(5, aRestaurant.getQuantityForFoodType(FoodDescriptions.GLUTEN_FREE.toString()));
	    	   assertEquals(9, aRestaurant.getQuantityForFoodType(FoodDescriptions.OTHER.toString()));
	       }
	       if (restaurantName.equals("C")) {
	    	   assertEquals(5, aRestaurant.getQuantityForFoodType(FoodDescriptions.FISH_FREE.toString()));
	       }
		}

	}

}
