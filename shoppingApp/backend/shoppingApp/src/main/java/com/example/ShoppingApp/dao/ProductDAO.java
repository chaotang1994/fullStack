package com.example.ShoppingApp.dao;

import java.util.List;

import com.example.ShoppingApp.exception.IdNotExits;
import com.example.ShoppingApp.model.Product;

public interface ProductDAO {
	
	public String updateProductName(String name);
	public String updateQuantity(int quantity);
	public String updateCategory(String category);
	public String updateCondition(String condition);
	public List<Product> getProductsFromAdmin(String id) throws Exception;
	public boolean addProductFromAdmin(String admin_name, Product product);
	public List<Product> getProductsFromShoppingCart(String id) throws Exception;
	public int addProductToCustomer(int productID, String id);
	public boolean removeProductFromAdmin(Integer id, String admin_id);
	public boolean editProductFromAdmin(String admin_id, Product product);

}
