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
	
	
	//게시글 작성
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


	// 상세 조회 (Board_NO)
	public BoardVo boardDetailByNo(String no) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.increaseInquiry(conn, no);
		BoardVo voList = dao.boardDetailByNo(conn , no);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return voList;
	}


	// 게시글 제목 수정
	public int titleModify(BoardVo vo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.titleModify(conn , vo);
		
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		
		return result;
	}


	// 게시글 내용 수정
	public int contentModify(BoardVo vo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		
		int result = dao.contentModify(conn , vo);
		
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		
		return result;
	}


	// 게시글 조회(USER_NO가 쓴글)
	public List<BoardVo> userBoardSelect(String userNo) throws Exception{
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.increaseInquiry(conn, userNo);
		List<BoardVo> voList = dao.userBoardSelect(conn , userNo);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return voList;
	}


	//게시글 삭제
	public int delete(String no) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.delete(conn , no);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
			
		return result;
	}
	
	
}
