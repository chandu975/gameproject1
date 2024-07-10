package com.dao;

import com.model.Booking;

public class HistoryDao extends Booking {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String transId;

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

}
