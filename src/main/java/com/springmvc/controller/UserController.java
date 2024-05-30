package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

	
	@GetMapping("/userRegForm")
	public String userRegForm() {
		
		return "userRegForm";
	}
	
	@PostMapping("/userReg")
	public String userReg(
			@RequestParam("name")String name,
			@RequestParam("email")String email,
			@RequestParam("password")String password
			) {
		
		System.out.println("name :" + name);
		System.out.println("email : " + email);
		System.out.println("passwrod" + password);
		
		return "redirect:/welcome";
	}
	
}
