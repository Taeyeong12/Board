package com.springmvc.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcInsertOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.dto.Board;

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
    
    @Transactional
    public void addBoard(int userId, String title, String content) {
        Board board = new Board();
        board.setUserId(userId);
        board.setTitle(title);
        board.setContent(content);
        board.setRegdate(LocalDateTime.now());
        SqlParameterSource params =  new BeanPropertySqlParameterSource(board);
        insertBoard.execute(params);
    }
    
    @Transactional(readOnly = true)
	public int getTotalCount() {
    	String sql ="select count(*)as total_Count from board"; //집합쿼리는 무조건 1건의 데이터만
    	Integer totalCount = jdbcTemplate.queryForObject(sql, Map.of(), Integer.class);//1건의 데이터만 가지고 오는
    	return totalCount.intValue();
	}
    
    @Transactional(readOnly = true)
    public List<Board> getBoards(int page) {
        // start는 0, 10, 20, 30 는 1page, 2page, 3page, 4page
        int start = (page - 1) * 10;
        String sql = "select b.user_id, b.board_id, b.title, b.regdate, b.view_cnt, u.name from board b, user u where b.user_id = u.user_id  order by board_id desc limit :start, 10";
        RowMapper<Board> rowMapper = BeanPropertyRowMapper.newInstance(Board.class);
        List<Board> list = jdbcTemplate.query(sql, Map.of("start", start), rowMapper);
        return list;
    }

    @Transactional(readOnly = true)
    public Board getBoard(int boardId) {
        // 1건 또는 0건.
        String sql = "select b.user_id, b.board_id, b.title, b.regdate, b.view_cnt, u.name, b.content from board b, user u where b.user_id = u.user_id  and b.board_id = :boardId";
        RowMapper<Board> rowMapper = BeanPropertyRowMapper.newInstance(Board.class);
        Board board = jdbcTemplate.queryForObject(sql, Map.of("boardId", boardId), rowMapper);
        return board;
    }

    @Transactional
    public void updateViewCnt(int boardId) {
        String sql = "update board\n" +
                "set view_cnt = view_cnt + 1\n" +
                "where board_id = :boardId";
        jdbcTemplate.update(sql, Map.of("boardId", boardId));
    }
    
    @Transactional
	public void deleteBoard(int boardId) {
		String sql = "delete from board where board_Id = :boardId";
        jdbcTemplate.update(sql, Map.of("boardId", boardId));
	}

	

}