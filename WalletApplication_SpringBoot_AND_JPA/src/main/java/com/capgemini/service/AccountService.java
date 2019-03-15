package com.capgemini.service;

import java.math.BigDecimal;
import java.util.List;

import com.capgemini.bean.Customer;
import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.MobileNumberAlreadyExistsException;
import com.capgemini.exceptions.MobileNumberDoesNotExistException;

public interface AccountService {

	Object showBalance(String mobileNumber) throws MobileNumberDoesNotExistException;

	boolean addCustomer(String mobileNumber, String name, BigDecimal balance) throws MobileNumberAlreadyExistsException;

	Customer deposit(String mobileNumber, BigDecimal amount) throws MobileNumberDoesNotExistException;

	Customer withdraw(String mobileNumber, BigDecimal amount)throws MobileNumberDoesNotExistException,InsufficientBalanceException;

	String fundsTransfer(String sourceMobileNumber, String destMobileNumber, BigDecimal amount)throws MobileNumberDoesNotExistException,InsufficientBalanceException;

	public List<Customer> getALLByName(String name);
	
}