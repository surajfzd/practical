package com.capgemini.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capgemini.bean.Customer;
import com.capgemini.bean.Wallet;
import com.capgemini.exceptions.MobileNumberAlreadyExistsException;
import com.capgemini.exceptions.MobileNumberDoesNotExistException;
import com.capgemini.repository.AccountRepo;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepo repo;
	
	@Override
	public Object showBalance(String mobileNumber)throws MobileNumberDoesNotExistException {
		
		if(repo.existsById(mobileNumber))
		return repo.findById(mobileNumber).get();
		throw new MobileNumberDoesNotExistException();
		
	}
	
	@Override
	public boolean addCustomer(String mobileNumber,String name, BigDecimal balance) throws MobileNumberAlreadyExistsException{
		
		if(repo.existsById(mobileNumber))
			throw new MobileNumberAlreadyExistsException();
		
		Wallet wallet= new Wallet();
		Customer customer= new Customer();
		wallet.setBalance(balance);
		customer.setMobileNumber(mobileNumber);
		customer.setName(name);
		customer.setWallet(wallet);
		if(repo.save(customer)!=null)
			return true;
		return false;
		
	}
	
	@Override
	public Customer deposit(String mobileNumber, BigDecimal amount) {
		
		Customer customer=(Customer)repo.findById(mobileNumber).get();
		customer.getWallet().setBalance(customer.getWallet().getBalance().add(amount));
		repo.save(customer);
		return (Customer)repo.findById(mobileNumber).get();
	}
	

	@Override
	public Customer withdraw(String mobileNumber, BigDecimal amount) {
		
		Customer customer=(Customer)repo.findById(mobileNumber).get();
		customer.getWallet().setBalance(customer.getWallet().getBalance().subtract(amount));
		repo.save(customer);
		return (Customer)repo.findById(mobileNumber).get();
	}
	
	@Override
	public String fundsTransfer(String sourceMobileNumber, String destMobileNumber, BigDecimal amount) {
		
		Customer source= repo.findById(sourceMobileNumber).get();
		Customer dest= repo.findById(destMobileNumber).get();
		source.getWallet().setBalance(source.getWallet().getBalance().add(amount));
		repo.save(source);
		dest.getWallet().setBalance(dest.getWallet().getBalance().subtract(amount));
		repo.save(dest);
		
		return "Balance after Deduction and Transfer is respectively:"+repo.findById(sourceMobileNumber).get().getWallet().getBalance()+","+repo.findById(destMobileNumber).get().getWallet().getBalance();
		
	}

	@Override
	public List<Customer> getALLByName(String name) {
		
		return repo.findByName(name);
		
	}

	
}
