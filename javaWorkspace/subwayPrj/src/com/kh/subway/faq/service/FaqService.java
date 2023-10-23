package com.kh.subway.faq.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import com.kh.subway.faq.dao.FaqDao;
import com.kh.subway.faq.vo.FaqVo;

import javaJDBCTEMPLATE.JDBCTemplate;

public class FaqService {
	
	//필드
	private final FaqDao dao;
	
	//기본 생성자
	public FaqService() {
		dao = new FaqDao();
	}

	//FAQ 게시글 작성 (관리자 전용)
	public int writer(FaqVo vo) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//DAO
		int result = dao.write(conn, vo);
		
		//tx
		if(result == 1){
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		//close
		JDBCTemplate.close(conn);
		return result;

	}

	
	//FAQ 목록조회 (최신순)
	public List<FaqVo> faqList() throws Exception {

		//CONN
		Connection conn = JDBCTemplate.getConnection();
		
		//DAO
		List<FaqVo> voList = dao.faqList(conn);
		
		
		//close
		JDBCTemplate.close(conn);
		
		return voList;
	}

	
	//FAQ 상세 조회 (번호)
	public FaqVo faqDetailByNo(String num) throws Exception {

		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		int result = dao.inquiry(conn, num);
		FaqVo vo = dao.faqDetailByNo(conn, num);
		
		//tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		//close
		JDBCTemplate.close(conn);
		
		return vo;

	} //faqDetailByNo
	
	
	//FAQ 검색 (제목)
	public List<FaqVo> searchFaqByTitle(String searchValue) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		List<FaqVo> voList = dao.searchFaqByTitle(conn, searchValue);
		
		//close
		JDBCTemplate.close(conn);
		
		return voList;
		
	} //searchFaqByTitle
		
		
		//FAQ 검색 (내용)
		public List<FaqVo> searchFaqByContent(String searchValue) throws Exception {
			
			//conn
			Connection conn = JDBCTemplate.getConnection();
			
			//dao
			List<FaqVo> voList = dao.searchFaqByContent(conn, searchValue);
			
			//close
			JDBCTemplate.close(conn);
			
			return voList;
			
		} //searchFaqByContent

		
	
	//FAQ 제목 수정
		public int titleModify (FaqVo vo) throws Exception {
			
			// conn
			Connection conn = JDBCTemplate.getConnection();
			
			// DAO
			int result = dao.titleModify(conn , vo);
			
			// close
			JDBCTemplate.close(conn);
			
			return result;
			
		} //Modify
		
		

		//FAQ 내용 수정
		public int contentModify (FaqVo vo) throws Exception {
			
			// conn
			Connection conn = JDBCTemplate.getConnection();
			
			// DAO
			int result = dao.contentModify(conn , vo);
			
			// close
			JDBCTemplate.close(conn);
			
			return result;
			
		} //Modify
		

	//FAQ 삭제
	public int delete(HashMap<String, String> map) throws Exception {

		//CONN
		Connection conn = JDBCTemplate.getConnection();
		
		//DAO
		int result = dao.delete(conn, map);
		
		//TX
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		//CLOSE
		JDBCTemplate.close(conn);
		return result;
		
	} //delete

	
 } //class