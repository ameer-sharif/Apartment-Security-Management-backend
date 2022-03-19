package com.cg.aps.exception;



public class MessageIdNotFoundException extends RuntimeException{

	private String message;

	public MessageIdNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public MessageIdNotFoundException(String message) {
		super(message);
	}


}