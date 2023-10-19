package com.kh.subway.store.service;

import java.sql.Connection;
import java.util.List;

import com.kh.subway.store.dao.SubwayDao;
import com.kh.subway.store.vo.SubwayVo;
import javaJDBCTEMPLATE.JDBCTemplate;

public class SubwayService {

	//필드
	private final SubwayDao dao;
	
	//기본 생성자
	public SubwayService() {
		dao = new SubwayDao();
	}
	
	//역 주위 서브웨이 전체 매장 조회
	public List<SubwayVo> subwayList() throws Exception {
	
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		List<SubwayVo> voList = dao.subwayList(conn);
		
		//close
		JDBCTemplate.close(conn);
		
		return voList;
	}

	//역 이름으로 근처 매장 조회
	public SubwayVo subwayListByName(String name) throws Exception {
	
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		SubwayVo vo = dao.subwayListByName(conn, name);
		
		//tx
		
		//close
		JDBCTemplate.close(conn);
		
		return vo;
	}

	//관리자 계정으로 매장 정보 수정
	public int editStore(SubwayVo vo) throws Exception {
	
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		int result = dao.editStore(conn, vo);
		
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
