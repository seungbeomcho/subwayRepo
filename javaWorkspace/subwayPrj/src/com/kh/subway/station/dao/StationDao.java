package com.kh.subway.station.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.subway.station.vo.StationVo;

import javaJDBCTEMPLATE.JDBCTemplate;

public class StationDao {

	//역정보검색
	public List<StationVo> searchStationInfo(Connection conn, String station) throws Exception {
		//sql
		String sql = "SELECT STATION_NAME ,LINE_NO ,TRANSFER_YN ,EXIT_COUNT ,TOILET_IO FROM STATION WHERE STATION_NAME = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, station);
		ResultSet resultSet = pstmt.executeQuery();
		
		//rs
		List<StationVo> voList = new ArrayList<StationVo>();
		while(resultSet.next()) {
			String stationName = resultSet.getString("STATION_NAME");
			String lineNo = resultSet.getString("LINE_NO");
			String transferYn = resultSet.getString("TRANSFER_YN");
			String exitCount = resultSet.getString("EXIT_COUNT");
			String toiletIo = resultSet.getString("TOILET_IO");
			
			StationVo vo = new StationVo();
			
			vo.setStationName(stationName);
			vo.setLineNo(lineNo);
			vo.setTransferYn(transferYn);
			vo.setExitCount(exitCount);
			vo.setToiletIo(toiletIo);
			
			voList.add(vo);
		}
		
		//close
		JDBCTemplate.close(pstmt);
		
		return voList;
	}//searchStationInfo

	//시간표 조회
	public StationVo searchTimetable(Connection conn, StationVo timeVo) throws Exception {
		
		String sql = "SELECT WEEK_START_TIME ,WEEK_END_TIME ,HOL_START_TIME ,HOL_END_TIME FROM STATION WHERE STATION_NAME = ? AND LINE_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, timeVo.getStationName());
		pstmt.setString(2, timeVo.getLineNo());
		ResultSet resultSet = pstmt.executeQuery();
	    
		StationVo rsVo = new StationVo();
		if(!resultSet.next()) {
			throw new Exception();
		}
		String weekStime = resultSet.getString("WEEK_START_TIME");
		String weekEtime = resultSet.getString("WEEK_END_TIME");
		String holStime = resultSet.getString("HOL_START_TIME");
		String holEtime = resultSet.getString("HOL_END_TIME");
		
		rsVo.setWeekStartTime(weekStime);
		rsVo.setWeekEndTime(weekEtime);
		rsVo.setHolStartTime(holStime);
		rsVo.setHolEndTime(holEtime);
		
		
		
		return rsVo;
	}

}
