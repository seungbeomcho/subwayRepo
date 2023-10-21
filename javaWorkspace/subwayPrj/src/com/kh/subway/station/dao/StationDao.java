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
		
		//close
		JDBCTemplate.close(resultSet);
		JDBCTemplate.close(pstmt);
		
		return rsVo;
	}
	
	//호선번호와 역번호 찾기
	public List<StationVo> searchNoInfo(Connection conn, String station) throws Exception {
		//sql
		String sql = "SELECT STATION_NO, LINE_NO FROM STATION WHERE STATION_NAME = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, station);
		ResultSet resultSet = pstmt.executeQuery();
		
		List<StationVo> voList = new ArrayList<StationVo>();
		
		//rs
		while(resultSet.next()) {
			String stationNo = resultSet.getString("STATION_NO");
			String lineNo = resultSet.getString("LINE_NO");
			
			StationVo vo = new StationVo();
			vo.setStationNo(stationNo);
			vo.setLineNo(lineNo);
			
			voList.add(vo);
		}
		
		//close
		JDBCTemplate.close(resultSet);
		JDBCTemplate.close(pstmt);
		
		return voList;
	}
	
	//하행 경유역 리스트
	public List<String> transitStationListDown(Connection conn, String startStationNo, String endStationNo) throws Exception {
		//sql
		String sql = "SELECT STATION_NAME FROM STATION WHERE STATION_NO BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, startStationNo);
		pstmt.setString(2, endStationNo);
		ResultSet resultSet = pstmt.executeQuery();
		
		List<String> transitList = new ArrayList<String>();
		
		while(resultSet.next()) {
			String transitStationName = resultSet.getString("STATION_NAME");
			
			transitList.add(transitStationName);
		}
		
		//close
		JDBCTemplate.close(resultSet);
		JDBCTemplate.close(pstmt);
		
		return transitList;
	}//transitStationListDown
	
	//상행 경유역 리스트
	public List<String> transitStationListUp(Connection conn, String startStationNo, String endStationNo) throws Exception {
		//sql
		String sql = "SELECT STATION_NAME ,STATION_NO FROM STATION WHERE STATION_NO BETWEEN ? AND ? ORDER BY STATION_NO DESC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, startStationNo);
		pstmt.setString(2, endStationNo);
		ResultSet resultSet = pstmt.executeQuery();
		
		List<String> transitList = new ArrayList<String>();
		
		while(resultSet.next()) {
			String transitStationName = resultSet.getString("STATION_NAME");
			
			transitList.add(transitStationName);
		}
		
		//close
		JDBCTemplate.close(resultSet);
		JDBCTemplate.close(pstmt);
		
		return transitList;
	}//transitStationListUp
	
	//경유역조회 
	public List<String> searchTransferStation(Connection conn, String startStation, String endStation) throws SQLException {
		//sql
		String sql = "SELECT DISTINCT STATION_NAME FROM STATION WHERE STATION_NAME "
				+ "IN ( SELECT STATION_NAME FROM STATION WHERE LINE_NO IN (SELECT LINE_NO FROM STATION WHERE STATION_NAME = ?) ) "
				+ "AND STATION_NAME IN ( SELECT STATION_NAME FROM STATION WHERE LINE_NO IN (SELECT LINE_NO FROM STATION WHERE STATION_NAME = ?) )";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, startStation);
		pstmt.setString(2, endStation);
		ResultSet resultSet = pstmt.executeQuery();
		
		List<String> transList = new ArrayList<String>();

		//rs
		while(resultSet.next()) {
			String transferStation = resultSet.getString("STATION_NAME");
			
			transList.add(transferStation);
		}
		//close
		JDBCTemplate.close(resultSet);
		JDBCTemplate.close(pstmt);
		
		return transList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
// 병합지점==============================================================================================
	
	
	// 역/노선 전체 조회 ( 역 개수 / 모든역 print)
	public List<StationVo> stationInfoView(Connection conn) throws Exception {
		
		// sql
		String sql = "SELECT STATION_NAME , LINE_NO, DEL_YN FROM STATION WHERE DEL_YN = 'N' ORDER BY LINE_NO";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		List<StationVo> voList = new ArrayList<StationVo>();
		
		// rs
		while(rs.next()) {
			String line = rs.getString("LINE_NO");
			String stationName = rs.getString("STATION_NAME");
			
			StationVo vo = new StationVo();
			vo.setLineNo(line);
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
		String sql = "UPDATE STATION SET DEL_YN = 'Y' WHERE STATION_NAME = ? AND LINE_NO = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getStationName());
		pstmt.setString(2, vo.getLineNo());
		int result = pstmt.executeUpdate();
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;

	}

	public int subwayStationEdit(Connection conn, StationVo vo, String editStationName) throws Exception {
		// sql
		String sql = "UPDATE STATION SET ? = ? WHERE STATION_NAME =? AND LINE_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, sql);
		
		pstmt.setString(11, vo.getStationName());
		pstmt.setString(12, vo.getLineNo());
		
		int result = pstmt.executeUpdate();
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	// 역 이름 수정
	public int stationNameEdit(Connection conn, StationVo vo, String editStationName) throws Exception {
		// sql
		String sql = "UPDATE STATION SET STATION_NAME = ? WHERE STATION_NAME =? AND LINE_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, editStationName);
		pstmt.setString(2, vo.getStationName());
		pstmt.setString(3, vo.getLineNo());
		
		int result = pstmt.executeUpdate();
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	// 노선번호 수정
	public int editStationNo(Connection conn, StationVo vo, String editStationNo) throws Exception {
		// sql
		String sql = "UPDATE STATION SET STATION_NO = ? WHERE STATION_NAME =? AND LINE_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, editStationNo);
		pstmt.setString(2, vo.getStationName());
		pstmt.setString(3, vo.getLineNo());
		
		int result = pstmt.executeUpdate();
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	// 환승여부 수정
	public int editTransferYn(Connection conn, StationVo vo, String editTransferYn) throws Exception {
		// sql
		String sql = "UPDATE STATION SET TRANSFER_YN = ? WHERE STATION_NAME =? AND LINE_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, editTransferYn);
		pstmt.setString(2, vo.getStationName());
		pstmt.setString(3, vo.getLineNo());
		
		int result = pstmt.executeUpdate();
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	// 출구 개수 수정
	public int editExitCount(Connection conn, StationVo vo, String editExitCount) throws Exception {
		String sql = "UPDATE STATION SET EXIT_COUNT = ? WHERE STATION_NAME =? AND LINE_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, editExitCount);
		pstmt.setString(2, vo.getStationName());
		pstmt.setString(3, vo.getLineNo());
		
		int result = pstmt.executeUpdate();
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	// 평일 첫차시간 수정
	public int editWeekStartTime(Connection conn, StationVo vo, String editWeekStartTime) throws Exception {
		String sql = "UPDATE STATION SET WEEK_START_TIME = ? WHERE STATION_NAME =? AND LINE_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, editWeekStartTime);
		pstmt.setString(2, vo.getStationName());
		pstmt.setString(3, vo.getLineNo());
		
		int result = pstmt.executeUpdate();
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	// 평일 막차시간 수정
	public int editWeekEndTime(Connection conn, StationVo vo, String editWeekEndTime) throws Exception {
		String sql = "UPDATE STATION SET WEEK_END_TIME = ? WHERE STATION_NAME =? AND LINE_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, editWeekEndTime);
		pstmt.setString(2, vo.getStationName());
		pstmt.setString(3, vo.getLineNo());
		
		int result = pstmt.executeUpdate();
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	// 주말 첫차시간 수정
	public int editHolStartTime(Connection conn, StationVo vo, String editHolStartTime) throws Exception {
		String sql = "UPDATE STATION SET HOL_START_TIME = ? WHERE STATION_NAME =? AND LINE_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, editHolStartTime);
		pstmt.setString(2, vo.getStationName());
		pstmt.setString(3, vo.getLineNo());
		
		int result = pstmt.executeUpdate();
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	// 주말 막차시간 수정
	public int editHolEndTime(Connection conn, StationVo vo, String editHolEndTime) throws Exception {
		String sql = "UPDATE STATION SET HOL_END_TIME = ? WHERE STATION_NAME =? AND LINE_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, editHolEndTime);
		pstmt.setString(2, vo.getStationName());
		pstmt.setString(3, vo.getLineNo());
		
		int result = pstmt.executeUpdate();
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	public int editTolietIo(Connection conn, StationVo vo, String editTolietIo) throws Exception {
		String sql = "UPDATE STATION SET TOILET_IO = ? WHERE STATION_NAME =? AND LINE_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, editTolietIo);
		pstmt.setString(2, vo.getStationName());
		pstmt.setString(3, vo.getLineNo());
		
		int result = pstmt.executeUpdate();
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;
	}
}
