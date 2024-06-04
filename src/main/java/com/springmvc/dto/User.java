package com.springmvc.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class User {
	
	private int userId;
	private String email;
	private String name;
	private String password;
	private LocalDateTime regdate; //원래는 날짜 type으로 읽어 온 후 문자열로 변환하는 과정을 거쳐야함

}
