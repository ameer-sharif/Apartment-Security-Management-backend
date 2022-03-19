package com.cg.aps.exception;

public class GuardTrainingUserIdNotFoundException extends RuntimeException {
	
	private String message;
	
	public GuardTrainingUserIdNotFoundException()
	{
		
	}
	
	public GuardTrainingUserIdNotFoundException(String message) {
		super(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}