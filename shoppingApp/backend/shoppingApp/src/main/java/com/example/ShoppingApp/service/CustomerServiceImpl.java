package com.example.ShoppingApp.service;


import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.ShoppingApp.dao.CustomerDAO;
import com.example.ShoppingApp.exception.EmailNotExits;
import com.example.ShoppingApp.exception.InvalidPasswordOrUsername;
import com.example.ShoppingApp.model.Customer;
import com.example.ShoppingApp.security.PasswordEncode;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{
	
	
	@Autowired
	private CustomerDAO customerDAO;
	
	
	
	@Override
	public String registerNewCustomer(Customer customer) {
		
		return customerDAO.registerNewCustomer(customer);
	}
	
	
//	public Customer customerLogin(String email, String password) {
//		try {
//			return customerDAO.customerLogin(email, password);
//		} catch (InvalidPasswordOrUsername e) {
//			System.out.println(e.getMessage());
//		}
//		return null;
//	}

	@Override
	public Customer getAccountInfo(String accountID) {
		// TODO Auto-generated method stub
		System.out.println("Service");
		return customerDAO.getAccountInfo(accountID);
	}


	@Override
	public String authentication(String username, String password) throws InvalidPasswordOrUsername, EmailNotExits {
		return customerDAO.authentication(username, password);
	}

	
	public Boolean UpdateUser(Customer customer) {
		return customerDAO.UpdateUser(customer);
	}


	@Override
	public String getNameByEmail(String id) {
		return customerDAO.getNameByEmail(id);
	}

	
	
}
