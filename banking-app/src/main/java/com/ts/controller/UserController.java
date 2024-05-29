package com.ts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ts.model.User;
import com.ts.service.UserService;

@RestController
public class UserController {
	
	@Autowired 
	UserService userService;
	
	@PostMapping("/reg-user")
	public String regUser(@RequestBody User user) {
		return userService.regUser(user);
	}
	
	@PutMapping("/add-balance")
	public String addBalance(@RequestParam("accno") int accNo,
			                 @RequestParam("bal") int balance,
			                 @RequestParam("pan") String pan) {
		
		return userService.addBalance(accNo, balance, pan);
	}
	
}
