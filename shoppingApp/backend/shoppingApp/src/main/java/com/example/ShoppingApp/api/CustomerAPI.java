package com.example.ShoppingApp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.ShoppingApp.exception.EmailNotExits;
import com.example.ShoppingApp.exception.InvalidPasswordOrUsername;
import com.example.ShoppingApp.model.Customer;
import com.example.ShoppingApp.model.Name;
import com.example.ShoppingApp.service.CustomerService;

@RestController
@CrossOrigin
public class CustomerAPI {
	
	@Autowired
	private CustomerService customerService;
	

	
	@RequestMapping(value="/registerNewCustomer", method=RequestMethod.POST)
	public ResponseEntity<String> registerNewCustomer(@RequestBody Customer customer){
		try {
			System.out.println("customer id here: "+customer.getEmailID());
			String registeredWithEmailID= customerService.registerNewCustomer(customer);
			return new ResponseEntity<String>(registeredWithEmailID,HttpStatus.OK);
		} catch (Exception e) {
//			if(e.getMessage().contains("Validator")){
//				throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,environment.getProperty(e.getMessage()));
//			}
//			throw new ResponseStatusException(HttpStatus.CONFLICT,environment.getProperty(e.getMessage()));
			throw new ResponseStatusException(HttpStatus.CONFLICT);

		}
		
	
		
	}
	

	
	@PostMapping(value="/printAccountInfo")
	public ResponseEntity<Customer> printAccountInfo(@RequestBody String id){
		Customer customer = customerService.printAccountInfo(id);
		if(customer!=null) {
			return new ResponseEntity<Customer>(customer,HttpStatus.OK);
		}
		return null;
	}
	
	
	@PutMapping(value="/updateUser")
	public ResponseEntity<Boolean> UpdateUser(@RequestBody Customer customer){
		Boolean result = customerService.UpdateUser(customer);
		return new ResponseEntity<Boolean>(result,HttpStatus.OK);
	}

	

	



}
