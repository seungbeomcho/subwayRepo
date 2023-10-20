package com.kh.subway.qna.service;

import java.sql.Connection;
import java.util.List;

import com.kh.subway.qna.dao.QnaDao;
import com.kh.subway.qna.vo.QnaVo;

import javaJDBCTEMPLATE.JDBCTemplate;


public class QnaService {

	//필드
	private final QnaDao dao;
	
	public QnaService() {
		dao = new QnaDao();
	}
	
	
	
	//게시글 작성
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
	
	//게시글 답변 관리자
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
	//게시글 목록(최신순)
	public List<QnaVo> qnaList() throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		//DAO
		List<QnaVo> voList = dao.qnaList(conn);
		//tx 리스트 받아오기때문에 필요없음
		
		//close
		JDBCTemplate.close(conn);
		
		return voList;
	}


    //게시글 상세조회(번호)
	public QnaVo qnaDetailByNo(String num) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		//DAO
		QnaVo vo = dao.qnaDetailByNo(conn, num);
		//tx
		
		//close
		JDBCTemplate.close(conn);
		
		return vo;
	}

}

