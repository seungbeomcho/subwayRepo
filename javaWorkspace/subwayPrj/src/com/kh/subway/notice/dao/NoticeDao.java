package com.kh.subway.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.kh.subway.notice.vo.NoticeVo;

import javaJDBCTEMPLATE.JDBCTemplate;

public class NoticeDao {
	
	
	//공지사항 작성
	public int write(Connection conn, NoticeVo vo)throws Exception {
		
		//SQL
		String sql = "INSERT INTO NOTICE (NOTICE_NO, STATION_NO, TITLE, CONTENT, INQUIRY) VALUES (SEQ_NOTICE_NO.NEXTVAL, (SELECT STATION_NO FROM STATION WHERE STATION_NAME = ?), ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getStationno());
		pstmt.setString(2, vo.getTitle());
		pstmt.setString(3, vo.getContent());
		int result = pstmt.executeUpdate();
		
		//RS
		
		//CLOSE
		JDBCTemplate.close(pstmt);
		
		return result;
	}

}
