package com.kh.subway.faq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
		String sql = "SELECT F.FAQ_NO AS NO, F.FAQ_TITLE AS TITLE, F.INQUIRY,  TO_CHAR(F.POST_TIME, 'YYYY\"년\"MM\"월\"DD\"일\"') AS POST_TIME FROM FAQ F ORDER BY FAQ_NO DESC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		List<FaqVo> voList = new ArrayList<FaqVo>();
		while(rs.next()) {
			String faqno = rs.getString("NO");
			String faqtitle = rs.getString("TITLE");
			String inquiry = rs.getString("INQUIRY");
			String posttime = rs.getString("POST_TIME");
			
			FaqVo vo = new FaqVo();
			vo.setFaqno(faqno);
			vo.setFaqtitle(faqtitle);
			vo.setInquiry(inquiry);
			vo.setPosttime(posttime);
			
			voList.add(vo);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;   
		
	}

	//FAQ 상세 조회 (번호)
	public FaqVo faqDetailByNo(Connection conn, String num) throws Exception {
		System.out.println("메인DAO호출");

		//sql
		String sql = "SELECT F.FAQ_NO, F.STATION_NO, F.FAQ_TITLE, F.INQUIRY, TO_CHAR(F.POST_TIME, 'YYYY/MM/DD') AS POST_TIME, F.CONTENT FROM FAQ F JOIN STATION S ON F.STATION_NO = S.STATION_NO WHERE F.FAQ_NO = ? AND F.DELETE_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, num);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		FaqVo vo = null;
		if(rs.next()) {
			String faqno = rs.getString("FAQ_NO");
			String stationno = rs.getString("STATION_NO");
			String faqtitle = rs.getString("FAQ_TITLE");
			String inquiry = rs.getString("INQUIRY");
			String posttime = rs.getString("POST_TIME");
			String content = rs.getString("CONTENT");
			
			vo = new FaqVo();
			vo.setFaqno(faqno);
			vo.setStationno(stationno);
			vo.setFaqtitle(faqtitle);
			vo.setInquiry(inquiry);
			vo.setPosttime(posttime);
			vo.setContent(content);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return vo;
		
	}

	//조회수 증가
	public int inquiry(Connection conn, String num) throws Exception  {
		System.out.println("인쿼리 DAO 호출");
		//sql
		String sql = "UPDATE FAQ SET INQUIRY = INQUIRY+1 WHERE FAQ_NO = ?";
		PreparedStatement pstmt =  conn.prepareStatement(sql);
		pstmt.setString(1, num);
		int result = pstmt.executeUpdate();
		
		//close
		JDBCTemplate.close(pstmt);
		return result;
	}

	//게시글 삭제
	public int delete(Connection conn, HashMap<String, String> map)throws Exception {

		//sql
		String sql = "UPDATE FAQ SET DELETE_YN = 'Y' , MODIFY_DATE = SYSDATE WHERE FAQ_NO = ? AND DELETE_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, map.get("faqno"));
		int result = pstmt.executeUpdate();
		
		
		//close
		JDBCTemplate.close(pstmt);
		
		return result;
		
	
	}

	
	
	
	
	
	
}//class
