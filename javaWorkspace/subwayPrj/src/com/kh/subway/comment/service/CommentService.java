package com.kh.subway.comment.service;

import java.sql.Connection;

import com.kh.subway.comment.dao.CommentDao;

import javaJDBCTEMPLATE.JDBCTemplate;

public class CommentService {
	
	private CommentDao dao;
	
	public CommentService() {
		dao = new CommentDao();
	}
	
	//댓글 입력
	public int leaveComment(String comment) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.leaveComment(conn , comment);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	
	}
}
