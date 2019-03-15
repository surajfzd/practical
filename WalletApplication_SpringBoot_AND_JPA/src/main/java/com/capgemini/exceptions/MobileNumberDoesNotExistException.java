package com.capgemini.exceptions;

@SuppressWarnings("serial")
public class MobileNumberDoesNotExistException extends Exception {
	
	
	@Override
	public String getMessage() {
	
		return "Mobile Number Does not Exist!";
		
	}

	
}
