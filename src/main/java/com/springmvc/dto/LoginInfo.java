package com.springmvc.dto;



import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//@AllArgsConstructor // 모든 필드에 대한 생성자가 자동으로 만들어진다.


public class LoginInfo {
    private int userId;
    private String email;
    private String name;
    private List<String> roles = new ArrayList<String>();
    
    public void addRole(String roleName) {
    	roles.add(roleName);
    }

	public LoginInfo(int userId, String email, String name) {
		super();
		this.userId = userId;
		this.email = email;
		this.name = name;
	}

}