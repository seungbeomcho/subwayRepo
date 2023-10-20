package com.kh.subway.faq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kh.subway.faq.vo.FaqVo;

import javaJDBCTEMPLATE.JDBCTemplate;

public class FaqDao {


	//FAQ 게시글 작성 (관리자 전용)
	public int write(Connection conn, FaqVo vo) throws Exception {
		
		//SQL
		String sql = "INSERT INTO FAQ (FAQ_NO, STATION_NO, FAQ_TITLE, CONTENT) VALUES (SEQ_FAQ_NO.NEXTVAL, (SELECT STATION_NO FROM STATION WHERE STATION_NAME = ?), ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getStationno());
		pstmt.setString(2, vo.getFaqtitle());
		pstmt.setString(3, vo.getContent());
		int result = pstmt.executeUpdate();
		
		//RS
		
		//CLOSE
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}
	
	//faq 목록 (최신순)
	public List<FaqVo> faqList (Connection conn)throws Exception{
		
	
		//SQL
		String sql = "SELECT FAQ_NO, FAQ_TITLE, POST_TIME FROM FAQ ORDER BY FAQ_NO DESC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		List<FaqVo> voList = new ArrayList<FaqVo>();
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;   
		
		
		
		
		
	}

	
	}
	
	
	
	
//}//class
