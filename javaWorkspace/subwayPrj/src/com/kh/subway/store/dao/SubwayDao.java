package com.kh.subway.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.kh.subway.store.vo.SubwayVo;

import javaJDBCTEMPLATE.JDBCTemplate;

public class SubwayDao {

	//역 주위 매장 전체 조회
	public List<SubwayVo> subwayList(Connection conn) throws Exception{
		
		
		//sql
		String sql = "SELECT STORE_NO, TEL, STORE_NAME, ADDRESS FROM SUBWAY";
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
	public SubwayVo subwayListByName(Connection conn, String name) throws Exception {
	    String sql = "SELECT STORE_NO, STORE_NAME, TEL, ADDRESS FROM SUBWAY WHERE STORE_NAME LIKE ?";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, "%" + name + "%"); // '%'를 추가하여 부분 일치 검색을 수행

	    ResultSet rs = pstmt.executeQuery();

	    SubwayVo vo = null;

	    if (rs.next()) {
	        String storeNo = rs.getString("STORE_NO");
	        String storeName = rs.getString("STORE_NAME");
	        String tel = rs.getString("TEL");
	        String address = rs.getString("ADDRESS");

	        vo = new SubwayVo();
	        vo.setStoreNo(storeNo);
	        vo.setStoreName(storeName);
	        vo.setTel(tel);
	        vo.setAddress(address);
	    }

	    // close resources
	    JDBCTemplate.close(rs);
	    JDBCTemplate.close(pstmt);

	    return vo;
	}

	//관리자 계정으로 매장 정보 수정
	public int editStore(Connection conn, SubwayVo vo) throws Exception{
	
		//sql
		String sql = "UPDATE SUBWAY SET STORE_NAME = ? , ADDRESS = ? WHERE STORE_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getStoreName());
		pstmt.setString(2, vo.getAddress());
		pstmt.setString(3, vo.getStoreNo());
		int result = pstmt.executeUpdate();
		
		//rs
		
		//close
		JDBCTemplate.close(pstmt);
		return result;
	}

}
