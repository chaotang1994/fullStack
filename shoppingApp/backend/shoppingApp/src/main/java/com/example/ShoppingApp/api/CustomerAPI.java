package com.example.ShoppingApp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.CONFLICT);

		}
		
	}

	

	
//	
//	@RequestMapping(value="/printAccountInfo", method=RequestMethod.GET)
//	public ResponseEntity<String> printAccountInfo() {
//		try {
//			String accountID="ctang2316@gmail.com";
//			Customer customer = customerService.printAccountInfo(accountID);
//			return new ResponseEntity<String>("Successfully " + customer.getEmailID() +" "
//					+customer.getAddress()+" "
//					+customer.getName(),HttpStatus.CREATED);
//		}catch(Exception e) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage(),e);
//		}
//		
//	}

}
