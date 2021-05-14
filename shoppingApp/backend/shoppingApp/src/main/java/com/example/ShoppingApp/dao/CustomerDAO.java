package com.example.ShoppingApp.dao;

import org.springframework.http.ResponseEntity;

import com.example.ShoppingApp.exception.EmailNotExits;
import com.example.ShoppingApp.exception.InvalidPasswordOrUsername;
import com.example.ShoppingApp.model.Customer;

public interface CustomerDAO {

	public String registerNewCustomer(Customer customer);
	public String authentication(String username, String password)throws InvalidPasswordOrUsername, EmailNotExits;
	public String getPasswordByEmail(String email)throws EmailNotExits;
//	public String changeAllAccountInformation(Account account);//accountId
//	public Customer customerLogin(String email, String password) throws InvalidPasswordOrUsername;
	public Customer getAccountInfo(String accountID);
	public Boolean UpdateUser(Customer customer);
	public String getNameByEmail(String id);
	
}
