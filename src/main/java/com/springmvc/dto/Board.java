package com.springmvc.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Board {
    private int boardId;
    private String title;
    private String content;
    private String name;
    private int userId;
    private LocalDateTime regdate;
    private int viewCnt;
}

//조인을 위한 컬럼을 추가
// b.user_id, b.board_id, b.title, b.regdate, b.view_cnt, u.name

/*
'board_id', 'int', 'NO', 'PRI', NULL, 'auto_increment'
'title', 'varchar(100)', 'NO', '', NULL, ''
'content', 'text', 'YES', '', NULL, ''
'user_id', 'int', 'NO', 'MUL', NULL, ''
'regdate', 'timestamp', 'YES', '', 'CURRENT_TIMESTAMP', 'DEFAULT_GENERATED'
'view_cnt', 'int', 'YES', '', '0', ''

 */
