package com.springmvc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {
	
	@Transactional
	public void addBoard(int userId, String title, String content) {
				
	}
	
	

}
