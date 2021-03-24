package com.example.ShoppingApp.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.ShoppingApp.exception.IdNotExits;
import com.example.ShoppingApp.model.Product;
import com.example.ShoppingApp.service.AdminService;
import com.example.ShoppingApp.service.ProductService;


@RestController
@CrossOrigin
public class productAPI {
	
	
	@Autowired
	private ProductService productService;
	
	
	@PostMapping(value="/addProductFromAdmin/{admin_name}")
	public ResponseEntity<Boolean> addProductFromAdmin(@PathVariable("admin_name") String admin_name, @RequestBody Product product){
		System.out.println("admin_name: "+ admin_name);
		System.out.println("product name: "+ product.getName());
		System.out.println("product quantity: "+ product.getQuantity());

		Boolean status= productService.addProductFromAdmin(admin_name,product);
		
		return status ? new ResponseEntity<Boolean>(status,HttpStatus.CREATED) : new ResponseEntity<Boolean>(status,HttpStatus.SEE_OTHER);
	}

	@PostMapping(value="/getProductsFromAdmin")
	public ResponseEntity<List<Product>> getProductsFromAdmin(@RequestBody String id) throws Exception {
		
			List<Product> list = productService.getProductsFromAdmin(id);
			if(list!=null) {
				return new ResponseEntity<List<Product>>(list,HttpStatus.OK);
			}

			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	
	@PostMapping(value="/addProductToCustomer/{id}")
	@ResponseBody
	public ResponseEntity<Integer> addProductToCustomer(@PathVariable("id")String id, @RequestBody Product product){
		System.out.println("productID " +product.getId());
		System.out.println("customerID" +id);
		int productId=productService.addProductToCustomer(product,id);
		return new ResponseEntity<Integer>(productId,HttpStatus.OK);
	} 
	
	@GetMapping(value="/getProductsFromShoppingCart/{id}")
	@ResponseBody
	public ResponseEntity<List<Product>> getProductsFromShoppingCart(@PathVariable("id") String id) throws Exception{
		List<Product> list;
		try {
			list = productService.getProductsFromShoppingCart(id);
			return new ResponseEntity<List<Product>>(list,HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);		
		}
	}
	
	
	@PostMapping(value="/removeProductFromAdmin/{admin_id}")
	@ResponseBody
	public ResponseEntity<Boolean> removeProductFromAdmin(@PathVariable("admin_id")String admin_id,@RequestBody Integer id){
		boolean result=false;
		System.out.println("adminId: "+admin_id);
		System.out.println("id: "+id);

		result=productService.removeProductFromAdmin(id,admin_id);
		System.out.println("result: "+result);
		if(result!=false) {
			return new ResponseEntity<Boolean>(result,HttpStatus.OK);
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(value="/removeProductFromUser/{id}/{product_id}")
	@ResponseBody
	public ResponseEntity<Integer> removeProductFromCustomer(@PathVariable("id")String id, @PathVariable("product_id")int product_id) {
		int result = productService.removeProductFromCustomer(id, product_id);
		if(result!=-1) {
			return new ResponseEntity<Integer>(result,HttpStatus.OK);
		}
		return new ResponseEntity<Integer>(result,HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping(value="/editProductFromAdmin/{admin_id}")
	public ResponseEntity<Boolean> editProductFromAdmin(@PathVariable("admin_id") String admin_id,@RequestBody Product product){
		boolean result=false;
		result=productService.editProductFromAdmin(admin_id,product);
		if(result==false) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Boolean>(result,HttpStatus.OK);
		
	}
	
	@PutMapping(value="/updateQuantityFromUser/{user_id}")
	public ResponseEntity<Integer> updateQuantityFromUser(@PathVariable("user_id") String user_id,@RequestBody Product product){
		Integer id=productService.updateQuantityFromUser(user_id, product);
		return new ResponseEntity<Integer>(id,HttpStatus.OK);
	}
	
	
	
}
