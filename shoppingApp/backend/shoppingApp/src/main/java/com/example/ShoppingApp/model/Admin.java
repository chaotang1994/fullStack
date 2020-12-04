package com.example.ShoppingApp.model;

import java.util.HashMap;

public class Admin {
	
	private String name;
	private String username;
	private String password;
	private HashMap<Integer,Product> productList;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public HashMap<Integer, Product> getProductList() {
		return productList;
	}
	public void setProductList(HashMap<Integer, Product> productList) {
		this.productList = productList;
	}

	
	 

}
