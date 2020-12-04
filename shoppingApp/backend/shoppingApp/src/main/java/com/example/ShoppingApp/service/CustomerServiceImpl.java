package com.example.ShoppingApp.service;


import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ShoppingApp.dao.CustomerDAO;
import com.example.ShoppingApp.model.Customer;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{
	
	
	@Autowired
	private CustomerDAO customerDAO;
	
	
	@Override
	public String registerNewCustomer(Customer customer) {
		return customerDAO.registerNewCustomer(customer);
	}

	@Override
	public Customer printAccountInfo(String accountID) {
		// TODO Auto-generated method stub
		System.out.println("Service");
		return customerDAO.printAccountInfo(accountID);
	}

	
	
	
}
