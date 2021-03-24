package com.example.ShoppingApp.dao;

import java.util.ArrayList;
import java.util.Collection;
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
	
	
	public ProductEntity setProductEntity(Product product) {
		ProductEntity productEntity = new ProductEntity();
		productEntity.setId(product.getId());
		productEntity.setName(product.getName());
		productEntity.setCategory(product.getCategory());
		productEntity.setCondition(product.getCondition());
		productEntity.setImgURL("".getBytes());//
		productEntity.setPrice(product.getPrice());
		productEntity.setQuantity(product.getQuantity());
		return productEntity;
	}
	
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

	

	@Override//do not have to check same product exits before add to shoppingCart
	public int addProductToCustomer(Product product, String id) {
		CustomerEntity customerEntity;
		customerEntity=entityManager.find(CustomerEntity.class, id);
		ProductEntity productEntity = entityManager.find(ProductEntity.class, product.getId());//check product exits in product database
		ProductEntity pe;
		if(customerEntity!=null && productEntity!=null) {
			try {
//				for(ProductEntity ptEntity: customerEntity.getShoppingCart().getProductEntity()) {
//					if(ptEntity.getId()==product.getId()) {
//						ptEntity.setQuantity(ptEntity.getQuantity()+product.getQuantity());//if item already exits in cart when user click again addToCart, all I do is to increase quantity of the product in the cart
//						return product.getId(); 
//					}
//				}
				pe = new ProductEntity();
				pe.setId(product.getId());
				pe.setName(product.getName());
				pe.setCategory(product.getCategory());
				pe.setCondition(product.getCondition());
				pe.setImgURL("".getBytes());//
				pe.setPrice(product.getPrice());
				pe.setQuantity(product.getQuantity());
				
				
				System.out.println(" ppp  ppp size: "+ customerEntity.getShoppingCart().getProductEntity().size());
				System.out.println(" pe: "+ pe.getQuantity());

				customerEntity.getShoppingCart().getProductEntity().add(pe);
				System.out.println(" ppp  ppp size after: "+ customerEntity.getShoppingCart().getProductEntity().size());
				
				return product.getId();
				
			}catch(Exception e) {
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
		CustomerEntity customerEntity;
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
				System.out.println(" get  quantity: "+ product.getQuantity());
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


	@Override
	public int removeProductFromCustomer(String id, int product_id) {
		CustomerEntity customerEntity=entityManager.find(CustomerEntity.class, id);
		List<ProductEntity> peList=customerEntity.getShoppingCart().getProductEntity();
		for(int i=0; i<peList.size(); i++) {
			if(peList.get(i).getId()==product_id) {
				System.out.println("item "+peList.get(i).getId()+ " id + "+ peList.get(i).getName() +" remove successfully!!!");
				ProductEntity pe=peList.remove(i);
				return pe.getId();
			}
		}
		return -1;
	}


	@Override
	public Integer updateQuantityFromUser(String user_id, Product product) {
		CustomerEntity customerEntity=entityManager.find(CustomerEntity.class, user_id);
		List<ProductEntity> customer_productList=customerEntity.getShoppingCart().getProductEntity();
		for(ProductEntity pe : customer_productList) {
			if(pe.getId()==product.getId()) {
				pe.setQuantity(product.getQuantity());
				System.out.println("quantity: "+product.getQuantity());
				return pe.getId();
			}
		}
		return -1;
	}








}
