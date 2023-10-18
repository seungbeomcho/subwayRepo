package com.kh.subway.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.kh.subway.board.vo.BoardVo;

public class BoardDao {
	
	public int write(Connection conn, BoardVo vo) throws Exception {
		
		String sql = "INSERT INTO BOARD(BOARD_NO, TITLE, CONTENT) VALUES (SEQ_BOARD_NO.NEXTVAL , ? , ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getContent());
		int result = pstmt.executeUpdate();
		
		return result;
	}
	
	
	
}
