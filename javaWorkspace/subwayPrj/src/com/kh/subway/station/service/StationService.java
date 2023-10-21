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
		
		//표시처리
		StationVo stationInfo= voList.get(0);
		
		if(stationInfo.getTransferYn().equals("Y")) {
			stationInfo.setTransferYn("환승가능");
		}else {
			stationInfo.setTransferYn("환승불가! 환승역 아님");
		}
		//tx
		
		//close
		JDBCTemplate.close(conn);
		return voList;
	}

	//시간표 조회
	public StationVo searchTimetable(StationVo timeVo) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//DAO
		StationVo rsVo = stationDao.searchTimetable(conn, timeVo);
		
		//close
		JDBCTemplate.close(conn);
		
		return rsVo;
	}//searchTimetable
	
	
//	==========================================================================

	// 역/노선 전체 조회 ( 역 개수 / 모든역 print)
	public List<StationVo> stationInfoView() throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		List<StationVo> voList = stationDao.stationInfoView(conn);
		
		// close
		JDBCTemplate.close(conn);
		
		return voList;
	}

	// 역 추가
	public int subwayStationAdd(StationVo vo) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		int result = stationDao.subwayStationAdd(conn, vo);
		
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

	// 삭제
	public int subwayStationDelete(StationVo vo) throws Exception {
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		int result = stationDao.subwayStationDelete(conn, vo);
		
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

	// 역 이름 수정
	public int stationNameEdit(StationVo vo, String editStationName) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		int result = stationDao.stationNameEdit(conn, vo, editStationName);
		
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

	// 노선번호 수정
	public int editStationNo(StationVo vo, String editStationNo) throws Exception {
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		int result = stationDao.editStationNo(conn, vo, editStationNo);
		
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

	// 환승여부 수정
	public int editTransferYn(StationVo vo, String editTransferYn) throws Exception  {
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		int result = stationDao.editTransferYn(conn, vo, editTransferYn);
		
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

	// 출구 개수 수정
	public int editExitCount(StationVo vo, String editExitCount) throws Exception {
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		int result = stationDao.editExitCount(conn, vo, editExitCount);
		
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

	// 평일 첫차시간 수정
	public int editWeekStartTime(StationVo vo, String editWeekStartTime) throws Exception {
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		int result = stationDao.editWeekStartTime(conn, vo, editWeekStartTime);
		
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

	// 평일 막차시간 수정
	public int editWeekEndTime(StationVo vo, String editWeekEndTime) throws Exception {
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		int result = stationDao.editWeekEndTime(conn, vo, editWeekEndTime);
		
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

	// 주말 첫차시간 수정
	public int editHolStartTime(StationVo vo, String editHolStartTime) throws Exception {
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		int result = stationDao.editHolStartTime(conn, vo, editHolStartTime);
		
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

	// 주말 막차시간 수정
	public int editHolEndTime(StationVo vo, String editHolEndTime) throws Exception {
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		int result = stationDao.editHolEndTime(conn, vo, editHolEndTime);
		
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

	// 화장실 내외위치 수정
	public int editTolietIo(StationVo vo, String editTolietIo) throws Exception {
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		int result = stationDao.editTolietIo(conn, vo, editTolietIo);
		
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
