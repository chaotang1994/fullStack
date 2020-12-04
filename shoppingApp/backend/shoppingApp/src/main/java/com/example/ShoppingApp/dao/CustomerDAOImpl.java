package com.example.ShoppingApp.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.example.ShoppingApp.entity.CustomerEntity;
import com.example.ShoppingApp.entity.ShoppingCartEntity;
import com.example.ShoppingApp.model.Customer;


@Repository("customerDAO")
public class CustomerDAOImpl implements CustomerDAO{

	@PersistenceContext
	private EntityManager entityManager;

	
	@Override
	public String registerNewCustomer(Customer customer) {
		
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setEmailID(customer.getEmailID());
		customerEntity.setPassword(customer.getPassword());
		customerEntity.setFirstName(customer.getFirstName());
		customerEntity.setLastName(customer.getLastName());
		customerEntity.setAddress(customer.getAddress());
		customerEntity.setCity(customer.getCity());
		customerEntity.setState(customer.getState());
		customerEntity.setZipCode(customer.getZipCode());
		customerEntity.setPhoneNumber(customer.getPhoneNumber());
		entityManager.persist(customerEntity);
		return customerEntity.getEmailID();
	}

	@Override
	public Customer printAccountInfo(String accountID) {
//		entityManager.find(Account.class, accountID);
		System.out.println("printAccountInfo");
		Customer customer=null;
		CustomerEntity customerEntity = entityManager.find(CustomerEntity.class, accountID);
		if(customerEntity !=null) {
			customer = new Customer();
			customer.setEmailID(customerEntity.getEmailID());
			customer.setAddress(customerEntity.getAddress());
			customer.setFirstName(customerEntity.getFirstName());
			customer.setLastName(customerEntity.getLastName());
			customer.setPhoneNumber(customerEntity.getPhoneNumber());
			customer.setShoppingCart_id(null);
		}
		return customer;
	}

	@Override
	public void emptyShoppingCart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changePassword(String password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeAddress(String Address) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changePhone(String Phone) {
		// TODO Auto-generated method stub
		
	}



}
