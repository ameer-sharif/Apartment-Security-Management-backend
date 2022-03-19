package com.cg.aps.exception;

public class GuardSalaryUserIdNotFoundException extends RuntimeException {
	
	private String message;
	
	public  GuardSalaryUserIdNotFoundException()
	{
		
	}
	
	public GuardSalaryUserIdNotFoundException(String message) {
		super(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}