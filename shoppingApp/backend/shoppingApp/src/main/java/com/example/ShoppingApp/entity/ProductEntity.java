package com.example.ShoppingApp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PRODUCT")
public class ProductEntity {
	
	@Id
	@Column(name="PRODUCT_ID")
	private int id;
	
	@Column(name="PRODUCT_NAME")
	private String name;
	
	@Column(name="PRODUCT_CATEGORY")
	private String category;
	
	@Column(name="PRODUCT_QUANTITY")
	private int quantity;
	
	@Column(name="PRODUCT_CONDITION")
	private String condition;
	
	@Column(name="PRODUCT_IMG")
	private String imgURL;
	
	@ManyToOne
    @JoinColumn(name = "SHOPPINGCART_ID")
	private ShoppingCartEntity shoppingCart_id;
	
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
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
	public String getImgURL() {
		return imgURL;
	}
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
	public ShoppingCartEntity getShoppingCart_id() {
		return shoppingCart_id;
	}
	public void setShoppingCart_id(ShoppingCartEntity shoppingCart_id) {
		this.shoppingCart_id = shoppingCart_id;
	}

	
	
	
}
