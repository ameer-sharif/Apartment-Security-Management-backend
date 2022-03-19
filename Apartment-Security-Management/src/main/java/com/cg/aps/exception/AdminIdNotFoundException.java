package com.cg.aps.exception;
public class AdminIdNotFoundException extends RuntimeException {

	
	private String admin;
	
	public AdminIdNotFoundException(){
		
	}

	public AdminIdNotFoundException(String admin) {
		
		super(admin);
	}

}