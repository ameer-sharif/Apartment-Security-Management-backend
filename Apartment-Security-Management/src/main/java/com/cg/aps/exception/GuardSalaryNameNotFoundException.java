package com.cg.aps.exception;

public class GuardSalaryNameNotFoundException extends RuntimeException {
	
	private String message;
	
	public GuardSalaryNameNotFoundException()
	{
		
	}
	
	public GuardSalaryNameNotFoundException(String message) {
		super(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}