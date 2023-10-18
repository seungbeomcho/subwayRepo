package com.kh.subway.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
	
	
	//자유게시판 목록 조회(작성일자 최신순)
	public List<BoardVo> boardList(Connection conn) throws Exception {
		
		String sql = "";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		return null;
	}
	
	
	
}
