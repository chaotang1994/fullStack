package com.example.ShoppingApp.entity;




import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="SHOPPING_CART")
public class ShoppingCartEntity {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="TOTAL_PRICE")
	private int totalPrice;
	
	//tax?
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinTable(name="shopping_cart_has_product",
	        joinColumns = @JoinColumn(name = "shopping_cart_id", referencedColumnName = "ID"),
	        inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "ID"))
	private List<ProductEntity> productEntity = new ArrayList<>();

	
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

	public List<ProductEntity> getProductEntity() {
		return productEntity;
	}

	public void setProductEntity(List<ProductEntity> productEntity) {
		this.productEntity = productEntity;
	}




	

	
	

	
}
