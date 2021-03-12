package com.example.ShoppingApp.model;

import java.util.ArrayList;
import java.util.List;

import com.example.ShoppingApp.entity.ProductEntity;

public class ShoppingCart {
	
	private int id;
	private int totalPrice;
	private List<Product> product= new ArrayList<>();
	
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public List<Product> getProduct() {
		return product;
	}
	public void setProduct(List<Product> product) {
		this.product = product;
	}

	

}
