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
		System.out.println("서비스 호출");
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
		
	}

	

 }
