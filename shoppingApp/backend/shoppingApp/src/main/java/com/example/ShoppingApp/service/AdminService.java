package com.example.ShoppingApp.service;
import java.util.ArrayList;

import com.example.ShoppingApp.exception.EmailNotExits;
import com.example.ShoppingApp.exception.InvalidPasswordOrUsername;
import com.example.ShoppingApp.model.Admin;
import com.example.ShoppingApp.model.Product;

public interface AdminService {
	
	public void changeName(String name);
	public void changePassword(String password);
	public String addProduct(Product product);
	public Product deleteProduct(Product product);
	public Product updateProduct(Product product);
	public ArrayList<Product> getProducts();
	public Admin addProductAndAdmin(Admin admin);
	public String createAdmin(Admin admin) throws Exception;
	public String authentication(String username, String password) throws InvalidPasswordOrUsername, EmailNotExits;

}
