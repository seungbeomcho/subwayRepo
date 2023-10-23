package com.kh.subway.comment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.kh.subway.comment.vo.CommentVo;

import javaJDBCTEMPLATE.JDBCTemplate;

public class CommentDao {
	
	
	//댓글 입력(유저)
	public int leaveCommentUser(Connection conn, CommentVo vo) throws Exception {
		String sql = "INSERT INTO BOARD_COMMENT(COMMENT_NO, BOARD_NO, USER_NO ,CONTENT) VALUES (SEQ_COMMENT_NO.NEXTVAL , ?, ? , ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getBoardNo());
		pstmt.setString(2, vo.getUserNo());
		pstmt.setString(3, vo.getContent());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}
	//댓글 입력(관리자)
	public int leaveCommentAdmin(Connection conn, CommentVo vo) throws Exception{
		String sql = "INSERT INTO BOARD_COMMENT(COMMENT_NO, BOARD_NO, ADMIN_NO ,CONTENT) VALUES (SEQ_COMMENT_NO.NEXTVAL , ?, ? , ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getBoardNo());
		pstmt.setString(2, vo.getAdminNo());
		pstmt.setString(3, vo.getContent());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}
}
