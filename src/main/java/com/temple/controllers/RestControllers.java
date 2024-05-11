package com.temple.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.temple.Entities.Devotee;
import com.temple.Entities.UserOrder;
import com.temple.Entities.Products;
import com.temple.repos.FileUploadRepo;
import com.temple.repos.OrdersRepository;
import com.temple.repos.UserRepositories;
import com.temple.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@org.springframework.web.bind.annotation.RestController
public class RestControllers {
	@Autowired
	FileUploadRepo repo;
	@Autowired
	UserService service;
	@Autowired
	UserRepositories repositories;
	@Autowired
	FileUploadRepo fileUploadRepo;
	@Autowired
	OrdersRepository ordersRepository;

	@GetMapping(value = "/getAllItems", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Products>> getAllProducts() {
		List<Products> list = new ArrayList<>();
		list = repo.findAll();
		return ResponseEntity.ok().body(list);
	}

	@DeleteMapping("/data")
	public String getDataToUpdate() {
		System.out.println("hello from post");
		return "redirect:/Users/Donate";
	}

	@GetMapping(value = "/get/profile", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Devotee> getUserProfile() {
		Devotee byUsername = repositories.findByUsername(this.service.getLoggedInUSer());
		return ResponseEntity.ok().body(byUsername);
	}

	@PutMapping(value = "/updateUser", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Devotee> updateUser(@RequestBody Devotee devotee) throws Exception {
		System.out.println(devotee.getId());
		Devotee updateUser = service.updateUser(repositories.findByUsername(this.service.getLoggedInUSer()), devotee);
		System.out.println(updateUser.getId());
		return ResponseEntity.ok().body(devotee);
	}

	@DeleteMapping(value = "/deleteUser", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Devotee> deleteUser(@RequestBody Devotee devotee) {
		repositories.delete(devotee);
		return ResponseEntity.ok().body(new Devotee());
	}

	@DeleteMapping(value = "/delete-item", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Products>> deleteItems(@RequestBody Products products) {
		fileUploadRepo.delete(products);
		List<Products> all = fileUploadRepo.findAll();
		return ResponseEntity.ok().body(all);
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, response, SecurityContextHolder.getContext().getAuthentication());
		return "redirect:/login"; // You can redirect to any page after logout
	}

	@PostMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserOrder>> placeOrder(@RequestBody List<UserOrder> orders) {

		for (UserOrder o : orders) {
			ordersRepository.save(o);
		}
		return ResponseEntity.ok().body(orders);

	}

}
