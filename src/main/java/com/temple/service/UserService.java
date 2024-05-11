package com.temple.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.temple.Entities.Devotee;
import com.temple.repos.UserRepositories;

import jakarta.transaction.Transactional;


@Service
public class UserService {
		@Autowired
		UserRepositories repository;
		@Autowired
		BCryptPasswordEncoder bCryptPasswordEncoder;
		
		
		public void save(Devotee devotee) throws Exception {
			

			
			System.out.println("checking Username" );
			if(checkIsUserAlreadyExistByEmail(devotee.getEmail())) {
				System.out.println("checking Username" );
				throw new Exception("User already exists for this email");
			}
			if(checkIsUserAlreadyExistByName(devotee.getUsername())) {
				System.out.println("checking Username" );
				throw new Exception("User already exists for this UserName");
			}
			
			devotee.setPassword(bCryptPasswordEncoder.encode(devotee.getPassword()));
			devotee.setEnabled(true); // Enable user by default
	        devotee.setRole("ROLE_USER");
	        repository.save(devotee);
	    }
		
		public Devotee updateUser(Devotee currentUser ,Devotee devotee) {
			currentUser.setUsername(devotee.getUsername());
			currentUser.setEmail(devotee.getEmail());
			currentUser.setPhone(devotee.getPhone());
			return  repository.save(currentUser);
		}
		
		public boolean checkIsUserAlreadyExistByEmail(String email) {
			return repository.findByEmail(email) != null ? true : false;
		}
		public boolean checkIsUserAlreadyExistByName(String email) {
			return repository.findByUsername(email) != null ? true : false;
		}
		
		public String getLoggedInUSer() {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			return  auth.getName();
		}
}
