package com.example.ShoppingApp.dao;

import org.springframework.stereotype.Repository;

import com.example.ShoppingApp.model.Product;

@Repository("admin_accountDAO")
public class AdminDAOImpl implements AdminDAO{

	@Override
	public void changeName(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addProduct(Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Product deleteProduct(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product updateProduct(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changePassword(String password) {
		// TODO Auto-generated method stub
		
	}

}
