package com.kh.subway.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.kh.subway.store.vo.SubwayVo;
import javaJDBCTEMPLATE.JDBCTemplate;

public class SubwayDao {

	//역 주위 매장 전체 조회
	public List<SubwayVo> subwayList(Connection conn) throws Exception{
		
		
		//sql
		String sql = "SELECT STORE_NO, TEL, STORE_NAME, ADDRESS FROM SUBWAY ORDER BY STORE_NO ASC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		List<SubwayVo> voList = new ArrayList<SubwayVo>();
		while(rs.next()) {
			String storeNo = rs.getString("STORE_NO");
			String tel = rs.getString("TEL");
			String storeName = rs.getString("STORE_NAME");
			String address = rs.getString("ADDRESS");
			
			SubwayVo vo = new SubwayVo();
			vo.setStoreNo(storeNo);
			vo.setTel(tel);
			vo.setStoreName(storeName);
			vo.setAddress(address);
			
			voList.add(vo);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;
	}

	//입력한 역 주위 매장 조회
	public List<SubwayVo> subwayListByName(Connection conn, String name) throws Exception {
	    String sql = "SELECT S.STORE_NO, S.STORE_NAME, S.TEL, S.ADDRESS FROM SUBWAY S JOIN STATION T ON S.STATION_NO = T.STATION_NO WHERE T.STATION_NAME LIKE '%' || ? || '%'";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, name); 
	    ResultSet rs = pstmt.executeQuery();

	    List<SubwayVo> voList = new ArrayList<SubwayVo>();

	    while(rs.next()) {
	    	
	        String storeNo = rs.getString("STORE_NO");
	        String storeName = rs.getString("STORE_NAME");
	        String tel = rs.getString("TEL");
	        String address = rs.getString("ADDRESS");

	        SubwayVo vo = new SubwayVo();
	        vo.setStoreNo(storeNo);
	        vo.setStoreName(storeName);
	        vo.setTel(tel);
	        vo.setAddress(address);
	        
	        voList.add(vo);
	    }

	    // close resources
	    JDBCTemplate.close(rs);
	    JDBCTemplate.close(pstmt);

	    return voList;
	}

	//관리자 계정으로 매장 이름 수정
	public int changeStoreName(Connection conn, SubwayVo vo) throws Exception{
	
		//sql
		String sql = "UPDATE SUBWAY SET STORE_NAME = ? WHERE STORE_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getStoreName());
		pstmt.setString(2, vo.getStoreNo());
		int result = pstmt.executeUpdate();
		
		//rs
		
		//close
		JDBCTemplate.close(pstmt);
		return result;
	}
	
	//관리자 게정으로 매장 주소 수정
	public int changeStoreAddress(Connection conn, SubwayVo vo) throws Exception {
		
		//sql
		String sql = "UPDATE SUBWAY SET ADDRESS = ? WHERE STORE_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getAddress());
		pstmt.setString(2, vo.getStoreNo());
		int result = pstmt.executeUpdate();
		
		//rs
		
		//close
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	
	//폐업 매장 등록(삭제)
	public int closeStore(Connection conn, HashMap<String, String> map) throws Exception {

		//sql
		String sql = "UPDATE SUBWAY SET DEL_YN = 'Y' ,CLOSE_DATE = SYSDATE ,MODIFY_DATE = SYSDATE WHERE STORE_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, map.get("storeNo"));
		int result = pstmt.executeUpdate();
		
		//rs
		
		//close
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	
	//신규 매장 추가
	public int newStore(Connection conn, SubwayVo vo) throws Exception {
		
		//sql
		String sql = "INSERT INTO SUBWAY(STORE_NO, STORE_NAME, TEL, ADDRESS) VALUES (?, ?, ?, ?) ORDER BY STORE_NO ASC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getStoreNo());
		pstmt.setString(2, vo.getStoreName());
		pstmt.setString(3, vo.getTel());
		pstmt.setString(4, vo.getAddress());
		int result = pstmt.executeUpdate();
		//rs
		
		//close
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}
	

}
