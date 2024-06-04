package com.springmvc.dao;

import java.time.LocalDateTime;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcInsertOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.dto.User;


@Repository
public class UserDao {
	
    private final NamedParameterJdbcTemplate jdbcTemplate;
    
    private final SimpleJdbcInsertOperations insertUser;


    @Autowired
    public UserDao(DataSource dataSource) {
       this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
       insertUser = new SimpleJdbcInsert(dataSource)
               .withTableName("user")
               .usingGeneratedKeyColumns("user_id"); // 자동으로 증가되는 id를 설정.
    }
	
	
	@Transactional
    public User addUser(String email, String name, String password){
        // Service에서 이미 트랜잭션이 시작했기 때문에, 그 트랜잭션에 포함된다.
        // insert into user (email, name, password, regdate) values (:email, :name, :password, :regdate); # user_id auto gen
        // SELECT LAST_INSERT_ID();
        User user = new User();
        user.setName(name); // name 칼럼.
        user.setEmail(email); // email
        user.setPassword(password); // password
        user.setRegdate(LocalDateTime.now()); // regdate
        SqlParameterSource params = new BeanPropertySqlParameterSource(user);
        Number number = insertUser.executeAndReturnKey(params);// insert를 실행하고, 자동으로 생성된 id를 가져온다.
        int userId = number.intValue();
        user.setUserId(userId);
        return user;
    }
	
	@Transactional
	public void mappingUserRole(int userId) {
        // Service에서 이미 트랜잭션이 시작했기 때문에, 그 트랜잭션에 포함된다.
        // insert into user_role( user_id, role_id ) values ( ?, 1);
        String sql = "insert into user_role( user_id, role_id ) values (:userId, 1)";
        SqlParameterSource params = new MapSqlParameterSource("userId", userId);
        jdbcTemplate.update(sql, params);
	}
	
	

}
