package com.example.ShoppingApp.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ShoppingApp.model.Admin;
import com.example.ShoppingApp.model.Customer;
import com.example.ShoppingApp.model.Product;
import com.example.ShoppingApp.service.AdminService;

@RestController
@CrossOrigin
public class adminAPI {
	
	@Autowired
	private AdminService adminService;

	
	
	
	@PostMapping(value="/createAdmin")
	public ResponseEntity<String> createAdmin(@RequestBody Admin admin){
		String id = null;
		try {
			id = adminService.createAdmin(admin);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(id,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(id,HttpStatus.OK);
	}


	
	
	@GetMapping(value="/addProductAndAdmin")
	public ResponseEntity<Admin> getProductsFromAdmin() {
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
		product.setImgURL("iphone_img");
		productList.add(product);
		
		
		Product product1 = new Product();
		product1.setName("galaxyS10");
		product1.setCategory("electronics");
		product1.setQuantity(2);
		product1.setCondition("new");
		product1.setImgURL("galaxy_img");
		productList.add(product1);
		
		Product product2 = new Product();
		product2.setName("googlePixle");
		product2.setCategory("electronics");
		product2.setQuantity(6);
		product2.setCondition("new");
		product2.setImgURL("googlePixle_img");
		productList.add(product2);
		
		admin.setProduct(productList);
		
		
		Admin a=adminService.addProductAndAdmin(admin);
		return new ResponseEntity<Admin>(a,HttpStatus.OK);
		
	}
	
	
}
