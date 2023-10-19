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

	//자유게시판 수정(일반회원 USER_NO 로 조회해서 UPDATE)
//	public int editBoardUser(Connection conn, BoardVo vo) {
//		
//		String sql = "UPDATE BOARD SET ";
//		
//		return 0;
//	}

	//게시판 상세 조회(BOARD_NO)
	public BoardVo boardDetailByNo(Connection conn, String no) throws Exception{
		
		String sql = "SELECT B.BOARD_NO ,S.STATION_NAME ,B.TITLE ,B.CONTENT ,TO_CHAR(B.ENROLL_DATE , 'YY/MM/DD') AS ENROLLDATE ,B.INQUIRY ,U.NICK AS WRITER_NICK FROM BOARD B JOIN SUBWAY_USER U ON B.USER_NO = U.USER_NO JOIN STATION S ON B.STATION_NO = S.STATION_NO WHERE DELETE_YN = 'N' AND B.BOARD_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		ResultSet rs = pstmt.executeQuery();
		
		BoardVo voList = null;
		if(rs.next()) {
			String doardNo = rs.getString("BOARD_NO");
			String stationName = rs.getString("STATION_NAME");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String enrollDate = rs.getString("ENROLLDATE");
			String inquiry = rs.getString("INQUIRY");
			String writerNick = rs.getString("WRITER_NICK");
			
			voList = new BoardVo();
			voList.setBoardNo(doardNo);
			voList.setStationName(stationName);
			voList.setTitle(title);
			voList.setContent(content);
			voList.setEnrollDate(enrollDate);
			voList.setInquiry(inquiry);
			voList.setWriterNick(writerNick);
		}
		
		
		return voList;
	}
	
		public int increaseInquiry(Connection conn, String num) throws Exception {
		
		//SQL
		String sql = "UPDATE BOARD SET HIT = INQUIRY + 1 WHERE NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, num);
		int result = pstmt.executeUpdate();
		
		
		//close
		JDBCTemplate.close(pstmt);
		
		return result;
	
		}
}
