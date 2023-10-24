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
		
		String sql = "SELECT B.BOARD_NO ,S.STATION_NAME ,B.TITLE ,B.CONTENT ,TO_CHAR(B.ENROLL_DATE , 'YY/MM/DD') AS ENROLL_DATE ,B.INQUIRY ,U.NICK AS WRITER_NICK ,(SELECT COUNT(BOARD_NO) FROM BOARD_COMMENT WHERE DELETE_YN = 'N' GROUP BY BOARD_NO) AS COMMENT_COUNT FROM BOARD B JOIN SUBWAY_USER U ON B.USER_NO = U.USER_NO JOIN STATION S ON B.STATION_NO = S.STATION_NO JOIN BOARD_COMMENT C ON B.BOARD_NO = C.BOARD_NO WHERE B.DELETE_YN = 'N' AND C.DELETE_YN = 'N' ORDER BY B.ENROLL_DATE DESC";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		List<BoardVo> voList = new ArrayList<BoardVo>();
		while(rs.next()) {
			String doardNo = rs.getString("BOARD_NO");
			String stationName = rs.getString("STATION_NAME");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String enrollDate = rs.getString("ENROLL_DATE");
			String inquiry = rs.getString("INQUIRY");
			String writerNick = rs.getString("WRITER_NICK");
			String commentCount = rs.getString("COMMENT_COUNT");
			
			BoardVo vo = new BoardVo();
			vo.setBoardNo(doardNo);
			vo.setStationName(stationName);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setEnrollDate(enrollDate);
			vo.setInquiry(inquiry);
			vo.setWriterNick(writerNick);
			vo.setCommentCount(commentCount);
			
			voList.add(vo);
			
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;
	}



	//게시판 상세 조회(BOARD_NO)
	public BoardVo boardDetailByNo(Connection conn, String no) throws Exception{
		
		String sql = "SELECT B.BOARD_NO ,S.STATION_NAME ,B.TITLE ,B.CONTENT ,TO_CHAR(B.ENROLL_DATE , 'YY/MM/DD') AS ENROLLDATE ,B.INQUIRY ,U.NICK AS WRITER_NICK ,(SELECT COUNT(BOARD_NO) FROM BOARD_COMMENT WHERE DELETE_YN = 'N' GROUP BY BOARD_NO) AS COMMENT_COUNT FROM BOARD B JOIN SUBWAY_USER U ON B.USER_NO = U.USER_NO JOIN STATION S ON B.STATION_NO = S.STATION_NO JOIN BOARD_COMMENT C ON B.BOARD_NO = C.BOARD_NO WHERE B.DELETE_YN = 'N' AND B.BOARD_NO = ?";
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
			String commentCount = rs.getString("COMMENT_COUNT");
			
			voList = new BoardVo();
			voList.setBoardNo(doardNo);
			voList.setStationName(stationName);
			voList.setTitle(title);
			voList.setContent(content);
			voList.setEnrollDate(enrollDate);
			voList.setInquiry(inquiry);
			voList.setWriterNick(writerNick);
			voList.setCommentCount(commentCount);
		}
		
		
		return voList;
	}
		//조회수 증가
		public int increaseInquiry(Connection conn, String num) throws Exception {
		
		//SQL
		String sql = "UPDATE BOARD SET INQUIRY = INQUIRY + 1 WHERE BOARD_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, num);
		int result = pstmt.executeUpdate();
		
		
		//close
		JDBCTemplate.close(pstmt);
		
		return result;
	
		}

		// 게시글 제목 수정
		public int titleModify(Connection conn, BoardVo vo) throws Exception {
			
			String sql = "UPDATE BOARD SET TITLE = ? , MODIFY_DATE = SYSDATE WHERE USER_NO = ? AND DELETE_YN = 'N'";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getUserNo());
			int result = pstmt.executeUpdate();
			
			JDBCTemplate.close(pstmt);
			
			return result;
		}
		
		// 게시글 수정 (관리자 , 제목)
		public int titleAdminModify(Connection conn, BoardVo vo) throws Exception {
			
			String sql = "UPDATE BOARD SET TITLE = ? , MODIFY_DATE = SYSDATE WHERE BOARD_NO = ? AND DELETE_YN = 'N'";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getBoardNo());
			int result = pstmt.executeUpdate();
			
			JDBCTemplate.close(pstmt);
			
			return result;

		}

		// 게시글 내용 수정
		public int contentModify(Connection conn, BoardVo vo) throws Exception {
			
			String sql = "UPDATE BOARD SET CONTENT = ? , MODIFY_DATE = SYSDATE WHERE USER_NO = ? AND DELETE_YN = 'N'";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getContent());
			pstmt.setString(2, vo.getUserNo());
			int result = pstmt.executeUpdate();
			
			JDBCTemplate.close(pstmt);
			
			return result;
		}
		
		// 게시글 수정 (관리자 , 내용)
		public int contentAdminModify(Connection conn, BoardVo vo) throws Exception {

			String sql = "UPDATE BOARD SET CONTENT = ? , MODIFY_DATE = SYSDATE WHERE BOARD_NO = ? AND DELETE_YN = 'N'";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getContent());
			pstmt.setString(2, vo.getBoardNo());
			int result = pstmt.executeUpdate();
			
			JDBCTemplate.close(pstmt);
			
			return result;
			
		}
		
		//게시판 수정 (역이름)
		public int stationNameModify(Connection conn, BoardVo vo) throws Exception {
			
			String sql = "UPDATE BOARD SET (SELECT STATION_NO FROM STATION WHERE STATION_NAME = ?) , MODIFY_DATE = SYSDATE WHERE  = ? AND DELETE_YN = 'N'";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getStationName());
			pstmt.setString(2, vo.getUserNo());
			int result = pstmt.executeUpdate();
			
			JDBCTemplate.close(pstmt);
			
			return result;
		}
		
		// 게시글 수정(관리자, 역이름)
		public int stationNameAdminModify(Connection conn, BoardVo vo) throws Exception{

			String sql = "UPDATE BOARD SET STATION_NO = (SELECT STATION_NO FROM STATION WHERE STATION_NAME = ?) , MODIFY_DATE = SYSDATE WHERE BOARD_NO = ? AND DELETE_YN = 'N'";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getStationName());
			pstmt.setString(2, vo.getBoardNo());
			int result = pstmt.executeUpdate();
			
			JDBCTemplate.close(pstmt);
			
			return result;
		}

		
		// 게시글 조회(USER_NO가 쓴글)
		public List<BoardVo> userBoardSelect(Connection conn, String userNo) throws Exception {
			String sql = "SELECT B.BOARD_NO ,S.STATION_NAME ,B.TITLE ,B.CONTENT ,TO_CHAR(B.ENROLL_DATE , 'YY/MM/DD') AS ENROLLDATE ,B.INQUIRY ,U.NICK AS WRITER_NICK FROM BOARD B JOIN SUBWAY_USER U ON B.USER_NO = U.USER_NO JOIN STATION S ON B.STATION_NO = S.STATION_NO WHERE B.USER_NO = ? AND DELETE_YN = 'N' ORDER BY B.ENROLL_DATE DESC";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userNo);
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

		// 게시글 삭제
		public int delete(Connection conn, String no) throws Exception {
			
			String sql = "UPDATE BOARD SET DELETE_YN = 'Y' WHERE BOARD_NO = ? AND DELETE_YN = 'N'";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			int result = pstmt.executeUpdate();
			
			JDBCTemplate.close(pstmt);
			
			return result;
			
		}

		//게시판 검색(제목+내용)
		public List<BoardVo> searchBoardByTitleContent(Connection conn, String searchValue) throws Exception {
			String sql = "SELECT B.BOARD_NO ,S.STATION_NAME ,B.TITLE ,B.CONTENT ,TO_CHAR(B.ENROLL_DATE , 'YY/MM/DD') AS ENROLLDATE ,B.INQUIRY ,U.NICK AS WRITER_NICK ,(SELECT COUNT(BOARD_NO) FROM BOARD_COMMENT WHERE DELETE_YN = 'N' GROUP BY BOARD_NO) AS COMMENT_COUNT FROM BOARD B JOIN SUBWAY_USER U ON B.USER_NO = U.USER_NO JOIN STATION S ON B.STATION_NO = S.STATION_NO JOIN BOARD_COMMENT C ON B.BOARD_NO = C.BOARD_NO WHERE B.DELETE_YN = 'N' AND B.TITLE LIKE '%' || ? || '%' OR B.CONTENT LIKE '%' || ? || '%' ORDER BY B.ENROLL_DATE DESC";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchValue);
			pstmt.setString(2, searchValue);
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
				String commentCount = rs.getString("COMMENT_COUNT");
				
				BoardVo vo = new BoardVo();
				vo.setBoardNo(doardNo);
				vo.setStationName(stationName);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setEnrollDate(enrollDate);
				vo.setInquiry(inquiry);
				vo.setWriterNick(writerNick);
				vo.setCommentCount(commentCount);
				
				voList.add(vo);
				
			}
			
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
			
			return voList;
		}

		//게시판 검색(역이름)
		public List<BoardVo> searchBoardByStationName(Connection conn, String searchValue) throws Exception {
			String sql = "SELECT B.BOARD_NO ,S.STATION_NAME ,B.TITLE ,B.CONTENT ,TO_CHAR(B.ENROLL_DATE , 'YY/MM/DD') AS ENROLLDATE ,B.INQUIRY ,U.NICK AS WRITER_NICK ,(SELECT COUNT(BOARD_NO) FROM BOARD_COMMENT WHERE DELETE_YN = 'N' GROUP BY BOARD_NO) AS COMMENT_COUNT FROM BOARD B JOIN SUBWAY_USER U ON B.USER_NO = U.USER_NO JOIN STATION S ON B.STATION_NO = S.STATION_NO JOIN BOARD_COMMENT C ON B.BOARD_NO = C.BOARD_NO WHERE S.STATION_NAME LIKE '%' || ? || '%' AND B.DELETE_YN = 'N' ORDER BY B.ENROLL_DATE DESC";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchValue);
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
				String commentCount = rs.getString("COMMENT_COUNT");
				
				BoardVo vo = new BoardVo();
				vo.setBoardNo(doardNo);
				vo.setStationName(stationName);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setEnrollDate(enrollDate);
				vo.setInquiry(inquiry);
				vo.setWriterNick(writerNick);
				vo.setCommentCount(commentCount);
				
				voList.add(vo);
				
			}
			
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
			
			return voList;
		}

		//게시판 검색(닉네임)
		public List<BoardVo> searchBoardByNick(Connection conn, String searchValue) throws Exception {
			String sql = "SELECT B.BOARD_NO ,S.STATION_NAME ,B.TITLE ,B.CONTENT ,TO_CHAR(B.ENROLL_DATE , 'YY/MM/DD') AS ENROLLDATE ,B.INQUIRY ,U.NICK AS WRITER_NICK ,(SELECT COUNT(BOARD_NO) FROM BOARD_COMMENT WHERE DELETE_YN = 'N' GROUP BY BOARD_NO) AS COMMENT_COUNT FROM BOARD B JOIN SUBWAY_USER U ON B.USER_NO = U.USER_NO JOIN STATION S ON B.STATION_NO = S.STATION_NO JOIN BOARD_COMMENT C ON B.BOARD_NO = C.BOARD_NO WHERE U.NICK LIKE '%' || ? || '%' AND B.DELETE_YN = 'N' ORDER BY B.ENROLL_DATE DESC";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchValue);
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
				String commentCount = rs.getString("COMMENT_COUNT");
				
				BoardVo vo = new BoardVo();
				vo.setBoardNo(doardNo);
				vo.setStationName(stationName);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setEnrollDate(enrollDate);
				vo.setInquiry(inquiry);
				vo.setWriterNick(writerNick);
				vo.setCommentCount(commentCount);
				
				voList.add(vo);
				
			}
			
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
			
			return voList;
		}







	



}
