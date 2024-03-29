package com.example.ShoppingApp.service;

import org.springframework.http.ResponseEntity;

import com.example.ShoppingApp.exception.EmailNotExits;
import com.example.ShoppingApp.exception.InvalidPasswordOrUsername;
import com.example.ShoppingApp.model.Customer;

public interface CustomerService {
	
	public String registerNewCustomer(Customer customer);
	public String authentication(String username, String password)throws InvalidPasswordOrUsername, EmailNotExits;
	public Customer getAccountInfo(String accountID);
//	public Customer customerLogin(String email, String password);
	public Boolean UpdateUser(Customer customer);
	public String getNameByEmail(String id);
	
}
