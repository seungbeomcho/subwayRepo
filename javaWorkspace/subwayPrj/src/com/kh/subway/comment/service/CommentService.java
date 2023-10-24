package com.kh.subway.comment.service;

import java.sql.Connection;

import com.kh.subway.comment.dao.CommentDao;
import com.kh.subway.comment.vo.CommentVo;
import com.kh.subway.main.Main;

import javaJDBCTEMPLATE.JDBCTemplate;

public class CommentService {
	
	private CommentDao dao;
	
	public CommentService() {
		dao = new CommentDao();
	}
	
	//댓글 입력
	public int leaveComment(CommentVo vo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = 0;
		if(Main.loginUser != null) {
			result = dao.leaveCommentUser(conn , vo);
		}else if(Main.loginAdmin !=null) {
			result = dao.leaveCommentAdmin(conn , vo);
		}
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	
	}
}
