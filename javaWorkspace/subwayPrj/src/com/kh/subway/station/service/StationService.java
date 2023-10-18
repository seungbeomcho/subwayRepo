package com.kh.subway.station.service;

import java.sql.Connection;
import java.util.List;

import com.kh.subway.station.dao.StationDao;
import com.kh.subway.station.vo.StationVo;

import javaJDBCTEMPLATE.JDBCTemplate;

public class StationService {
	
	// 필드
	private final StationDao dao;

	// 생성자
	public StationService() {
		dao = new StationDao();
	}

	// 역/노선 전체 조회 ( 역 개수 / 모든역 print)
	public List<StationVo> stationInfoView() throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		List<StationVo> voList = dao.stationInfoView(conn);
		
		// close
		JDBCTemplate.close(conn);
		
		return voList;
	}

	// 역 추가
	public int subwayStationAdd(StationVo vo) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		int result = dao.subwayStationAdd(conn, vo);
		
		// tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		// close
		JDBCTemplate.close(conn);
		
		return result;
		
	}

	public int subwayStationDelete(StationVo vo) throws Exception {
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		int result = dao.subwayStationDelete(conn, vo);
		
		// tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		// close
		JDBCTemplate.close(conn);
		
		return result;
	}

}
