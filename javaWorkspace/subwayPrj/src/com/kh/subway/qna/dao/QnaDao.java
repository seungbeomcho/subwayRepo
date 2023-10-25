package com.kh.subway.qna.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.kh.subway.board.vo.BoardVo;
import com.kh.subway.main.Main;
import com.kh.subway.qna.vo.QnaVo;

import javaJDBCTEMPLATE.JDBCTemplate;

public class QnaDao {

	//작성하기
	public int write(Connection conn , QnaVo vo) throws Exception {
		
		//sql
		String sql = "INSERT INTO QNA(QNA_NO, STATION_NO, USER_NO, TITLE, CONTENT) VALUES(SEQ_QNA_NO.NEXTVAL,(SELECT STATION_NO FROM STATION WHERE STATION_NAME = ?), ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getStationNo());
		pstmt.setString(2, Main.loginUser.getUserNo());
		pstmt.setString(3, vo.getTitle());
		pstmt.setString(4, vo.getContent());
		int result = pstmt.executeUpdate();
		//rs
		//close
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	
	
	//게시글 목록(최신순)
	public List<QnaVo> writeList(Connection conn) throws Exception {
		
		//sql
		String sql = "SELECT Q.QNA_NO , Q.USER_NO, U.NICK AS WRITER_NICK,  S.STATION_NO, S.STATION_NAME , Q.TITLE ,Q.CONTENT ,Q.INQUIRY ,Q.POST_TIME, Q.DELETE_YN FROM QNA Q JOIN STATION S ON Q.STATION_NO = S.STATION_NO JOIN SUBWAY_USER U ON Q.USER_NO = U.USER_NO WHERE DELETE_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		List<QnaVo> voList = new ArrayList<QnaVo>();
		while(rs.next()) {
			String qnaNo = rs.getString("QNA_NO");
			String userNo = rs.getString("USER_NO");
			String writerNick = rs.getString("WRITER_NICK");
			String stationNo = rs.getString("STATION_NO");
			String stationName = rs.getString("STATION_NAME");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String inquiry = rs.getString("INQUIRY");
			String postTime = rs.getString("POST_TIME");
			String deleteYn = rs.getString("DELETE_YN");
			
			QnaVo vo = new QnaVo();
			vo.setQnaNo(qnaNo);
			vo.setUserNo(userNo);
			vo.setWriterNick(writerNick);
			vo.setStationNo(stationNo);
			vo.setStationName(stationName);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setInquiry(inquiry);
			vo.setPostTime(postTime);
			vo.setDeleteYn(deleteYn);
			
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
	
		String sql = "SELECT Q.QNA_NO, S.STATION_NAME, Q.USER_NO, Q.TITLE , Q.CONTENT, U.NICK AS WRITER_NICK , Q.INQUIRY , TO_CHAR(Q.POST_TIME, 'MM/DD') AS POST_TIME FROM QNA Q JOIN SUBWAY_USER U ON Q.USER_NO = U.USER_NO JOIN STATION S ON Q.STATION_NO = S.STATION_NO WHERE Q.DELETE_YN = 'N' AND QNA_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, num);
		ResultSet rs = pstmt.executeQuery();
		QnaVo vo = null;
		
		//rs
		if(rs.next()) {
			String qnaNo = rs.getString("QNA_NO");
			String userNo = rs.getString("USER_NO");
			String stationName = rs.getString("STATION_NAME");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String writerNick = rs.getString("WRITER_NICK");
			String inquiry = rs.getString("INQUIRY");
			String postTime = rs.getString("POST_TIME");
			
			vo = new QnaVo();
			vo.setQnaNo(qnaNo);
			vo.setTitle(title);
			vo.setStationName(stationName);
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
				String sql = "INSERT INTO QNA_ADMIN(QNA_NO, RE_TITLE, RE_CONTENT)VALUES(SEQ_QNA_NO.NEXTVAL, ?, ?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getReTitle());
				pstmt.setString(2, vo.getReContent());
				int result = pstmt.executeUpdate();
				//rs
				
				//close
				JDBCTemplate.close(pstmt);
				
				return result;
	}

	//질의답변 목록조회<최신순>
	public List<QnaVo> reWriteList(Connection conn) throws Exception {
		
		//sql
				String sql = "SELECT A.QNA_ADMIN_NO, A.RE_TITLE , A.RE_CONTENT , A.INQUIRY , TO_CHAR(A.POST_TIME, 'YYYY\"년\"MM\"월\"DD\"일\"') AS RE_POST_TIME, A.USER_NO FROM QNA_ADMIN A JOIN SUBWAY_USER U ON A.USER_NO = U.USER_NO WHERE A.DELETE_YN = 'N' ORDER BY A.QNA_ADMIN_NO DESC";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				
				//rs
				List<QnaVo> voList = new ArrayList<QnaVo>();
				while(rs.next()) {
					String qnaAdminNo = rs.getString("QNA_ADMIN_NO");
					String reTitle = rs.getString("RE_TITLE");
					String reContent = rs.getString("RE_CONTENT");
					String inquiry = rs.getString("INQUIRY");
					String rePostTime = rs.getString("RE_POST_TIME");
					String userNo = rs.getString("USER_NO");
					
					QnaVo vo = new QnaVo();
					vo.setQnaNo(qnaAdminNo);
					vo.setReTitle(reTitle);
					vo.setReContent(reContent);
					vo.setInquiry(inquiry);
					vo.setRePostTime(rePostTime);
					vo.setUserNo(userNo);
					
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
				String sql = "SELECT Q.QNA_NO , Q.USER_NO , S.STATION_NAME, Q.TITLE , U.NICK AS WRITER_NICK, Q.CONTENT , Q.INQUIRY , TO_CHAR(Q.POST_TIME, 'YYYY-MM-DD') AS POST_TIME FROM QNA Q JOIN SUBWAY_USER U ON Q.USER_NO = U.USER_NO JOIN STATION S ON Q.STATION_NO = S.STATION_NO WHERE Q.TITLE LIKE '%' || ? || '%' ORDER BY QNA_NO DESC";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, searchValue);
				ResultSet rs = pstmt.executeQuery();
				
				//rs
				List<QnaVo> voList = new ArrayList<QnaVo>();
				while(rs.next()) {
					String qnaNo = rs.getString("QNA_NO");
					String userNo = rs.getString("USER_NO");
					String stationName = rs.getString("STATION_NAME");
					String title = rs.getString("TITLE");
					String writerNick = rs.getString("WRITER_NICK");
					String content = rs.getString("CONTENT");
					String inquiry = rs.getString("INQUIRY");
					String postTime = rs.getString("POST_TIME");
					
					QnaVo vo = new QnaVo();
					vo.setQnaNo(qnaNo);
					vo.setUserNo(userNo);
					vo.setStationName(stationName);
					vo.setTitle(title);
					vo.setWriterNick(writerNick);
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
		
			String sql = "SELECT Q.QNA_NO , S.STATION_NAME , Q.TITLE , Q.CONTENT , A.RE_TITLE , A.RE_CONTENT , U.NICK AS WRITER_NICK , Q.INQUIRY , TO_CHAR(Q.POST_TIME, 'MM/DD') AS POST_TIME , TO_CHAR(A.POST_TIME, 'MM/DD') AS RE_POST_TIME FROM QNA Q JOIN QNA_ADMIN A ON Q.USER_NO = A.USER_NO JOIN SUBWAY_USER U ON Q.USER_NO = U.USER_NO JOIN STATION S ON Q.STATION_NO = S.STATION_NO WHERE Q.DELETE_YN = 'N' AND Q.QNA_NO = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			ResultSet rs = pstmt.executeQuery();
			QnaVo vo = null;
			
			//rs
			if(rs.next()) {
				String qnaNo = rs.getString("QNA_NO");
				String stationName = rs.getString("STATION_NAME");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				String reTitle = rs.getString("RE_TITLE");
				String reContent = rs.getString("RE_CONTENT");
				String writerNick = rs.getString("WRITER_NICK");
				String inquiry = rs.getString("INQUIRY");
				String postTime = rs.getString("POST_TIME");
				String rePostTime = rs.getString("RE_POST_TIME");
				
				vo = new QnaVo();
				vo.setQnaNo(qnaNo);
				vo.setStationName(stationName);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setReTitle(reTitle);
				vo.setReContent(reContent);
				vo.setInquiry(inquiry);
				vo.setWriterNick(writerNick);
				vo.setPostTime(postTime);
				vo.setRePostTime(rePostTime);
			}
			//close
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
			
			return vo;
		
		
	}


	public List<QnaVo> searchReWriteByTitle(Connection conn, String searchValue) throws Exception {
		
		//sql
		String sql = "SELECT A.QNA_ADMIN_NO , A.RE_TITLE , A.RE_CONTENT , A.INQUIRY , TO_CHAR(A.POST_TIME, 'YYYY-MM-DD') AS RE_POST_TIME FROM QNA_ADMIN A JOIN SUBWAY_USER U ON A.USER_NO = U.USER_NO WHERE A.RE_TITLE LIKE '%' || ? || '%' ORDER BY QNA_ADMIN_NO DESC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, searchValue);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		List<QnaVo> voList = new ArrayList<QnaVo>();
		while(rs.next()) {
			String qnaAdminNo = rs.getString("QNA_ADMIN_NO");
			String reTitle = rs.getString("RE_TITLE");
			String reContent = rs.getString("RE_CONTENT");
			String inquiry = rs.getString("INQUIRY");
			String rePostTime = rs.getString("RE_POST_TIME");
			
			QnaVo vo = new QnaVo();
			vo.setQnaAdminNo(qnaAdminNo);
			vo.setReTitle(reTitle);
			vo.setReContent(reContent);
			vo.setInquiry(inquiry);
			vo.setRePostTime(rePostTime);
			
			voList.add(vo);
		}
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;
		
	}


	public int writeDelete(Connection conn, HashMap<String, String> map) throws Exception {

		//sql 
				String sql = "UPDATE QNA SET DELETE_YN = 'Y' , POST_TIME = SYSDATE WHERE QNA_NO = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, map.get("QNA_NO"));
				int result = pstmt.executeUpdate();
				//rs
				
				//close
				JDBCTemplate.close(pstmt);
				
				return result;
	}


	public int reWriteDelete(Connection conn, HashMap<String, String> map) throws Exception {

		//sql 
		String sql = "UPDATE QNA_ADMIN SET DELETE_YN = 'Y' , RE_POST_TIME = SYSDATE WHERE QNA_ADMIN_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, map.get("QNA_ADMIN_NO"));
		int result = pstmt.executeUpdate();
		//rs
		
		//close
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}


	public int titleCorrect(Connection conn, QnaVo vo) throws Exception {
		
			String sql = "UPDATE QNA SET TITLE = ? , POST_TIME = SYSDATE WHERE QNA_NO = ?  AND DELETE_YN = 'N'";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getQnaNo());
			int result = pstmt.executeUpdate();
			
			JDBCTemplate.close(pstmt);
			
			return result;
		
	}


	public int contentCorrect(Connection conn, QnaVo vo)	 throws Exception {
		
		String sql = "UPDATE QNA SET CONTENT = ? , POST_TIME = SYSDATE WHERE QNA_NO = ?  AND DELETE_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getContent());
		pstmt.setString(2, vo.getQnaNo());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}


	public int reTitleCorrect(Connection conn, QnaVo vo)  throws Exception {
		
		String sql = "UPDATE QNA_ADMIN SET RE_TITLE = ? , RE_POST_TIME = SYSDATE WHERE QNA_ADMIN_NO = ?  AND DELETE_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getReTitle());
		pstmt.setString(2, vo.getQnaAdminNo());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	
	}


	public int reContentCorrect(Connection conn, QnaVo vo) throws Exception {
		
		String sql = "UPDATE QNA_ADMIN SET RE_CONTENT = ? , RE_POST_TIME = SYSDATE WHERE QNA_ADMIN_NO = ?  AND DELETE_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getReContent());
		pstmt.setString(2, vo.getQnaAdminNo());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	
	public int increaseInquiry(Connection conn, String num) throws Exception {
		
			//sql
			String sql = "UPDATE QNA SET INQUIRY = INQUIRY+1 WHERE QNA.NO = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			int result = pstmt.executeUpdate();
			//rs
		
		//close
		JDBCTemplate.close(pstmt);
		
		return result;
	}
}
