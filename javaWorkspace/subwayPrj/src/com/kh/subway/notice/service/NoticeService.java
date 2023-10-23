package com.kh.subway.notice.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import com.kh.subway.faq.vo.FaqVo;
import com.kh.subway.notice.dao.NoticeDao;
import com.kh.subway.notice.vo.NoticeVo;

import javaJDBCTEMPLATE.JDBCTemplate;

public class NoticeService {
	
	//필드
	private final NoticeDao dao;
	
	//기본생성자
	public NoticeService() {
		dao = new NoticeDao();
	}

	
	//공지사항 작성
	public int writer(NoticeVo vo) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//DAO
		int result = dao.write(conn, vo);
		
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

	
	//공지사항 수정
	public int Modify(NoticeVo vo) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// DAO
		int result = dao.Modify(conn , vo);
		
		// close
		JDBCTemplate.close(conn);
		
		return result;
		
	}

	
	//공지사항 삭제
	public int delete(HashMap<String, String> map) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
				
		int result = dao.delete(conn , map);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
			
		return result;
	}

	
	//공지사항 목록조회 (최신순)
	public List<NoticeVo> noticeList() throws Exception {

		//CONN
		Connection conn = JDBCTemplate.getConnection();
		
		//DAO
		List<NoticeVo> voList = dao.noticeList(conn);
		
		
		//close
		JDBCTemplate.close(conn);
		
		return voList;
	}

	
	//공지사항 상세 조회 (번호)
	public NoticeVo noticeDetailByNo(String num) throws Exception {
		
		// conn
		Connection conn =  JDBCTemplate.getConnection();
		
		// DAO 
		int result = dao.inquiry(conn, num);
		NoticeVo vo = dao.noticeDetailByNo(conn, num);
		
		// tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		//close
		JDBCTemplate.close(conn);
		
		return vo;
		
	}

	
	//공지사항 검색 (제목)
	public List<NoticeVo> searchNoticeByTitle(String searchValue) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		List<NoticeVo> voList = dao.searchNoticeByTitle(conn, searchValue);
		
		//close
		JDBCTemplate.close(conn);
		
		return voList;
	}

	
	//공지사항 검색 (내용)
	public List<NoticeVo> searchNoticeByContent(String searchValue) throws Exception {
			
			//conn
			Connection conn = JDBCTemplate.getConnection();
			
			//dao
			List<NoticeVo> voList = dao.searchNoticeByContent(conn, searchValue);
			
			//close
			JDBCTemplate.close(conn);
			
			return voList;
		}



}//class
