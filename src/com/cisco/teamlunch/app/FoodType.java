/**
 * 
 */
package com.cisco.teamlunch.app;

/**
 * Class representing a food type/selection together with the serving quantity. 
 * 
 * @author Jasminka Mancevska
 *
 */
public class FoodType {
	
	private String description;
	private int quantity;
	
	public FoodType(String aDescription, int aQuantity) {
		description = aDescription;
		quantity = aQuantity;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * 
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FoodType [Description=" + getDescription()
				+ ", Quantity=" + getQuantity() + "]";
	}
	
	

}
