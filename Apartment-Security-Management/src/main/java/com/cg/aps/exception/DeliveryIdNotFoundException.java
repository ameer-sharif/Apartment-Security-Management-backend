package com.cg.aps.exception;

public class DeliveryIdNotFoundException extends RuntimeException {
	private String delivery;

	public DeliveryIdNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public DeliveryIdNotFoundException(String delivery) {
		super(delivery);
	}


}