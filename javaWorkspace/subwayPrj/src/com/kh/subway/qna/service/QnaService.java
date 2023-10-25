package com.kh.subway.qna.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import com.kh.subway.main.Main;
import com.kh.subway.qna.dao.QnaDao;
import com.kh.subway.qna.vo.QnaVo;

import javaJDBCTEMPLATE.JDBCTemplate;


public class QnaService {

	//필드
	private final QnaDao dao;
	
	public QnaService() {
		dao = new QnaDao();
	}
	
	
	
	//질의 작성
	public int write(QnaVo vo) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		//DAO
		int result = dao.write(conn, vo);
		//tx(DML 실행시에만 필요)
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		//close
		JDBCTemplate.close(conn);
		
		return result;
	
	}
	
	//질의 답변 관리자
		public int reWrite(QnaVo vo) throws Exception {
			
			//conn
			Connection conn = JDBCTemplate.getConnection();
			//DAO
			int result = dao.reWrite(conn, vo);
			//tx(DML 실행시에만 필요)
			if(result == 1) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			//close
			JDBCTemplate.close(conn);
			
			return result;
		
		}
	//질의 목록(최신순)
	public List<QnaVo> writeList() throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		//DAO
		List<QnaVo> voList = dao.writeList(conn);
		//tx 리스트 받아오기때문에 필요없음
		
		//close
		JDBCTemplate.close(conn);
		
		return voList;
	}


    //질의 상세조회(번호)
	public QnaVo writeDetailByNo(String num) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		//DAO
		int result = dao.increaseInquiry(conn, num);
		QnaVo vo = dao.writeDetailByNo(conn, num);
		//tx
		
		//close
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return vo;
	}


   //질의답변 목록 조회<최신순>
	public List<QnaVo> reWriteList() throws Exception {
		       //conn
				Connection conn = JDBCTemplate.getConnection();
				//DAO
				List<QnaVo> voList = dao.reWriteList(conn);
				//tx 리스트 받아오기때문에 필요없음
				
				//close
				JDBCTemplate.close(conn);
				
				return voList;
	}



	public List<QnaVo> searchWriteByTitle(String searchValue) throws Exception {
		
				//conn
				Connection conn = JDBCTemplate.getConnection();
				
				List<QnaVo> voList = dao.searchWriteByTitle(conn, searchValue);
				
				JDBCTemplate.close(conn);
				
				return voList;
		
	}



	public QnaVo reWriteDetailByNo(String num) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//DAO
				QnaVo vo = dao.reWriteDetailByNo(conn, num);
				
       //close
				JDBCTemplate.close(conn);
				
				return vo;
		
		
	}



	public List<QnaVo> searchReWriteByTitle(String searchValue) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		List<QnaVo> voList = dao.searchReWriteByTitle(conn, searchValue);
		
		JDBCTemplate.close(conn);
		return voList;
	}



	public int writeDelete(HashMap<String, String> map) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		//dao
		int result = dao.writeDelete(conn, map);
		//tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		//close
		JDBCTemplate.close(conn);
		return result;
		
	}



	public int reWriteDelete(HashMap<String, String> map) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		//dao
		int result = dao.reWriteDelete(conn, map);
		//tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		//close
		JDBCTemplate.close(conn);
		return result;
		
	}



	public int titleCorrect(QnaVo vo) throws Exception {

		Connection conn = JDBCTemplate.getConnection();
		
		int result = 0;
		if(Main.loginUser != null) {
			result = dao.titleCorrect(conn , vo);
		}
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		
		return result;
	}



	public int contentCorrect(QnaVo vo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = 0;
		if(Main.loginUser != null) {
			result = dao.contentCorrect(conn , vo);
		}
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		
		return result;
		
		
	}



	public int reTitleCorrect(QnaVo vo) throws Exception {

		Connection conn = JDBCTemplate.getConnection();
		
		int result = 0;
		if(Main.loginAdmin != null) {
			result = dao.reTitleCorrect(conn , vo);
		}
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		
		return result;
	}



	public int reContentCorrect(QnaVo vo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = 0;
		if(Main.loginAdmin != null) {
			result = dao.reContentCorrect(conn , vo);
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

