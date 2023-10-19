package com.kh.subway.faq.service;

import java.sql.Connection;
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

	public int writer(FaqVo vo) {
		// TODO Auto-generated method stub
		return 0;
	}

//	//faq 목록 (최신순)
//	public List<FaqVo> faqList(){
//		
//		//conn
//		Connection conn = JDBCTemplate.getConnection();
//		
//		//dao
//		List<FaqVo> voList = dao.faqList(conn);
//		
//		//tx
//		
//		//close
//	}
	
	
	
	
	
	//faq 상세 조회 (번호)
//	public FaqVo faqDetailByNo(String num) throws Exception {
//		
//		//conn
//		Connection conn = JDBCTemplate.getConnection();
//		
//		//dao
//		int result 
//		FaqVo vo = dao.faqDetailByNo(conn, num);
//		
//		//tx
//		if(result == 1) {
//			JDBCTemplate.commit(conn);
//		}else {
//			JDBCTemplate.rollback(conn);
//		}
		
		//rs
	}
// }
