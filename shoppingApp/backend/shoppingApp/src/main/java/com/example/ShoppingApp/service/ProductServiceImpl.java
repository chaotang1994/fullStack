package com.example.ShoppingApp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ShoppingApp.dao.ProductDAO;
import com.example.ShoppingApp.exception.IdNotExits;
import com.example.ShoppingApp.model.Product;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductDAO productDAO;

	@Override
	public List<Product> getProductsFromAdmin(String id) throws Exception {
		return productDAO.getProductsFromAdmin(id);
	}

	@Override
	public List<Product> getProductsFromShoppingCart(String id) throws Exception {
		return productDAO.getProductsFromShoppingCart(id);
	}

	@Override
	public int addProductToCustomer(int productID, String id) {
		return productDAO.addProductToCustomer(productID, id);
	}
	
	@Override
	public boolean removeProductFromAdmin(Integer id,String admin_id)  {
		return productDAO.removeProductFromAdmin(id, admin_id);
	}

	@Override
	public boolean editProductFromAdmin(String admin_id, Product product) {
		return productDAO.editProductFromAdmin(admin_id,product);
	}


	@Override
	public boolean addProductFromAdmin(String admin_id, Product product) {
		return productDAO.addProductFromAdmin(admin_id, product);
	}

}
