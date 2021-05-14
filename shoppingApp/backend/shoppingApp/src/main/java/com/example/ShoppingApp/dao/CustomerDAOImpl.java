package com.example.ShoppingApp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.example.ShoppingApp.entity.CustomerEntity;
import com.example.ShoppingApp.entity.ProductEntity;
import com.example.ShoppingApp.entity.ShoppingCartEntity;
import com.example.ShoppingApp.exception.EmailNotExits;
import com.example.ShoppingApp.exception.InvalidPasswordOrUsername;
import com.example.ShoppingApp.model.Customer;
import com.example.ShoppingApp.model.ShoppingCart;
import com.example.ShoppingApp.security.PasswordEncode;


@Repository("customerDAO")
public class CustomerDAOImpl implements CustomerDAO{

	@PersistenceContext
	private EntityManager entityManager;

	
	private PasswordEncode encode = new PasswordEncode();
	
	
	
	@Override
	public String authentication(String username, String password) throws InvalidPasswordOrUsername, EmailNotExits {
		try {
			String pWord=this.getPasswordByEmail(username);
			if(encode.passwordVerification(pWord,password)){
				return username;
			}else {
				throw new InvalidPasswordOrUsername("Invalid username or password");
			}
			
		} catch(InvalidPasswordOrUsername e) {
			throw new InvalidPasswordOrUsername("Invalid username or password");
		}catch(EmailNotExits e) {
			throw new EmailNotExits("Email does not exits");
		}
	}
	
	@Override
	public String registerNewCustomer(Customer customer){
			
		CustomerEntity customerEntity;
		try {
			customerEntity = new CustomerEntity();
			customerEntity.setEmailID(customer.getEmailID());
			customer.setPassword(encode.passwordEncode(customer.getPassword(), 10));
			customerEntity.setPassword(customer.getPassword());
			customerEntity.setFirstName(customer.getFirstName());
			customerEntity.setLastName(customer.getLastName());
			customerEntity.setAddress(customer.getAddress());
			customerEntity.setCity(customer.getCity());
			customerEntity.setState(customer.getState());
			customerEntity.setZipCode(customer.getZipCode());
			customerEntity.setPhoneNumber(customer.getPhoneNumber());
			ShoppingCartEntity sc= new ShoppingCartEntity();
			customerEntity.setShoppingCart(sc);
			entityManager.persist(customerEntity);
			return customerEntity.getEmailID();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	
	
	


	@Override
	public String getPasswordByEmail(String email) throws EmailNotExits {

		CustomerEntity customerEntity=entityManager.find(CustomerEntity.class, email);
		String password;
		if(customerEntity!=null) {
			password=customerEntity.getPassword();
		}else{
			throw new EmailNotExits("Email does not exits");
		}
		
		return password;
	}


	
	
	
	

	@Override
	public Customer getAccountInfo(String accountID) {

		Customer customer=null;
		CustomerEntity customerEntity = entityManager.find(CustomerEntity.class, accountID);
		if(customerEntity !=null) {
			customer = new Customer();
			customer.setEmailID(customerEntity.getEmailID());
			customer.setFirstName(customerEntity.getFirstName());
			customer.setLastName(customerEntity.getLastName());
			customer.setPhoneNumber(customerEntity.getPhoneNumber());
			customer.setAddress(customerEntity.getAddress());
			customer.setCity(customerEntity.getCity());
			customer.setState(customerEntity.getState());
			customer.setZipCode(customerEntity.getZipCode());
			customer.setShoppingCart(null);
		}
		return customer;
	}

	
	public Boolean UpdateUser(Customer customer) {
		CustomerEntity customerEntity=entityManager.find(CustomerEntity.class, customer.getEmailID());
		if(customerEntity!=null) {
			customerEntity.setFirstName(customer.getFirstName());
			customerEntity.setLastName(customer.getLastName());
			customerEntity.setPhoneNumber(customer.getPhoneNumber());
			customerEntity.setAddress(customer.getAddress());
			customerEntity.setCity(customer.getCity());
			customerEntity.setState(customer.getState());
			customerEntity.setZipCode(customer.getZipCode());
			return true;
		}
		return false;
	}

	@Override
	public void emptyShoppingCart() {
		
	}

	@Override
	public String getNameByEmail(String id) {
		CustomerEntity customerEntity= entityManager.find(CustomerEntity.class, id);
		System.out.println("Name: "+ customerEntity.getFirstName()+" "+ customerEntity.getLastName());
		return customerEntity.getFirstName()+" "+ customerEntity.getLastName();
	}











}
