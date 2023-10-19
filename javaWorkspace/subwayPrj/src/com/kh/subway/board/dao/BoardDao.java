package com.kh.subway.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kh.subway.board.vo.BoardVo;

import javaJDBCTEMPLATE.JDBCTemplate;

public class BoardDao {
	
	//자유게시판 작성
	public int write(Connection conn, BoardVo vo) throws Exception {
		
		String sql = "INSERT INTO BOARD(BOARD_NO , STATION_NO, USER_NO , TITLE, CONTENT) VALUES (SEQ_BOARD_NO.NEXTVAL , (SELECT STATION_NO FROM STATION WHERE STATION_NAME = ?) , ? , ? ,?)";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, vo.getStationName());
		pstmt.setString(2, vo.getUserNo());
		pstmt.setString(3, vo.getTitle());
		pstmt.setString(4, vo.getContent());
		int result = pstmt.executeUpdate();
		
		return result;
	}
	
	
	//자유게시판 목록 조회( 최신순)
	public List<BoardVo> boardList(Connection conn) throws Exception {
		
		String sql = "SELECT B.BOARD_NO ,S.STATION_NAME ,B.TITLE ,B.CONTENT ,TO_CHAR(B.ENROLL_DATE , 'YY/MM/DD') AS ENROLLDATE ,B.INQUIRY ,U.NICK AS WRITER_NICK FROM BOARD B JOIN SUBWAY_USER U ON B.USER_NO = U.USER_NO JOIN STATION S ON B.STATION_NO = S.STATION_NO WHERE DELETE_YN = 'N' ORDER BY B.ENROLL_DATE DESC";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		List<BoardVo> voList = new ArrayList<BoardVo>();
		while(rs.next()) {
			String doardNo = rs.getString("BOARD_NO");
			String stationName = rs.getString("STATION_NAME");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String enrollDate = rs.getString("ENROLLDATE");
			String inquiry = rs.getString("INQUIRY");
			String writerNick = rs.getString("WRITER_NICK");
			
			BoardVo vo = new BoardVo();
			vo.setBoardNo(doardNo);
			vo.setStationName(stationName);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setEnrollDate(enrollDate);
			vo.setInquiry(inquiry);
			vo.setWriterNick(writerNick);
			
			voList.add(vo);
			
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;
	}
	
	
	
}
