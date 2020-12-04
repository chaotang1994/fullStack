package com.example.ShoppingApp.service;

import com.example.ShoppingApp.model.Customer;

public interface CustomerService {
	
	public String registerNewCustomer(Customer customer);
	public Customer printAccountInfo(String accountID);

}
