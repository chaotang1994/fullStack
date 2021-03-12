package com.example.ShoppingApp.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.example.ShoppingApp.entity.AdminEntity;
import com.example.ShoppingApp.entity.CustomerEntity;
import com.example.ShoppingApp.entity.ProductEntity;
import com.example.ShoppingApp.entity.ShoppingCartEntity;
import com.example.ShoppingApp.exception.IdNotExits;
import com.example.ShoppingApp.model.Admin;
import com.example.ShoppingApp.model.Product;

@Repository("productDAO")
public class ProductDAOImpl implements ProductDAO{
	
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public List<Product> getProductsFromAdmin(String username) throws Exception {
		AdminEntity adminEntity = null;
		try {
			String jpql = "SELECT a FROM AdminEntity a WHERE a.username = :username";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("username", username);
			adminEntity = (AdminEntity) query.getSingleResult();
		} catch (Exception e) {
			System.out.println("id invalid");
			e.printStackTrace();
		}
        List<Product> productList = new ArrayList<Product>();
        if(adminEntity.getAdminProduct()!=null) {
	        for(ProductEntity products: adminEntity.getAdminProduct()) {
	        	Product product = new Product();
	        	product.setId(products.getId());
	        	product.setName(products.getName());
	        	product.setCategory(products.getCategory());
	        	product.setCondition(products.getCondition());
	        	product.setPrice(products.getPrice());
	        	product.setQuantity(products.getQuantity());
	        	productList.add(product);
	        }
	        
			return productList;
        }else {
        	throw new Exception("List is null");
        }	
        
	}
	
	
	@Override
	public String updateProductName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateQuantity(int quantity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public boolean addProductFromAdmin(String admin_name, Product product) {
		AdminEntity adminEntity=null;
//		AdminEntity adminEntity=entityManager.find(AdminEntity.class, admin_name);
		String jpql = "SELECT a FROM AdminEntity a WHERE a.name = :admin_name";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("admin_name", admin_name);
		adminEntity = (AdminEntity) query.getSingleResult();
		
		
		if(adminEntity!=null) {
				List<ProductEntity> productEntity=adminEntity.getAdminProduct();
	        	ProductEntity productE = new ProductEntity();
				System.out.println("product name: "+product.getName());
	        	productE.setName(product.getName());
	        	productE.setCategory(product.getCategory());
	        	productE.setCondition(product.getCondition());
	        	productE.setQuantity(product.getQuantity());
	        	productE.setPrice(product.getPrice());
	        	productE.setImgURL("".getBytes());
	        	productE.setAdminEntity(adminEntity);
	        	productEntity.add(productE);
	        	return true;
		}
		return false;
	}

//	@Override
//	public int addProductFromAdmin(int id, List<Product> productList) {
//		AdminEntity adminEntity=entityManager.find(AdminEntity.class, id);
//		List<ProductEntity> productEntity=adminEntity.getAdminProduct();
//		
//		
//        for(Product product: productList) {
//        	ProductEntity productE = new ProductEntity();
//        	productE.setName(product.getName());
//        	productE.setCategory(product.getCategory());
//        	productE.setCondition(product.getCondition());
//        	productE.setQuantity(product.getQuantity());
//        	productE.setPrice(product.getPrice());
//        	productE.setImgURL("".getBytes());
//        	productE.setAdminEntity(adminEntity);
//        	productEntity.add(productE);
//        }
//        try {
//			adminEntity.setAdminProduct(productEntity);
//			entityManager.persist(adminEntity);
//			return id;
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return 0;
//	}

	

	@Override
	public int addProductToCustomer(int productID, String id) {
		CustomerEntity customerEntity=entityManager.find(CustomerEntity.class, id);
		ProductEntity productEntity = entityManager.find(ProductEntity.class, productID);
		if(customerEntity!=null && productEntity!=null) {
			try {
				customerEntity.getShoppingCart().getProductEntity().add(productEntity);
				productEntity.getShoppingCartEntity().add(customerEntity.getShoppingCart());
				entityManager.persist(customerEntity);
				System.out.println("here");
				return productID;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(customerEntity==null) {
			System.out.println("customer Entity is null");
		}
		if(productEntity==null) {
			System.out.println("product Entity is null");
		}
		return -1;
	}

	@Override
	public List<Product> getProductsFromShoppingCart(String id) throws Exception {
		CustomerEntity customerEntity= new CustomerEntity();
		customerEntity=entityManager.find(CustomerEntity.class, id);
		List<ProductEntity> shoppingCartProduct=customerEntity.getShoppingCart().getProductEntity();
		List<Product> list = new ArrayList<>();
		if(shoppingCartProduct==null || shoppingCartProduct.size()==0) {
			System.out.println("shoppingCartList is empty");
			throw new Exception("empty cart");
		}else {
			System.out.println("shoppingCartList is not empty");
			for(ProductEntity productE: shoppingCartProduct) {
				Product product = new Product();
				System.out.println("product Name: "+productE.getName());
				product.setId(productE.getId());
				product.setName(productE.getName());
				product.setCondition(productE.getCondition());
				product.setCategory(productE.getCategory());
				product.setQuantity(productE.getQuantity());
				product.setPrice(productE.getPrice());
				product.setImgURL("");
				list.add(product);
			}
		}
		
		return list;
	}


	@Override
	public boolean removeProductFromAdmin(Integer id, String admin_id){//only one admin account, so just remove product from the only admin account.
//		AdminEntity adminEntity= new AdminEntity();
		ProductEntity productEntity=entityManager.find(ProductEntity.class, id);
		if(productEntity!=null) {
			entityManager.remove(productEntity);
			return true;
		}
		return false;
		
	}


	@Override
	public boolean editProductFromAdmin(String admin_id, Product product) {
		ProductEntity productEntity = new ProductEntity();
		productEntity=entityManager.find(ProductEntity.class, product.getId());
		if(productEntity!=null && product!=null) {
			productEntity.setName(product.getName());
			productEntity.setCategory(product.getCategory());
			productEntity.setQuantity(product.getQuantity());
			productEntity.setCondition(product.getCondition());
			productEntity.setPrice(product.getPrice());
			entityManager.persist(productEntity);
			return true;
		}
		return false;
	}








}
