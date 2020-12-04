package com.example.ShoppingApp.dao;



import java.util.HashMap;

import com.example.ShoppingApp.model.Product;

public interface ShoppingCartDAO {
	
	public String addProduct(int product_id);
	public String deleteProduct(int product_id);
	public String updateProduct(int product_id);
	public HashMap<Integer,Product> printProductList();
}
