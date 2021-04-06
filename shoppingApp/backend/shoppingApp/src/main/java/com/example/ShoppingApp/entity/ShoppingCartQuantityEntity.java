package com.example.ShoppingApp.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.example.ShoppingApp.model.Product;
import com.example.ShoppingApp.model.ShoppingCart;

@Entity
@Table(name="SHOPPING_CART_PRODUCT")
public class ShoppingCartQuantityEntity {
	
	@Id
	@Column(name="shopping_cart_has_product_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn( name = "product_id")
	private ProductEntity productEntity;
	
	@ManyToOne
	@JoinColumn( name = "shopping_cart_id")
	private ShoppingCartEntity shoppingCartEntity;
	
	@Column(name="quantity")
	private int quantity;
	
	
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public ProductEntity getProductEntity() {
		return productEntity;
	}

	public void setProductEntity(ProductEntity productEntity) {
		this.productEntity = productEntity;
	}

	public ShoppingCartEntity getShoppingCartEntity() {
		return shoppingCartEntity;
	}

	public void setShoppingCartEntity(ShoppingCartEntity shoppingCartEntity) {
		this.shoppingCartEntity = shoppingCartEntity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
	
}
