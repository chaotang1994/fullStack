package com.example.ShoppingApp.dao;

import java.util.ArrayList;

import com.example.ShoppingApp.exception.EmailNotExits;
import com.example.ShoppingApp.exception.InvalidPasswordOrUsername;
import com.example.ShoppingApp.model.Admin;
import com.example.ShoppingApp.model.Product;

public interface AdminDAO {
//	public void changeName(String name);
//	public void changePassword(String password);
	public ArrayList<Product> getProducts();
//	public Product deleteProduct(Product product);
//	public Product updateProduct(Product product);
	public String createAdmin(Admin admin) throws Exception;
	public String authentication(String username, String password) throws InvalidPasswordOrUsername, EmailNotExits;
	String getPasswordByEmail(String email) throws EmailNotExits;

}
