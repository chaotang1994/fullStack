package com.example.ShoppingApp.dao;

public interface ProductDAO {
	
	public String updateProductName(String name);
	public String updateQuantity(int quantity);
	public String updateCategory(String category);
	public String updateCondition(String condition);

}
