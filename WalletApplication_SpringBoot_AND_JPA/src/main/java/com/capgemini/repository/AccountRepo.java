package com.capgemini.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.bean.Customer;

@Repository
public interface AccountRepo extends CrudRepository<Customer, String> {

	List<Customer> findByName(String name);
}



