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

	// 역/노선 전체 조회 ( 역 개수 / 모든역 print)
	public List<StationVo> stationInfoView(Connection conn) throws Exception {
		
		// sql
		String sql = "SELECT STATION_NAME , LINE_NO FROM STATION ORDER BY LINE_NO";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		List<StationVo> voList = new ArrayList<StationVo>();
		
		// rs
		while(rs.next()) {
			String line = rs.getString("LINE_NO");
			String stationName = rs.getString("STATION_NAME");
			
			StationVo vo = new StationVo();
			vo.setLineNo(line + "호선");
			vo.setStationName(stationName);
			
			voList.add(vo);
		}
		
		// close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;
	}

	// 역 추가
	public int subwayStationAdd(Connection conn, StationVo vo) throws Exception {
		
		// sql
		String sql = "INSERT INTO STATION (STATION_NO,STATION_NAME,LINE_NO,TRANSFER_YN,EXIT_COUNT,WEEK_START_TIME,WEEK_END_TIME,HOL_START_TIME,HOL_END_TIME,TOILET_IO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getStationNo());
		pstmt.setString(2, vo.getStationName());
		pstmt.setString(3, vo.getLineNo());
		pstmt.setString(4, vo.getTransferYn());
		pstmt.setString(5, vo.getExitCount());
		pstmt.setString(6, vo.getWeekStartTime());
		pstmt.setString(7, vo.getWeekEndTime());
		pstmt.setString(8, vo.getHolStartTime());
		pstmt.setString(9, vo.getHolEndTime());
		pstmt.setString(10, vo.getToiletIo());
		int result = pstmt.executeUpdate();
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	// 역 삭제
	public int subwayStationDelete(Connection conn, StationVo vo) throws Exception {

		// sql
		String sql = "DELETE FROM STATION WHERE STATION_NAME = ? AND LINE_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getStationName());
		pstmt.setString(2, vo.getLineNo());
		int result = pstmt.executeUpdate();
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;

	}

}
