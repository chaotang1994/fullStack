package com.example.ShoppingApp;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.example.ShoppingApp.model.Admin;
import com.example.ShoppingApp.model.Customer;
import com.example.ShoppingApp.model.Product;
import com.example.ShoppingApp.service.AdminService;
import com.example.ShoppingApp.service.CustomerService;
import com.example.ShoppingApp.service.ProductService;

@SpringBootApplication
public class ShoppingAppApplication implements CommandLineRunner{
	
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ProductService productService;

	
	public static void main(String[] args) {
		SpringApplication.run(ShoppingAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		printAccountInfo();
//		registerNewCustomer();
//		passwordCheck();
//		addProductAndAdmin();
//		addProductFromAdmin();
		System.out.println("____________");
//		getProductsFromAdmin();
//		addProductToCustomer();
	}

//	public void addProductToCustomer(){
//		int id=productService.addProductToCustomer(3, "meile");
//		System.out.println(id);
//	}
	

//	public void addProductFromAdmin() {
//		List<Product> productList = new ArrayList();
//		
//		Product product1 = new Product();
//		product1.setName("cctv");
//		product1.setCategory("electronics");
//		product1.setQuantity(2);
//		product1.setCondition("used");
//		product1.setImgURL("cctv");
//		product1.setPrice(100);
//		productList.add(product1);
//		
//		
//		Product product2 = new Product();
//		product2.setName("mouse");
//		product2.setCategory("electronics");
//		product2.setQuantity(6);
//		product2.setCondition("new");
//		product2.setImgURL("mouse");
//		product2.setPrice(50);
//
//		productList.add(product2);
//		
//		
//		Product product3 = new Product();
//		product3.setName("moniter");
//		product3.setCategory("electronics");
//		product3.setQuantity(10);
//		product3.setCondition("new");
//		product3.setPrice(200);
//		product3.setImgURL("moniter");
//		productList.add(product3);
//		
//		
//		int id=productService.addProductFromAdmin(4, productList);
//		if(id!=0) {
//			System.out.println("Product successfully add to "+ id);
//		}
//	}
	
	public void getProductsFromAdmin() {
		System.out.println("using here????");
		List<Product> productList;
		try {
			productList = productService.getProductsFromAdmin("Andy");
			System.out.println(productList);
			if(productList!=null) {
				for(Product product: productList) {
					System.out.println(product.getId());
					System.out.println(product.getName());

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void addProductAndAdmin() {
		List<Product> productList = new ArrayList<Product>();
		Admin admin = new Admin();
		admin.setUsername("Andy2316");
		admin.setName("Andy");
		admin.setPassword("andy2316");
		
		Product product = new Product();
		product.setName("iphone10");
		product.setCategory("electronics");
		product.setQuantity(3);
		product.setCondition("new");
		productList.add(product);
		
		
		Product product1 = new Product();
		product1.setName("galaxyS10");
		product1.setCategory("electronics");
		product1.setQuantity(2);
		product1.setCondition("new");
		productList.add(product1);
		
		Product product2 = new Product();
		product2.setName("googlePixle");
		product2.setCategory("electronics");
		product2.setQuantity(6);
		product2.setCondition("new");
		productList.add(product2);
		
		
		Product product3 = new Product();
		product2.setName("huawei");
		product2.setCategory("electronics");
		product2.setQuantity(10);
		product2.setCondition("new");
		productList.add(product3);
		
		
		Product product4 = new Product();
		product2.setName("xiaomi");
		product2.setCategory("electronics");
		product2.setQuantity(1);
		product2.setCondition("used");
		productList.add(product4);
		
		admin.setProduct(productList);
		


		System.out.println("Product successfully added!!!!");

	}

	
	public void registerNewCustomer() {
		
		Customer customer = new Customer("ab","ba","cbdfd","sdd","sde","dsf","sdi",123,"sdg");
		System.out.println("Register Done!!!!");
		String id=customerService.registerNewCustomer(customer);
		System.out.println("id is already insert "+ id);
		
	}
	
	
	
	public void passwordCheck() {
		String password="abc123";
		String afterHash=BCrypt.hashpw(password, BCrypt.gensalt(10));
		System.out.println("original password: "+password);
		System.out.println("after Hash password: "+afterHash);
		if(BCrypt.checkpw(password, afterHash)) {
			System.out.println("there are the same");
		}else {
			System.out.println("there are not the same");
			
		}
		
	}

	
	public void printAccountInfo() {
		Customer a=customerService.printAccountInfo("ctang2316@gmail.com");
		System.out.println("here;;][;[;][  "+a.getAddress());
	}
	
	

}
