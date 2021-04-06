package com.example.ShoppingApp.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ShoppingApp.dao.AdminDAOImpl;
import com.example.ShoppingApp.exception.EmailNotExits;
import com.example.ShoppingApp.exception.InvalidPasswordOrUsername;
import com.example.ShoppingApp.model.Admin;
import com.example.ShoppingApp.model.Product;


@Service
@Transactional
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminDAOImpl admin_accountDAO;
	
	@Override
	public String authentication(String username, String password)  throws InvalidPasswordOrUsername, EmailNotExits{
		return admin_accountDAO.authentication(username, password);

	}
	
	@Override
	public void changeName(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changePassword(String password) {
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
	public ArrayList<Product> getProducts() {
		return admin_accountDAO.getProducts();
	}

	@Override
	public String addProduct(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createAdmin(Admin admin) throws Exception {
		return admin_accountDAO.createAdmin(admin);
	}



}
