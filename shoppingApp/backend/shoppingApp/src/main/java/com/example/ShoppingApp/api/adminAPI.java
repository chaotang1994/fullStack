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

	
	
}
