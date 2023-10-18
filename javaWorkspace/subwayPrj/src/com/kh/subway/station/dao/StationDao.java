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

}
