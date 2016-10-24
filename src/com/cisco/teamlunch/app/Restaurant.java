/**
 * 
 */
package com.cisco.teamlunch.app;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

/**
 * Class representing a restaurant that has a rating and different food types 
 * which it can serve daily.
 *
 * @author Jasminka Mancevska
 * 
 */
public class Restaurant implements Comparable<Restaurant> {
	
	private String name;
	private int rating;
	private int totalNumOfMeals = 0;
	// Using Hashtable so that there is no duplication of FoodTypes in the collection
	private Hashtable<String, FoodType> foodTypes = new Hashtable<String, FoodType>();
	
	public Restaurant(String aName, int aRating) {
		name = aName;
		rating = aRating;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * 
	 * @param rating
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}
	

	/**
	 * @return the totalNumOfMeals
	 */
	public int getTotalNumOfMeals() {
		return totalNumOfMeals;
	}

	/**
	 * Adds a food type to the menu, with a quantity of meals it can serve for the day.
	 * 
	 * @param aFoodType
	 */
	public void addFoodTypes(FoodType aFoodType) {
		foodTypes.put(aFoodType.getDescription(), aFoodType);
		totalNumOfMeals = totalNumOfMeals + aFoodType.getQuantity();
	}

	/**
	 * Returns the maximum of meals the restaurant can serve for that day.
	 * 
	 * @param aDescription
	 * @return
	 */
	public int getQuantityForFoodType(String aDescription) {
		int q = 0;
		if (aDescription != null) {
			FoodType aFood = foodTypes.get(aDescription);
			if (aFood != null) {
				q= aFood.getQuantity();
			}
		}
		return q;
	}
	
	@Override
	public int compareTo(Restaurant otherRestaurant) {
		// Descending order. Higher rating has precedence.
		// For same rating, bigger total number of meals takes precedence.
		if (getRating() == otherRestaurant.getRating()) {
			return Integer.compare(otherRestaurant.getTotalNumOfMeals(), this.getTotalNumOfMeals());
		}
		else {
			return Integer.compare(otherRestaurant.getRating(), this.getRating());
		}
	}

	@Override
	public String toString() {
		String s = "Name: " + getName() + "\n";
		s = s + "Rating: " + rating + "\n";
	    Set<String> keys = foodTypes.keySet();
	    
	    Iterator<String> itr = keys.iterator();
	 
	    while (itr.hasNext()) { 
	       String key = itr.next();
	       FoodType aFoodType = foodTypes.get(key);
	       s = s + "Food Type: " + aFoodType.getDescription() + ", quantity: " + aFoodType.getQuantity() + "\n";
	    }
		
		return s;
	}
	
}
