package com.kh.subway.notice.service;

import java.sql.Connection;

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
	

}
