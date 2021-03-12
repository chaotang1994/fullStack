package com.example.ShoppingApp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.ShoppingApp.exception.EmailNotExits;
import com.example.ShoppingApp.exception.InvalidPasswordOrUsername;
import com.example.ShoppingApp.model.Customer;
import com.example.ShoppingApp.service.AdminService;
import com.example.ShoppingApp.service.CustomerService;


@RestController
@CrossOrigin
public class authenticationAPI {
	
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping(value="/Login/{type}")
	public ResponseEntity<String> authentication(@PathVariable("type") String type, @RequestBody Customer customer){

		
		try {
			String id;
			if(type.equals("Customer")) {
				id = customerService.authentication(customer.getEmailID(), customer.getPassword());
			}else {
				id = adminService.authentication(customer.getEmailID(), customer.getPassword());
			}
			return new ResponseEntity<String>(id,HttpStatus.OK);
		}catch(EmailNotExits e) {
			System.out.println(e);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}catch (InvalidPasswordOrUsername e) {
			System.out.println(e);

			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
		
	}

}
