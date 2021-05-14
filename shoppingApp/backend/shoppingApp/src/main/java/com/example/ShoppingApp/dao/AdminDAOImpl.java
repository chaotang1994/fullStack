package com.example.ShoppingApp.dao;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import com.example.ShoppingApp.entity.AdminEntity;
import com.example.ShoppingApp.entity.ProductEntity;
import com.example.ShoppingApp.exception.EmailNotExits;
import com.example.ShoppingApp.exception.InvalidPasswordOrUsername;
import com.example.ShoppingApp.model.Admin;
import com.example.ShoppingApp.model.Product;
import com.example.ShoppingApp.security.PasswordEncode;

@Repository("admin_accountDAO")
public class AdminDAOImpl implements AdminDAO{
	
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	private PasswordEncode encode = new PasswordEncode();

	
	public String authentication(String username, String password)  throws InvalidPasswordOrUsername, EmailNotExits{

		try {
			String pWord=this.getPasswordByEmail(username);
//		admin account haven't encrypted
//			if(encode.passwordVerification(pWord,password)){
//				return username;
//			}else {
//				throw new InvalidPasswordOrUsername("Invalid username or password");
//			}
	
			if(pWord.equals(password)){
				return username;
			}else {
				throw new InvalidPasswordOrUsername("Invalid username or password");
			}
		}catch(InvalidPasswordOrUsername e) {
			throw new InvalidPasswordOrUsername("Invalid username or password");
		}catch(EmailNotExits e) {
			throw new EmailNotExits("Email does not exits");
		}
	}
	
	
	@Override
	public String getPasswordByEmail(String username) throws EmailNotExits {

		AdminEntity adminEntity = new AdminEntity();
		try {
			String jpql = "SELECT a FROM AdminEntity a WHERE a.username = :username";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("username", username);
			adminEntity = (AdminEntity) query.getSingleResult();
		}catch(Exception e) {
			throw new EmailNotExits("Email does not exits");
		}
		String password;
//		if(adminEntity!=null) {
//			password=adminEntity.getPassword();
//		}else{
//			throw new EmailNotExits("Email does not exits");
//		}
		
		return adminEntity.getPassword();
	}
	
//	@Override
//	public void changeName(String name) {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//
//	@Override
//	public Product deleteProduct(Product product) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Product updateProduct(Product product) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void changePassword(String password) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public ArrayList<Product> getProducts() {
		
		return null;
	}

	@Override
	public String createAdmin(Admin admin) throws Exception {
		AdminEntity adminEntity= new AdminEntity();
		adminEntity.setName(admin.getName());
		adminEntity.setUsername(admin.getName());
		adminEntity.setPassword(admin.getPassword());
		try {
			entityManager.persist(adminEntity);
		}catch(Exception e) {
			throw new Exception();
		}
		return adminEntity.getName();
	}



	

}
