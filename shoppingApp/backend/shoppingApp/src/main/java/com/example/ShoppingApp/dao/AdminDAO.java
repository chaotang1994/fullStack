package com.example.ShoppingApp.dao;

import com.example.ShoppingApp.model.Product;

public interface AdminDAO {
	public void changeName(String name);
	public void changePassword(String password);
	public void addProduct(Product product);
	public Product deleteProduct(Product product);
	public Product updateProduct(Product product);
}
