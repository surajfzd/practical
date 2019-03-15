package com.capgemini.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.bean.Customer;
import com.capgemini.bean.Wallet;
import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.MobileNumberAlreadyExistsException;
import com.capgemini.exceptions.MobileNumberDoesNotExistException;
import com.capgemini.service.AccountService;

@RestController
public class ApplicationController {
	
	@Autowired
	static AccountService service;
	
	static {
		
		try {
			service.addCustomer("8009834384", "Sagar",new BigDecimal(52));
		} catch (MobileNumberAlreadyExistsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			service.addCustomer("8896638031", "Arrow", new BigDecimal(52));
		} catch (MobileNumberAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	@RequestMapping("/getByName/{name}")
	public List<Customer> getByName(@PathVariable String name){
		
		return service.getALLByName(name);
		
	}
	
	@RequestMapping("/getBalance/{mobile}")
	public Customer getBalance(@PathVariable String mobile) {
		
		try {
			
			return (Customer)service.showBalance(mobile);
			
		} 
		catch (MobileNumberDoesNotExistException e) {
		
			System.out.println(e.getMessage());
		
		}
		return null;
	
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/addCustomer/{mobile}/{name}/{balance}")
	
	public void addCustomer(@PathVariable String mobile,@PathVariable String name,@PathVariable BigDecimal balance) {
		
		try {
			service.addCustomer(mobile, name, balance);
		} 
		catch (MobileNumberAlreadyExistsException e) {
			
			System.out.println(e.getMessage());
		}
		
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/deposit/{mobile}/{amount}")
	
	public Customer deposit(@PathVariable String mobile, @PathVariable BigDecimal amount) {
		
		try {
		
			return service.deposit(mobile, amount);
		}
		catch (MobileNumberDoesNotExistException e) {

			System.out.println(e.getMessage());
			
		}
		return null;
		
	}
	
@RequestMapping(method=RequestMethod.PUT,value="/withdraw/{mobile}/{amount}")
	
	public Customer withdraw(@PathVariable String mobile, @PathVariable BigDecimal amount) {
		
		try {
			
			return service.withdraw(mobile, amount);
			
		}
		catch (MobileNumberDoesNotExistException | InsufficientBalanceException e) {

			System.out.println(e.getMessage());
			
		}
		return null;
		
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/fundsTransfer/{sourceMobileNumber}/{destMobileNumber}/{amount}")
	public String fundsTransfer(@PathVariable String sourceMobileNumber,@PathVariable String destMobileNumber,@PathVariable BigDecimal amount) {
		
		try {
			
			return service.fundsTransfer(sourceMobileNumber, destMobileNumber, amount);
			
		} 
		catch (MobileNumberDoesNotExistException | InsufficientBalanceException e) {

			System.out.println(e.getMessage());

		}
		return destMobileNumber;
		
	}
	

}
