package com.kh.subway.comment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javaJDBCTEMPLATE.JDBCTemplate;

public class CommentDao {
	
	
	//댓글 입력
	public int leaveComment(Connection conn, String comment) throws Exception {
		String sql = "INSERT INTO BOARD_COMMENT(COMMENT_NO, CONTENT) VALUES (SEQ_COMMENT_NO.NEXTVAL , ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, comment);
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}
}
