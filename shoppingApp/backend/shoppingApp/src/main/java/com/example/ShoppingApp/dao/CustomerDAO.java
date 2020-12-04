package com.example.ShoppingApp.dao;

import com.example.ShoppingApp.model.Customer;

public interface CustomerDAO {

	public String registerNewCustomer(Customer customer);
//	public String changeAllAccountInformation(Account account);//accountId
	public Customer printAccountInfo(String accountID);
	public void emptyShoppingCart();
	public void changePassword(String password);
	public void changeAddress(String Address);
	public void changePhone(String Phone);
	
}
