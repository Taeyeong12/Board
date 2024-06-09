package com.springmvc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.dao.UserDao;
import com.springmvc.dto.User;

import lombok.RequiredArgsConstructor;  // Lombok의 RequiredArgsConstructor 어노테이션을 사용하기 위해 import

@Service
@RequiredArgsConstructor
public class UserService {

    // final 필드, 생성자를 통해 초기화
    private final UserDao userDao;
    
    
    // 보통 서비스에서는  @Transactional을 붙여서 하나의 트랜잭션으로 처리하게 한다.
    //sping은 트랜잭션을 처리해주는 트랙재션 관리자를 가지고 있다.
    @Transactional
    public User addUser(String name, String email, String password) {
        // userDao를 통해 addUser 메서드 호출
    	
    	User user1 = userDao.addUser(email, name, password);
    	if(user1 !=null) {
    		throw new RuntimeException("이미 가입된 이메일 입니다.");
    	}
    	
        User user = userDao.addUser(email, name, password);
        userDao.mappingUserRole(user.getUserId()); //권한을 부여한다.
        
        return user;
    }
    
    //회원정보를 가지고 오는 
    @Transactional
    public User getUser(String email) {
    	return userDao.getUser(email);
    }
}
