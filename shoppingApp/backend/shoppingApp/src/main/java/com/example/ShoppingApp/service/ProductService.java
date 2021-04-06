package com.example.ShoppingApp.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.example.ShoppingApp.exception.IdNotExits;
import com.example.ShoppingApp.model.Product;

public interface ProductService {
	public List<Product> getProductsFromAdmin(String id) throws Exception;
	public List<Product>getProductsFromShoppingCart(String id) throws Exception;
	public int addProductToCustomer(Product product, String id);
	public boolean removeProductFromAdmin(Integer id,String admin_id);
	public boolean editProductFromAdmin(String admin_id, Product product);
	public boolean addProductFromAdmin(String admin_name, Product product) throws UnsupportedEncodingException;
	public int removeProductFromCustomer(String id, int product_id);
	public Integer updateQuantityFromUser(String user_id, Product prodcut);
}
