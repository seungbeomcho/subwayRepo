package com.kh.subway.qna.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.kh.subway.main.Main;
import com.kh.subway.qna.vo.QnaVo;

import javaJDBCTEMPLATE.JDBCTemplate;

public class QnaDao {

	//작성하기
	public int write(Connection conn , QnaVo vo) throws Exception {
		
		//sql
		String sql = "INSERT INTO QNA(QNA_NO, TITLE, CONTENT, USER_NO) VALUES(SEQ_QNA_NO.NEXTVAL, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getContent());
		pstmt.setString(3, Main.loginUser.getUserNo());
		int result = pstmt.executeUpdate();
		//rs
		
		//close
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	
	
	//게시글 목록(최신순)
	public List<QnaVo> writeList(Connection conn) throws Exception {
		
		//sql
		String sql = "SELECT Q.QNA_NO , Q.TITLE , Q.CONTENT, U.NICK AS WRITER_NICK , Q.INQUIRY , TO_CHAR(Q.POST_TIME, 'YYYY\"년\"MM\"월\"DD\"일\"') AS POST_TIME, Q.USER_NO FROM QNA Q JOIN SUBWAY_USER U ON Q.USER_NO = U.USER_NO WHERE Q.DELETE_YN = 'N' ORDER BY Q.QNA_NO DESC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		List<QnaVo> voList = new ArrayList<QnaVo>();
		while(rs.next()) {
			String qnaNo = rs.getString("QNA_NO");
			String userNo = rs.getString("USER_NO");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String writerNick = rs.getString("WRITER_NICK");
			String inquiry = rs.getString("INQUIRY");
			String postTime = rs.getString("POST_TIME");
			
			QnaVo vo = new QnaVo();
			vo.setQnaNo(qnaNo);
			vo.setUserNo(userNo);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setWriterNick(writerNick);
			vo.setInquiry(inquiry);
			vo.setPostTime(postTime);
			
			voList.add(vo);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;
	}
	
	//게시글 상세조회(번호)
	public QnaVo writeDetailByNo(Connection conn, String num) throws Exception {
		
		//sql
	
		String sql = "SELECT Q.QNA_NO , Q.TITLE , Q.CONTENT, U.NICK AS WRITER_NICK , Q.INQUIRY , TO_CHAR(Q.POST_TIME, 'MM/DD') AS POST_TIME, Q.USER_NO FROM QNA Q JOIN SUBWAY_USER U ON Q.USER_NO = U.USER_NO WHERE Q.DELETE_YN = 'N' AND QNA_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, num);
		ResultSet rs = pstmt.executeQuery();
		QnaVo vo = null;
		
		//rs
		if(rs.next()) {
			String qnaNo = rs.getString("QNA_NO");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String writerNick = rs.getString("WRITER_NICK");
			String inquiry = rs.getString("INQUIRY");
			String postTime = rs.getString("POST_TIME");
			String userNo = rs.getString("USER_NO");
			
			vo = new QnaVo();
			vo.setQnaNo(qnaNo);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setWriterNick(writerNick);
			vo.setInquiry(inquiry);
			vo.setPostTime(postTime);
			vo.setUserNo(userNo);
		}
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return vo;
	
	}


	public int reWrite(Connection conn, QnaVo vo) throws Exception {
		//sql
				String sql = "INSERT INTO QNA(QNA_NO, RE_TITLE, RE_CONTENT, ADMIN_NO) VALUES(SEQ_QNA_NO.NEXTVAL, ?, ?, ?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getReTitle());
				pstmt.setString(2, vo.getReContent());
				pstmt.setString(3, Main.loginAdmin.getAdminNo());
				int result = pstmt.executeUpdate();
				//rs
				
				//close
				JDBCTemplate.close(pstmt);
				
				return result;
	}

	//질의답변 목록조회<최신순>
	public List<QnaVo> reWriteList(Connection conn) throws Exception {
		
		//sql
				String sql = "SELECT Q.ADMIN_NO , Q.RE_TITLE , Q.RE_CONTENT , Q.INQUIRY , TO_CHAR(Q.POST_TIME, 'YYYY\"년\"MM\"월\"DD\"일\"') AS POST_TIME, Q.USER_NO FROM QNA Q JOIN SUBWAY_USER U ON Q.USER_NO = U.USER_NO WHERE Q.DELETE_YN = 'N' ORDER BY Q.QNA_NO DESC";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				
				//rs
				List<QnaVo> voList = new ArrayList<QnaVo>();
				while(rs.next()) {
					String adminNo = rs.getString("ADMIN_NO");
					String reTitle = rs.getString("RE_TITLE");
					String reContent = rs.getString("RE_CONTENT");
					String inquiry = rs.getString("INQUIRY");
					String postTime = rs.getString("POST_TIME");
					
					QnaVo vo = new QnaVo();
					vo.setQnaNo(adminNo);
					vo.setTitle(reTitle);
					vo.setWriterNick(reContent);
					vo.setInquiry(inquiry);
					vo.setPostTime(postTime);
					
					voList.add(vo);
				}
				
				//close
				JDBCTemplate.close(rs);
				JDBCTemplate.close(pstmt);
				
				return voList;
	}

	//회원 작성글 조회
	public List<QnaVo> searchWriteByTitle(Connection conn, String searchValue) throws Exception {
		//sql
				String sql = "SELECT Q.QNA_NO , Q.USER_NO , Q.TITLE , Q.CONTENT , Q.INQUIRY , TO_CHAR(Q.POST_TIME, 'YYYY-MM-DD') AS POST_TIME FROM QNA Q JOIN SUBWAY_USER U ON Q.USER_NO = U.USER_NO WHERE Q.USER_NO = ? ORDER BY QNA_NO DESC";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, searchValue);
				ResultSet rs = pstmt.executeQuery();
				
				//rs
				List<QnaVo> voList = new ArrayList<QnaVo>();
				while(rs.next()) {
					String qnaNo = rs.getString("QNA_NO");
					String userNo = rs.getString("USER_NO");
					String title = rs.getString("TITLE");
					String content = rs.getString("CONTENT");
					String inquiry = rs.getString("INQUIRY");
					String postTime = rs.getString("POST_TIME");
					
					QnaVo vo = new QnaVo();
					vo.setQnaNo(qnaNo);
					vo.setTitle(title);
					vo.setContent(content);
					vo.setInquiry(inquiry);
					vo.setPostTime(postTime);
					
					voList.add(vo);
				}
				//close
				JDBCTemplate.close(rs);
				JDBCTemplate.close(pstmt);
				
				return voList;
	
	}


	public QnaVo reWriteDetailByNo(Connection conn, String num) throws Exception {
		
		//sql
		
			String sql = "SELECT Q.QNA_NO , Q.RE_TITLE , Q.RE_CONTENT, Q.INQUIRY , TO_CHAR(Q.POST_TIME, 'MM/DD') AS POST_TIME FROM QNA Q JOIN SUBWAY_USER U ON Q.USER_NO = U.USER_NO WHERE Q.DELETE_YN = 'N' AND QNA_NO = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			ResultSet rs = pstmt.executeQuery();
			QnaVo vo = null;
			
			//rs
			if(rs.next()) {
				String qnaNo = rs.getString("QNA_NO");
				String reTitle = rs.getString("RE_TITLE");
				String reContent = rs.getString("RE_CONTENT");
				String inquiry = rs.getString("INQUIRY");
				String postTime = rs.getString("POST_TIME");
				
				vo = new QnaVo();
				vo.setQnaNo(qnaNo);
				vo.setReTitle(reTitle);
				vo.setReContent(reContent);
				vo.setInquiry(inquiry);
				vo.setPostTime(postTime);
			}
			//close
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
			
			return vo;
		
		
	}


	public List<QnaVo> searchReWriteByTitle(Connection conn, String searchValue) throws Exception {
		
		//sql
		String sql = "SELECT Q.QNA_NO , Q.ADMIN_NO,  Q.USER_NO , Q.RE_TITLE , Q.RE_CONTENT , Q.INQUIRY , TO_CHAR(Q.POST_TIME, 'YYYY-MM-DD') AS POST_TIME FROM QNA Q JOIN SUBWAY_USER U ON Q.USER_NO = U.USER_NO WHERE Q.ADMIN_NO = ? ORDER BY QNA_NO DESC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, searchValue);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		List<QnaVo> voList = new ArrayList<QnaVo>();
		while(rs.next()) {
			String qnaNo = rs.getString("QNA_NO");
			String adminNo = rs.getString("ADMIN_NO");
			String userNo = rs.getString("USER_NO");
			String reTitle = rs.getString("RE_TITLE");
			String reContent = rs.getString("RE_CONTENT");
			String inquiry = rs.getString("INQUIRY");
			String postTime = rs.getString("POST_TIME");
			
			QnaVo vo = new QnaVo();
			vo.setQnaNo(qnaNo);
			vo.setAdminNo(adminNo);
			vo.setTitle(reTitle);
			vo.setReContent(reContent);
			vo.setInquiry(inquiry);
			vo.setPostTime(postTime);
			
			voList.add(vo);
		}
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;
		
	}


	public int writeDelete(Connection conn, HashMap<String, String> map) throws Exception {

		//sql 
				String sql = "UPDATE QNA SET DELETE_YN = 'Y' , POST_TIME = SYSDATE WHERE USER_NO = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, map.get("userNo"));
				int result = pstmt.executeUpdate();
				//rs
				
				//close
				JDBCTemplate.close(pstmt);
				
				return result;
	}


	public int reWriteDelete(Connection conn, HashMap<String, String> map) throws Exception {

		//sql 
		String sql = "UPDATE QNA SET DELETE_YN = 'Y' , POST_TIME = SYSDATE WHERE QNA_NO = ? AND ADMIN_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, map.get("qnaNum"));
		pstmt.setString(2, map.get("loginAdmin"));
		int result = pstmt.executeUpdate();
		//rs
		
		//close
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}
	
}
