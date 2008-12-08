package com.themangoproject.db.h2;

public class DBListMovie extends DBMovie {
	
	private int orderId = -1;
	
	public DBListMovie() {
		super();
	}
	
	/**
	 * Set the order id for the movie.
	 * 
	 * @param orderId
	 *            The order in the list the movie is a part of.
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return The order id for the movie (if created by in the list DAO) or -1
	 *         if it has no order id.
	 */
	public int getOrderId() {
		return orderId;
	}
	
	
}
