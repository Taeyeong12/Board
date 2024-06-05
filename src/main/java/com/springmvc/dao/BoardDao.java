package com.springmvc.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcInsertOperations;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDao {
    private final NamedParameterJdbcTemplate jdbcTemplate; //각종 sql실행
    
    private final SimpleJdbcInsertOperations insertBoard; //인서트
    
    @Autowired
    public BoardDao(DataSource dataSource) {
       this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
       insertBoard = new SimpleJdbcInsert(dataSource)
               .withTableName("board")
               .usingGeneratedKeyColumns("board_id"); // 오토인크리먼트자동으로 증가되는 id를 설정.
    }
	

}
