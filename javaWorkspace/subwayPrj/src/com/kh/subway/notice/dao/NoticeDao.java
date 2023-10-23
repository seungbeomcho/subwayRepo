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
		pstmt.setString(1, vo.getStationno());
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
//		pstmt.setString(2, vo.getContent());
		pstmt.setString(3, vo.getNoticeno());
//		pstmt.setString(4, vo.getAdminno());
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
//		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getContent());
		pstmt.setString(3, vo.getNoticeno());
//		pstmt.setString(4, vo.getAdminno());
		int result = pstmt.executeUpdate();
		
		// close
			JDBCTemplate.close(pstmt);
			
			return result;
		}


	//공지사항 삭제
	public int delete(Connection conn, HashMap<String, String> map) throws Exception {
		
		String sql = "UPDATE NOTICE SET DELETE_YN = 'Y' , MODIFY_DATE = SYSDATE WHERE NOTICE_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, map.get("noticeno"));
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}


	//공지사항 목록조회 (최신순)
	public List<NoticeVo> noticeList(Connection conn) throws Exception {
		
		//SQL
		String sql = "SELECT NOTICE_NO AS NO , TITLE , INQUIRY ,  TO_CHAR(POST_TIME, 'YYYY\"년\"MM\"월\"DD\"일\"') AS POST_TIME FROM NOTICE ORDER BY NOTICE_NO DESC";
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

	
	//FAQ 상세 조회 (번호)
	public NoticeVo noticeDetailByNo(Connection conn, String num) throws Exception {
			
		//sql
		String sql = "SELECT NOTICE_NO , TITLE , ADMIN_NO , INQUIRY , TO_CHAR(POST_TIME , 'MM/DD') AS POST_TIME , MODIFY_DATE , CONTENT FROM NOTICE WHERE NOTICE_NO = ? AND DELETE_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, num);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		NoticeVo vo = null;
		if(rs.next()) {
			String no = rs.getString("NOTICE_NO");
			String title = rs.getString("TITLE");
			String inqiry = rs.getString("INQUIRY");
			String enrollDate = rs.getString("POST_TIME");
			String modifyDate = rs.getString("MODIFY_DATE");
			String content = rs.getString("CONTENT");
		
			vo = new NoticeVo();
			vo.setNoticeno(no);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setInqiry(inqiry);
			vo.setPosttime(enrollDate);
			vo.setModifydate(modifyDate);
		
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
		String sql = "SELECT NOTICE_NO , TITLE , INQUIRY , TO_CHAR(POST_TIME , 'YYYY-MM-DD') AS POST_TIME FROM NOTICE WHERE TITLE LIKE '%' || ? || '%' ORDER BY NOTICE_NO DESC";
		PreparedStatement pstmt =  conn.prepareStatement(sql);
		pstmt.setString(1, searchValue);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		List<NoticeVo> voList = new ArrayList<NoticeVo>();
		while(rs.next()) {
			String no = rs.getString("NOTICE_NO");
			String title = rs.getString("TITLE");
			String inquiry = rs.getString("INQUIRY");
			String posttime = rs.getString("POST_TIME");
			
			NoticeVo vo = new NoticeVo();
			vo.setNoticeno(no);
			vo.setTitle(title);
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
		String sql = "SELECT NOTICE_NO , TITLE , INQUIRY , TO_CHAR(POST_TIME , 'YYYY-MM-DD') AS POST_TIME , CONTENT FROM NOTICE WHERE TITLE LIKE '%' || ? || '%' ORDER BY NOTICE_NO DESC";
		PreparedStatement pstmt =  conn.prepareStatement(sql);
		pstmt.setString(1, searchValue);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		List<NoticeVo> voList = new ArrayList<NoticeVo>();
		while(rs.next()) {
			String no = rs.getString("NOTICE_NO");
			String title = rs.getString("TITLE");
			String inquiry = rs.getString("INQUIRY");
			String posttime = rs.getString("POST_TIME");
			String content = rs.getString("CONTENT");
			
			NoticeVo vo = new NoticeVo();
			vo.setNoticeno(no);
			vo.setTitle(title);
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
