package com.springmvc.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.dao.BoardDao;
import com.springmvc.dto.Board;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardDao boardDao;
	
	
	@Transactional
	public void addBoard(int userId, String title, String content) {
		boardDao.addBoard(userId, title, content);
	}

	
	@Transactional(readOnly = true) // select만 할때는 성능상 좋음
	public int getTotalCount() {
		return boardDao.getTotalCount();
	}

	@Transactional(readOnly = true)
	public List<Board> getBoards(int page) {
		// TODO Auto-generated method stub
		return boardDao.getBoards(page);
	}
	
	
	@Transactional
	public Board getBoard(int boardId) {
		Board board = boardDao.getBoard(boardId);
		boardDao.updateViewCnt(boardId);
		return board;
	}
	
	

}
