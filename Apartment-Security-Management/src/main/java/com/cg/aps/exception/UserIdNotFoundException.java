package com.cg.aps.exception;
public class UserIdNotFoundException extends RuntimeException {

	
	private String user;
	
	public UserIdNotFoundException(){
		
	}

	public UserIdNotFoundException(String user) {
		
		super(user);
	}

}