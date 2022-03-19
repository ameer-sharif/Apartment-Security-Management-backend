package com.cg.aps.exception;

public class GuardShiftUserIdNotFoundException extends RuntimeException{
	
	private String message;
	
	public GuardShiftUserIdNotFoundException()
	{
		
	}
	
	public GuardShiftUserIdNotFoundException(String message) {
		super(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}