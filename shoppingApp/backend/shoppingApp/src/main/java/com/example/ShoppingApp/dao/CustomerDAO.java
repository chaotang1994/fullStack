package com.example.ShoppingApp.dao;

import com.example.ShoppingApp.exception.EmailNotExits;
import com.example.ShoppingApp.exception.InvalidPasswordOrUsername;
import com.example.ShoppingApp.model.Customer;

public interface CustomerDAO {

	public String registerNewCustomer(Customer customer);
	public String authentication(String username, String password)throws InvalidPasswordOrUsername, EmailNotExits;
	public String getPasswordByEmail(String email)throws EmailNotExits;
//	public String changeAllAccountInformation(Account account);//accountId
//	public Customer customerLogin(String email, String password) throws InvalidPasswordOrUsername;
	public Customer printAccountInfo(String accountID);
	public void emptyShoppingCart();
	public void changePassword(String password);
	public void changeAddress(String Address);
	public void changePhone(String Phone);
	
}
