/**
 * 
 */
package com.cisco.teamlunch.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

/**
 * Order class that contains food selections for lunch menu,
 * and a list of available restaurants where the food can be ordered from.
 * 
 * This class has a method to calculate the best match of restaurants
 * for the food order selection, based on rating, maximum quantity of
 * that food type that the restaurant can provide daily.
 * 
 * The method could be extracted outside of the class and it can work
 * with lists of food selections and restaurant list provided as arguments.
 * 
 * @author Jasminka Mancevska
 *
 */
public class Order {
	
	private ArrayList<FoodType> foodSelection = new ArrayList<FoodType>();
	private ArrayList<Restaurant> availableRestaurants = new ArrayList<Restaurant>();
	
	/**
	 * 
	 * @param aFoodType
	 */
	public void addFoodSelection(FoodType aFoodType) {
		// Check if that food type is already in the list
		for (int i = 0; i < foodSelection.size(); i++) {
			if ( foodSelection.get(i).getDescription().equals(aFoodType.getDescription())) {
				return;
			}
		}
		foodSelection.add(aFoodType);
	}

	/**
	 * 
	 * @param aRestaurant
	 */
	public void addRestaurant(Restaurant aRestaurant) {
		// Check if that restaurant is already in the list
		for (int i = 0; i < availableRestaurants.size(); i++) {
			if ( availableRestaurants.get(i).getName().equals(aRestaurant.getName())) {
				return;
			}
		}
		availableRestaurants.add(aRestaurant);
	}
	
	/**
	 * Empty the food and restaurant lists.
	 * 
	 */
	public void clear() {
		foodSelection.clear();
		availableRestaurants.clear();
	}
	
	/**
	 * This method returns a Hashtable of restaurants with the restaurant name as the key.
	 * The Restaurant class is used to specify which food choices and how many meals this
	 * restaurant would provide for the order.
	 * 
	 * @return
	 */
	public Hashtable<String, Restaurant> findBestMatch() {
		Hashtable<String, Restaurant> result = new Hashtable<String, Restaurant>();
		
		// Sort the list of restaurants by rating and the total number of meals.
		Collections.sort(availableRestaurants);
		
		// Go through the food orders and find which restaurant has the quantity based on rating.
		// If the highest rated restaurant cannot fulfil the complete quantity, move to the next restaurant.
		for (int i = 0; i < foodSelection.size(); i++) {
			String food = foodSelection.get(i).getDescription();
			int quantity = foodSelection.get(i).getQuantity();
			
			Restaurant aRestaurant;
			int availableQuantity = 0;
			Restaurant matchedRestaurant = null;
			for (int j = 0; j < availableRestaurants.size(); j++) {
				aRestaurant = availableRestaurants.get(j);
				availableQuantity = aRestaurant.getQuantityForFoodType(food);
				if (availableQuantity != 0) {
					// Check first if that restaurant was already added for previous food selection.
					if (result.containsKey(aRestaurant.getName())) {
						matchedRestaurant = result.get(aRestaurant.getName());
					}
					else {
						matchedRestaurant = new Restaurant(aRestaurant.getName(), aRestaurant.getRating());
					}
					if (availableQuantity >= quantity) {
						matchedRestaurant.addFoodTypes(new FoodType(food, quantity));
						// Since the total number of meals for this food type is completed,
						// add the restaurant and move to the next food type.
						result.put(matchedRestaurant.getName(), matchedRestaurant);
						break;
					}
					else {
						matchedRestaurant.addFoodTypes(new FoodType(food, availableQuantity));
						quantity = quantity - availableQuantity;
					}
				}
				if (matchedRestaurant != null) {
					result.put(matchedRestaurant.getName(), matchedRestaurant);
				}
			}
			
		}
		
		return result;
		
	}

}
