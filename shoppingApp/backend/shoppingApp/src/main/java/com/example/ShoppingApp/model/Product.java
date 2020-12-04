package com.example.ShoppingApp.model;

public class Product {
	
	private int id;
	private String name;
	private String category;
	private int quantity;
	private String condition;
	private String imgURL;
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getimgURL() {
		return imgURL;
	}
	public void setUrl(String imgURL) {
		this.imgURL = imgURL;
	}
	
	
	

}
