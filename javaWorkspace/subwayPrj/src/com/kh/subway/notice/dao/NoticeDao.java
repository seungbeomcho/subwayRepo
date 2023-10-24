package com.kh.subway.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.kh.subway.faq.vo.FaqVo;
import com.kh.subway.notice.vo.NoticeVo;

import javaJDBCTEMPLATE.JDBCTemplate;

public class NoticeDao {
	
	
	//공지사항 작성
	public int write(Connection conn, NoticeVo vo)throws Exception {
		
		//SQL
		String sql = "INSERT INTO NOTICE (NOTICE_NO, STATION_NO, TITLE, CONTENT) VALUES (SEQ_NOTICE_NO.NEXTVAL, (SELECT STATION_NO FROM STATION WHERE STATION_NAME = ?), ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getStationname());
		pstmt.setString(2, vo.getTitle());
		pstmt.setString(3, vo.getContent());
		int result = pstmt.executeUpdate();
		
		//RS
		
		//CLOSE
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	
	//공지사항 제목 수정
	public int titleModify(Connection conn, NoticeVo vo) throws Exception {
		
		// SQL
		String sql = "UPDATE NOTICE SET TITLE = ? , MODIFY_DATE = SYSDATE WHERE NOTICE_NO = ? AND DELETE_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getNoticeno());
		int result = pstmt.executeUpdate();
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;
		}
	
	
	//공지사항 내용 수정
	public int contentModify(Connection conn, NoticeVo vo) throws Exception {
		
		// SQL
		String sql = "UPDATE NOTICE SET CONTENT = ? , MODIFY_DATE = SYSDATE WHERE NOTICE_NO = ? AND DELETE_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getContent());
		pstmt.setString(2, vo.getNoticeno());
		int result = pstmt.executeUpdate();
		
		// close
			JDBCTemplate.close(pstmt);
			
			return result;
		}


	// 공지사항 삭제
	public int delete(Connection conn, String no) throws Exception {
		
		String sql = "UPDATE NOTICE SET DELETE_YN = 'Y' , MODIFY_DATE = SYSDATE WHERE NOTICE_NO = ? AND DELETE_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}
	
	
//	//공지사항 삭제 2
//	public int delete(Connection conn, HashMap<String, String> map) throws Exception {
//		
//		//sql
//		String sql = "UPDATE NOTICE SET DELETE_YN = 'Y' , MODIFY_DATE = SYSDATE WHERE NOTICE_NO = ? AND DELETE_YN = 'N'";
//		PreparedStatement pstmt = conn.prepareStatement(sql);
//		pstmt.setString(1, map.get("noticeNum"));
//		int result = pstmt.executeUpdate();
//		
//		//close
//		JDBCTemplate.close(pstmt);
//		
//		return result;
//	
//	}


	//공지사항 목록조회 (최신순)
	public List<NoticeVo> noticeList(Connection conn) throws Exception {
		
		//SQL
		String sql = "SELECT NOTICE_NO AS NO , TITLE , INQUIRY ,  TO_CHAR(POST_TIME, 'YYYY\"년\"MM\"월\"DD\"일\"') AS POST_TIME FROM NOTICE WHERE DELETE_YN = 'N' ORDER BY NOTICE_NO DESC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		List<NoticeVo> voList = new ArrayList<NoticeVo>();
		while(rs.next()) {
			String faqno = rs.getString("NO");
			String faqtitle = rs.getString("TITLE");
			String inquiry = rs.getString("INQUIRY");
			String posttime = rs.getString("POST_TIME");
			
			NoticeVo vo = new NoticeVo();
			vo.setNoticeno(faqno);
			vo.setTitle(faqtitle);
			vo.setInqiry(inquiry);
			vo.setPosttime(posttime);
			
			voList.add(vo);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;   
	}

	
	//공지사항 상세 조회 (번호)
	public NoticeVo noticeDetailByNo(Connection conn, String num) throws Exception {
			
		//sql
		String sql = "SELECT NOTICE_NO , STATION_NO , TITLE , INQUIRY , TO_CHAR(POST_TIME , 'YYYY/MM/DD') AS POST_TIME , CONTENT FROM NOTICE WHERE NOTICE_NO = ? AND DELETE_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, num);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		NoticeVo vo = null;
		if(rs.next()) {
			String noticeno = rs.getString("NOTICE_NO");
			String stationno = rs.getString("STATION_NO");
			String title = rs.getString("TITLE");
			String inqiry = rs.getString("INQUIRY");
			String posttime = rs.getString("POST_TIME");
			String content = rs.getString("CONTENT");
		
			vo = new NoticeVo();
			vo.setNoticeno(noticeno);
			vo.setStationno(stationno);
			vo.setTitle(title);
			vo.setInqiry(inqiry);
			vo.setPosttime(posttime);
			vo.setContent(content);
		
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return vo;
	}

	//조회수 증가
	public int inquiry(Connection conn, String num) throws Exception {
		
		//SQL
		String sql = "UPDATE NOTICE SET INQUIRY = INQUIRY+1 WHERE NOTICE_NO = ?";
		PreparedStatement pstmt =  conn.prepareStatement(sql);
		pstmt.setString(1,num);
		int result = pstmt.executeUpdate();
		
		//close
		JDBCTemplate.close(pstmt);
		
		 return result;
	}


	//공지사항 검색 (제목)
	public List<NoticeVo> searchNoticeByTitle(Connection conn, String searchValue) throws Exception {

		//sql
		String sql = "SELECT N.NOTICE_NO ,S.STATION_NAME , N.TITLE , N.INQUIRY , TO_CHAR(N.POST_TIME , 'YYYY-MM-DD') AS POST_TIME FROM NOTICE N JOIN STATION S ON N.STATION_NO = S.STATION_NO WHERE TITLE LIKE '%' || ? || '%' ORDER BY NOTICE_NO DESC";
		PreparedStatement pstmt =  conn.prepareStatement(sql);
		pstmt.setString(1, searchValue);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		List<NoticeVo> voList = new ArrayList<NoticeVo>();
		while(rs.next()) {
			String no = rs.getString("NOTICE_NO");
			String title = rs.getString("TITLE");
			String stationname = rs.getString("STATION_NAME");
			String inquiry = rs.getString("INQUIRY");
			String posttime = rs.getString("POST_TIME");
			
			NoticeVo vo = new NoticeVo();
			vo.setNoticeno(no);
			vo.setTitle(title);
			vo.setStationname(stationname);
			vo.setInqiry(inquiry);
			vo.setPosttime(posttime);
			
			voList.add(vo);
			
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;
	
	}


	//공지사항 검색 (내용)
	public List<NoticeVo> searchNoticeByContent(Connection conn, String searchValue) throws Exception {
		
		//sql
		String sql = "SELECT N.NOTICE_NO ,S.STATION_NAME , N.TITLE , N.INQUIRY , TO_CHAR(N.POST_TIME , 'YYYY-MM-DD') AS POST_TIME , N.CONTENT FROM NOTICE N JOIN STATION S ON N.STATION_NO = S.STATION_NO WHERE TITLE LIKE '%' || ? || '%' ORDER BY NOTICE_NO DESC";
		PreparedStatement pstmt =  conn.prepareStatement(sql);
		pstmt.setString(1, searchValue);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		List<NoticeVo> voList = new ArrayList<NoticeVo>();
		while(rs.next()) {
			String no = rs.getString("NOTICE_NO");
			String stationname = rs.getString("STATION_NAME");
			String title = rs.getString("TITLE");
			String inquiry = rs.getString("INQUIRY");
			String posttime = rs.getString("POST_TIME");
			String content = rs.getString("CONTENT");
			
			NoticeVo vo = new NoticeVo();
			vo.setNoticeno(no);
			vo.setTitle(title);
			vo.setStationname(stationname);
			vo.setInqiry(inquiry);
			vo.setPosttime(posttime);
			vo.setContent(content);
			
			voList.add(vo);
			
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;
	
	}
		



}//CLASS