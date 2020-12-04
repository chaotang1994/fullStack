package com.example.ShoppingApp.entity;


import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="SHOPPING_CART")
public class ShoppingCartEntity {
	
	@Id
	@Column(name="SHOPPINGCART_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="TOTAL_PRICE")
	private int totalPrice;
	
	//tax?
	
	@OneToMany(mappedBy="shoppingCart_id", cascade = CascadeType.ALL)//mappedby properties in productEntity's shoppingCart_id
	private Map<Integer,ProductEntity> productList;
//	@JoinColumn(name="productList_ID", referencedColumnName="productList_id")

	
	

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

	public Map<Integer, ProductEntity> getProductList() {
		return productList;
	}

	public void setProductList(Map<Integer, ProductEntity> productList) {
		this.productList = productList;
	}



	

	
	

	
}
