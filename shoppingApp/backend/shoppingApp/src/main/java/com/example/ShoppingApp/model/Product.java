package com.example.ShoppingApp.model;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.ShoppingApp.entity.AdminEntity;
import com.example.ShoppingApp.entity.ShoppingCartEntity;

public class Product {
	
	private int id;
	private String name;
	private String category;
	private int quantity;
	private String condition;
	private double price;
	private byte[] imgURL;

	private List<ShoppingCartQuantityEntity> ShoppingCartQuantityEntity = new ArrayList<>();

	private AdminEntity adminEntity;


	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
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

	public byte[] getImgURL() {
		return imgURL;
	}
	public void setImgURL(byte[] imgURL) {
		this.imgURL = imgURL;
	}
	public List<ShoppingCartQuantityEntity> getShoppingCartQuantityEntity() {
		return ShoppingCartQuantityEntity;
	}
	public void setShoppingCartQuantityEntity(List<ShoppingCartQuantityEntity> shoppingCartQuantityEntity) {
		ShoppingCartQuantityEntity = shoppingCartQuantityEntity;
	}
	public AdminEntity getAdminEntity() {
		return adminEntity;
	}
	public void setAdminEntity(AdminEntity adminEntity) {
		this.adminEntity = adminEntity;
	}

	

}
