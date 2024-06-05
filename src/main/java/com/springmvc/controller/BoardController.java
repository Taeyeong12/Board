package com.springmvc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springmvc.dto.LoginInfo;
import com.springmvc.service.BoardService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	
	@GetMapping("/list")
	public String list(HttpSession session, Model model) {
		
		//session에서 가져온값은  object
		LoginInfo loginInfo = (LoginInfo)session.getAttribute("loginInfo");
		model.addAttribute("loginInfo", loginInfo);
		return "list";
	}

	@GetMapping("/board")
	public String board(@RequestParam("id")int id) {
		
		System.out.println("id : " + id );
		
		
		//id에 해당하는 게시물을 읽어온다.
		//id에 해당하는 게시물의 조회수도 1증가한다.
		return"board";
	}
	
	@GetMapping("/writeForm")
	public String writeForm(HttpSession session,Model model) {
		//로그인한 사용자만 글을 써야한다. 로그인을 하지 않았다면 리스트보기로 자동 이동시킨다.
		LoginInfo loginInfo = (LoginInfo)session.getAttribute("loginInfo");
		if(loginInfo == null) {
			return "redirect:/loginform";
		}
		
		model.addAttribute("loginInfo",loginInfo);
		
		return"writeForm";
	}
	
	@PostMapping("/write")
	public String write(
			@RequestParam("title") String title,
			@RequestParam("content") String content,
			HttpSession session
			) {
		LoginInfo loginInfo = (LoginInfo)session.getAttribute("loginInfo");
		if(loginInfo == null) {
			return "redirect:/loginform";
		}

		System.out.println("title : " + title );
		//System.out.println("content : " + content);
		
		boardService.addBoard(loginInfo.getUserId(), title, content);
		
		//로그인한 회원정보 + 제목,내용을 저장
		return "redirect:/list";
	}
	
}
