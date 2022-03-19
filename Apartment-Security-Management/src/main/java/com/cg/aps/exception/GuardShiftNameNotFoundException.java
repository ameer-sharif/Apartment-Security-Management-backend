package com.cg.aps.exception;

public class GuardShiftNameNotFoundException extends RuntimeException {
	
	private String message;
	
	public GuardShiftNameNotFoundException()
	{
		
	}
	
	public GuardShiftNameNotFoundException(String message) {
		super(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}