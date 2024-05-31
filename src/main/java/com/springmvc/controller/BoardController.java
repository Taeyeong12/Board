package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {
	
	@GetMapping("/list")
	public String list() {
		
		return "list";
	}

	@GetMapping("/board")
	public String board(@RequestParam("id")int id) {
		
		System.out.println("id : " + id );
		
		
		//id에 해당하는 게시물을 읽어온다.
		//id에 해당하는 게시물의 조회수도 1증가한다.
		return"board";
	}
}
