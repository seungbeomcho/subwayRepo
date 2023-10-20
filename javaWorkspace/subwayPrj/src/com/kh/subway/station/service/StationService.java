package com.kh.subway.station.service;

import java.sql.Connection;
import java.util.List;

import com.kh.subway.station.dao.StationDao;
import com.kh.subway.station.vo.StationVo;

import javaJDBCTEMPLATE.JDBCTemplate;

public class StationService {
	//필드
	private final StationDao stationDao;
	
	//기본 생성자
	public StationService() {
		stationDao = new StationDao();
	}


	//역정보검색
	public List<StationVo> searchStationInfo(String station) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//DAO
		List<StationVo> voList = stationDao.searchStationInfo(conn, station);
		//tx
		
		//close
		JDBCTemplate.close(conn);
		return voList;
	}

}
