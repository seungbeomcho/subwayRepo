package com.kh.subway.board.service;

import java.sql.Connection;
import java.util.List;

import com.kh.subway.board.dao.BoardDao;
import com.kh.subway.board.vo.BoardVo;

import javaJDBCTEMPLATE.JDBCTemplate;

public class BoardService {
	
private final BoardDao dao;
	
	public BoardService() {
		dao = new BoardDao();
	}
	
	
	
	public int write(BoardVo vo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.write(conn , vo);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		
		return result;
	}


	//자유게시판 목록 조회( 최신순)
	public List<BoardVo> boardList() throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		List<BoardVo> voList = dao.boardList(conn);
		
		JDBCTemplate.close(conn);
		
		return voList;
		
	}
	
	
}
