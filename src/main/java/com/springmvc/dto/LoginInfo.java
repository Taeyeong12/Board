package com.springmvc.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor // 모든 필드에 대한 생성자가 자동으로 만들어진다.
public class LoginInfo {
    private int userId;
    private String email;
    private String name;

}