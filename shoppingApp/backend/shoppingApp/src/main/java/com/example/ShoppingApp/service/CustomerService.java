package com.example.ShoppingApp.service;

import com.example.ShoppingApp.exception.EmailNotExits;
import com.example.ShoppingApp.exception.InvalidPasswordOrUsername;
import com.example.ShoppingApp.model.Customer;

public interface CustomerService {
	
	public String registerNewCustomer(Customer customer);
	public String authentication(String username, String password)throws InvalidPasswordOrUsername, EmailNotExits;
	public Customer printAccountInfo(String accountID);
//	public Customer customerLogin(String email, String password);
	public Boolean UpdateUser(Customer customer);
	
}
