package com.example.ShoppingApp;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.ShoppingApp.model.Customer;
import com.example.ShoppingApp.service.CustomerService;

@SpringBootApplication
public class ShoppingAppApplication implements CommandLineRunner{
	
	@Autowired
	private CustomerService customerService;
	
	public static void main(String[] args) {
		SpringApplication.run(ShoppingAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		printAccountInfo();
//		registerNewCustomer();
		passwordCheck();
	}
	
	public void registerNewCustomer() {
		
		Customer customer = new Customer("chao1994@gmail.com","abc123","Koi","Tang","5166467188","100 Jackson St Bayside, NY 11358");
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
