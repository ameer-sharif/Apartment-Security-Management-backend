package com.cg.aps.exception;

public class GuardTrainingNameNotFoundException extends RuntimeException{
	
	private String message;
	
	public GuardTrainingNameNotFoundException()
	{
		
	}
	
	

	public GuardTrainingNameNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}
	
	

}