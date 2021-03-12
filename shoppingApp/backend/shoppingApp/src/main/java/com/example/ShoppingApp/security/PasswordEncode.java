package com.example.ShoppingApp.security;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordEncode {

		public boolean passwordVerification(String passwordInData, String password) {
			return (BCrypt.checkpw(password,passwordInData));
			
		}
		
		public String passwordEncode(String password, int salt) {
			return BCrypt.hashpw(password,BCrypt.gensalt(salt));//password bcrpyt
		}
	
}
