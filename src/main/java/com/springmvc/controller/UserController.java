package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springmvc.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	//회원가입폼 페이지
	@GetMapping("/userRegForm")
	public String userRegForm() {
		
		return "userRegForm";
	}
	
	//회원가입한 정보를 받는 페이지
	@PostMapping("/userReg")
	public String userReg(
			@RequestParam("name")String name,
			@RequestParam("email")String email,
			@RequestParam("password")String password
			) {
		
		//회원정보를 저장
		System.out.println("name :" + name);
		System.out.println("email : " + email);
		System.out.println("passwrod : " + password);
		
		userService.addUser(name, email, password);
		
		return "redirect:/welcome";
	}
	
	//회원가입 환영페이지
	@GetMapping("/welcome")
	public String welcome() {	
		
		return "welcome";
	}
	
	@GetMapping("/loginform")
	public String loginform() {
		
		return"loginform";
	}
	
	
	@PostMapping("/login")
	public String login(
			@RequestParam("email")String email,
			@RequestParam("password")String password
			) {
		
		//email에 해당하는 회원 정보를 읽어온 후
		//아이디 암호가 맞다면 세션에 회원정보를 저장한다.
		//welcome페이로 이동한다.
		
		System.out.println("email : " + email );
		System.out.println("password : " + password );

		return"redirect:/list";
	}
	
	@GetMapping("/logout")
	public String lougout() {
		//세션에서 회원정보를 삭제
		return"redirect:/list";
	}
}
