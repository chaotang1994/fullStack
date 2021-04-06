package com.example.ShoppingApp.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.stereotype.Repository;
import com.example.ShoppingApp.entity.AdminEntity;
import com.example.ShoppingApp.entity.CustomerEntity;
import com.example.ShoppingApp.entity.ProductEntity;
import com.example.ShoppingApp.entity.ShoppingCartEntity;
import com.example.ShoppingApp.entity.ShoppingCartQuantityEntity;
import com.example.ShoppingApp.exception.IdNotExits;
import com.example.ShoppingApp.model.Product;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

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
		productEntity.setImgURL(product.getImgURL());//
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
	        	product.setImgURL(products.getImgURL());
	        	System.out.println("Image "+products.getImgURL());
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
	public boolean addProductFromAdmin(String admin_name, Product product) throws UnsupportedEncodingException {
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
	        	productE.setImgURL(product.getImgURL());
	        	System.out.println("image data: "+ productE.getImgURL());
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
	public boolean removeProductFromAdmin(Integer product_id, String admin_id){//only one admin account, so just remove product from the only admin account.
//		AdminEntity adminEntity= new AdminEntity();
		ProductEntity productEntity=entityManager.find(ProductEntity.class, product_id);
		
//		for(ShoppingCartQuantityEntity shoppingCartQuantityEntity :  productEntity.getShoppingCartQuantityEntity()) {
//			shoppingCartQuantityEntity.setShoppingCartEntity(null);
//			shoppingCartQuantityEntity.getShoppingCartEntity().setShoppingCartQuantityEntity(null);
//			entityManager.remove(entityManager.find(ShoppingCartQuantityEntity.class, shoppingCartQuantityEntity.getId()));
//		}
		if(productEntity!=null) {
			entityManager.remove(productEntity);
			return true;
		}
		return false;
		
	}
	
	

	

	@Override//do not have to check same product exits before add to shoppingCart
	public int addProductToCustomer(Product product, String id) {
		CustomerEntity customerEntity;
		customerEntity=entityManager.find(CustomerEntity.class, id);
		ProductEntity productEntity = entityManager.find(ProductEntity.class, product.getId());//check product exits in product database
		ProductEntity pe;
		if(customerEntity!=null && productEntity!=null) {
			List<ShoppingCartQuantityEntity>list=customerEntity.getShoppingCart().getShoppingCartQuantityEntity();
			try {
				for(int i=0; i<list.size();i++) {
					if(list.get(i).getProductEntity().getId()==product.getId()) {
						list.get(i).setQuantity(list.get(i).getQuantity()+product.getQuantity());//if item already exits in cart when user click again addToCart, all I do is to increase quantity of the product in the cart
						return list.get(i).getProductEntity().getId(); 
					}
				}
				pe = new ProductEntity();
				pe.setId(product.getId());
				pe.setName(product.getName());
				pe.setCategory(product.getCategory());
				pe.setCondition(product.getCondition());
	        	pe.setImgURL(product.getImgURL());
				pe.setPrice(product.getPrice());
				pe.setQuantity(product.getQuantity());
				ShoppingCartQuantityEntity sqe=new ShoppingCartQuantityEntity();
				sqe.setProductEntity(pe);
				sqe.setQuantity(pe.getQuantity());
				customerEntity.getShoppingCart().getShoppingCartQuantityEntity().add(sqe);
				sqe.setShoppingCartEntity(customerEntity.getShoppingCart());
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
	public int removeProductFromCustomer(String id, int product_id) {
		CustomerEntity customerEntity=entityManager.find(CustomerEntity.class, id);
		List<ShoppingCartQuantityEntity> ShoppingCartQuantityEntity=customerEntity.getShoppingCart().getShoppingCartQuantityEntity();
		for(int i=0; i<ShoppingCartQuantityEntity.size(); i++) {
			if(ShoppingCartQuantityEntity.get(i).getProductEntity().getId()==product_id) {
				int scid=ShoppingCartQuantityEntity.get(i).getId();
				System.out.println("item "+ShoppingCartQuantityEntity.get(i).getProductEntity().getId()+ " id + "+ ShoppingCartQuantityEntity.get(i).getProductEntity().getName() +" remove successfully!!!");
				ShoppingCartQuantityEntity.get(i).setShoppingCartEntity(null);
				ShoppingCartQuantityEntity scqe=ShoppingCartQuantityEntity.remove(i);
				System.out.println("return id: "+scqe.getProductEntity().getId());
				System.out.println("return size: "+ShoppingCartQuantityEntity.size());
				ShoppingCartQuantityEntity shoppingCartQuantityEntity=entityManager.find(ShoppingCartQuantityEntity.class, scid);
				entityManager.remove(shoppingCartQuantityEntity);
				
				
				return scqe.getProductEntity().getId();
			}
		}
		return -1;
	}

	
	

	@Override
	public List<Product> getProductsFromShoppingCart(String id) throws Exception {
		CustomerEntity customerEntity;
		int count=0;
		customerEntity=entityManager.find(CustomerEntity.class, id);
		List<ShoppingCartQuantityEntity> ShoppingCartQuantityEntity=customerEntity.getShoppingCart().getShoppingCartQuantityEntity();
		System.out.println("size of ShoppingCartQuantityEntity "+ShoppingCartQuantityEntity.size());
		List<Product> list = new ArrayList<>();
		if(ShoppingCartQuantityEntity==null || ShoppingCartQuantityEntity.size()==0) {
			System.out.println("shoppingCartList is empty");
			throw new Exception("empty cart");
		}else {
			System.out.println("shoppingCartList is not empty");
			for(ShoppingCartQuantityEntity products: ShoppingCartQuantityEntity) {
				Product product = new Product();
				System.out.println("product Name: "+products.getProductEntity().getName());
				product.setId(products.getProductEntity().getId());
				product.setName(products.getProductEntity().getName());
				product.setCondition(products.getProductEntity().getCondition());
				product.setCategory(products.getProductEntity().getCategory());
				product.setQuantity(products.getQuantity());
				product.setPrice(products.getProductEntity().getPrice());
				count+=products.getProductEntity().getPrice();
	        	product.setImgURL(products.getProductEntity().getImgURL());
				list.add(product);
			}
		}
		
		return list;
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
	public Integer updateQuantityFromUser(String user_id, Product product) {
		CustomerEntity customerEntity=entityManager.find(CustomerEntity.class, user_id);
		List<ShoppingCartQuantityEntity> customer_productList=customerEntity.getShoppingCart().getShoppingCartQuantityEntity();
		for(ShoppingCartQuantityEntity scqe : customer_productList) {
			if(scqe.getProductEntity().getId()==product.getId()) {
				scqe.setQuantity(product.getQuantity());
				System.out.println("quantity: "+scqe.getQuantity());
				return scqe.getProductEntity().getId();
			}
		}
		return -1;
	}








}
