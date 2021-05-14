package com.example.ShoppingApp.entity;

import java.sql.Blob;
import java.util.ArrayList;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.sql.rowset.serial.SerialBlob;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="PRODUCT")
public class ProductEntity {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="PRODUCT_NAME")
	private String name;
	
	@Column(name="PRODUCT_CATEGORY")
	private String category;
	
	@Column(name="PRODUCT_QUANTITY")
	private int quantity;
	
	@Column(name="PRODUCT_CONDITION")
	private String condition;
	
	@Column(name="PRODUCT_PRICE")
	private double price;
	
	@Lob
	@Column(name="PRODUCT_IMG",columnDefinition = "LONGBLOB")
	private byte[] imgURL;
	
	@OneToMany(cascade = CascadeType.ALL ,mappedBy = "productEntity")
	private List<ShoppingCartQuantityEntity> ShoppingCartQuantityEntity = new ArrayList<>();
	
	
	@ManyToOne
	@JoinColumn(name="admin_ID")
	private AdminEntity adminEntity;
	
	
	

	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
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
	public byte[] getImgURL() {
		return imgURL;
	}
	public void setImgURL(byte[] imgURL) {
		this.imgURL = imgURL;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

	public AdminEntity getAdminEntity() {
		return adminEntity;
	}
	public void setAdminEntity(AdminEntity adminEntity) {
		this.adminEntity = adminEntity;
	}
	public List<ShoppingCartQuantityEntity> getShoppingCartQuantityEntity() {
		return ShoppingCartQuantityEntity;
	}
	public void setShoppingCartQuantityEntity(List<ShoppingCartQuantityEntity> shoppingCartQuantityEntity) {
		ShoppingCartQuantityEntity = shoppingCartQuantityEntity;
	}



	
	
	
}
