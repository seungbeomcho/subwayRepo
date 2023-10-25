package com.kh.subway.store.service;

import java.sql.Connection;
import java.util.HashMap;
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
	
	//매장 변경사항 상세조회
	public List<SubwayVo> subwayDetailList() throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		List<SubwayVo> voList = dao.subwayDeatilList(conn);
		
		//close
		JDBCTemplate.close(conn);
		
		return voList;
	}

	//역 이름으로 근처 매장 조회
	public List<SubwayVo> subwayListByName(String name) throws Exception {
	
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		List<SubwayVo> voList = dao.subwayListByName(conn, name);
		
		//tx
		
		//close
		JDBCTemplate.close(conn);
		
		return voList;
	}

	//관리자 계정으로 매장 이름 수정
	public int changeStoreName(SubwayVo vo) throws Exception {
	
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		int result = dao.changeStoreName(conn, vo);
		
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
	
	//관리자 계정으로 매장 주소 수정
	public int changeStoreAddress(SubwayVo vo) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		int result = dao.changeStoreAddress(conn, vo);
		
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
	//폐업 매장 등록(삭제)
	public int closeStore(HashMap<String, String> map) throws Exception {

		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		int result = dao.closeStore(conn, map);
		
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

	//신규 매장 추가
	public int newStore(SubwayVo vo) throws Exception {

		//conn
		Connection conn = JDBCTemplate.getConnection();
	
		//dao
		int result = dao.newStore(conn, vo);
		
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

	//번호로 정보 조회
	public SubwayVo printStoreInfo(String storeNo) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		SubwayVo subwayVo = dao.printStoreInfo(conn, storeNo);
		
		JDBCTemplate.close(conn);
		return subwayVo;
	}


}
