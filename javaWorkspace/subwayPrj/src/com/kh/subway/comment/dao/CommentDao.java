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
		String sql = "INSERT INTO BOARD_COMMENT(COMMENT_NO, BOARD_NO,CONTENT) VALUES (SEQ_COMMENT_NO.NEXTVAL , ? , ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getBoardNo());
		pstmt.setString(2, vo.getContent());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	//댓글 수정
	public int editComment(Connection conn, CommentVo vo) throws Exception{
		String sql = "UPDATE BOARD_COMMENT SET CONTENT = ? , MODIFY_DATE = SYSDATE WHERE DELETE_YN = 'N' AND COMMENT_NO = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getComment());
		pstmt.setString(2, vo.getCommentNo());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	//댓글 삭제(관리자)
	public int delete(Connection conn, CommentVo vo) throws Exception {
		String sql = "UPDATE BOARD_COMMENT SET DELETE_YN = 'Y' WHERE COMMENT_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getCommentNo());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}
	public int deleteUser(Connection conn, CommentVo vo) throws Exception {
		String sql = "UPDATE BOARD_COMMENT SET DELETE_YN = 'Y' WHERE COMMENT_NO = ? AND USER_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getCommentNo());
		pstmt.setString(2, vo.getUserNo());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}
}















