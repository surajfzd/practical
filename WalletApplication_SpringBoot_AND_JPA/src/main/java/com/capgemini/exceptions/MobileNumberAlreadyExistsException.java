package com.capgemini.exceptions;

@SuppressWarnings("serial")
public class MobileNumberAlreadyExistsException extends Exception {
	@Override
	public String getMessage() {

		return "Mobile number already exists!";
	}
	
	

}
