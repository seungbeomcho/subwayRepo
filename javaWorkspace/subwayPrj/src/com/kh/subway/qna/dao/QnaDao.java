package com.kh.subway.qna.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kh.subway.main.Main;
import com.kh.subway.qna.vo.QnaVo;
import com.kh.subway.template.JDBCTemplate;

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
	public List<QnaVo> qnaList(Connection conn) throws Exception {
		
		//sql
		String sql = "SELECT Q.QNA_NO , Q.TITLE , U.NICK AS WRITER_NICK , Q.INQUIRY , TO_CHAR(Q.POST_TIME, 'YYYY\\\"년\\\"MM\\\"월\\\"DD\\\"일\\\"') AS POST_TIME FROM QNA Q JOIN SUBWAY_USER U ON Q.USER_NO = U.USER_ID WHERE Q.DELETE_YN = 'N' ORDER BY Q.QNA_NO DESC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		List<QnaVo> voList = new ArrayList<QnaVo>();
		while(rs.next()) {
			String qnaNo = rs.getString("QNA_NO");
			String title = rs.getString("TITLE");
			String userNo = rs.getString("USER_NO");
			String inquiry = rs.getString("INQUIRY");
			String postTime = rs.getString("POST_TIME");
			
			QnaVo vo = new QnaVo();
			vo.setQnaNo(qnaNo);
			vo.setTitle(title);
			vo.setUserNo(userNo);
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
	public QnaVo qnaDetailByNo(Connection conn, String num) throws Exception {
		
		//sql
		String sql = "SELECT B.NO , B.TITLE , B.CONTENT , M.NICK AS WRITER_NICK , B.HIT , TO_CHAR(B.ENROLL_DATE, 'MM/DD') AS ENROLL_DATE , B.MODIFY_DATE FROM BOARD B JOIN MEMBER M ON B.WRITER_NO = M.NO WHERE B.NO = ? AND B.DEL_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, num);
		ResultSet rs = pstmt.executeQuery();
		QnaVo vo = null;
		
		//rs
		if(rs.next()) {
			String qnaNo = rs.getString("QNA_NO");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String userNo = rs.getString("USER_NO");
			String inquiry = rs.getString("INQUIRY");
			String postTime = rs.getString("POST_TIME");
			
			vo = new QnaVo();
			vo.setQnaNo(qnaNo);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setUserNo(userNo);
			vo.setInquiry(inquiry);
			vo.setPostTime(postTime);
		}
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return vo;
	
	}
	
}
