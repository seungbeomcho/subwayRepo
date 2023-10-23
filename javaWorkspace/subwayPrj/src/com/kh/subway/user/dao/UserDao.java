package com.kh.subway.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.subway.user.vo.UserVo;

import javaJDBCTEMPLATE.JDBCTemplate;

public class UserDao {

	// 회원가입
	public int join(Connection conn, UserVo vo) throws Exception {
		
		// sql
		String sql = "INSERT INTO SUBWAY_USER(USER_NO, ID, PWD, NICK, ENROLL_DATE) VALUES(SEQ_USER_NO.NEXTVAL, ?, ?, ?, SYSDATE)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getId());
		pstmt.setString(2, vo.getPwd());
		pstmt.setString(3, vo.getNick());
		int result = pstmt.executeUpdate();
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}
	
	// 로그인
	public UserVo login(Connection conn, UserVo vo) throws Exception {
		// sql
		String sql = "SELECT * FROM SUBWAY_USER WHERE ID = ? AND PWD = ? AND DEL_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getId());
		pstmt.setString(2, vo.getPwd());
		ResultSet rs = pstmt.executeQuery();
		
		// rs -> vo
		UserVo dbVo = null;
		if(rs.next()) {
			String no = rs.getString("USER_NO");
			String id = rs.getString("ID");
			String pwd = rs.getString("PWD");
			String nick = rs.getString("NICK");
			String enrollDate = rs.getString("ENROLL_DATE");
			String delYn = rs.getString("DEL_YN");
			String pwdeditDate = rs.getString("PWDEDIT_DATE");
			
			dbVo = new UserVo();
			dbVo.setUserNo(no);
			dbVo.setId(id);
			dbVo.setPwd(pwd);
			dbVo.setNick(nick);
			dbVo.setEnrollDate(enrollDate);
			dbVo.setDelYn(delYn);
			dbVo.setPwdeditDate(pwdeditDate);
		}
		
		// close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return dbVo;
	}

	// 회원탈퇴
	public int quit(Connection conn, UserVo vo) throws Exception {
		
		// sql
		String sql = "UPDATE SUBWAY_USER SET DEL_YN = 'Y' WHERE USER_NO = ? AND ID = ? AND PWD = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getUserNo());
		pstmt.setString(2, vo.getId());
		pstmt.setString(3, vo.getPwd());
		int result = pstmt.executeUpdate();
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	// 비밀번호 변경
	public int editPwd(Connection conn, UserVo vo, String newPwd) throws Exception {
		
		// sql
		String sql = "UPDATE SUBWAY_USER SET PWD = ? WHERE ID = ? AND PWD = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, newPwd);
		pstmt.setString(2, vo.getId());
		pstmt.setString(3, vo.getPwd());
		
		int result = pstmt.executeUpdate();
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}

	// 닉네임 변경
	public int editNick(Connection conn, UserVo vo) throws Exception {
		
		// sql
		String sql = "UPDATE SUBWAY_USER SET NICK = ? WHERE ID = ? AND PWD = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getNick());
		pstmt.setString(2, vo.getId());
		pstmt.setString(3, vo.getPwd());
		
		int result = pstmt.executeUpdate();
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	// 회원정보 조회
	public UserVo getUserInfo(Connection conn, String id) throws Exception {
		
		// sql
		String sql = "SELECT ID, NICK, TO_CHAR(ENROLL_DATE, 'YYYY-MM-DD') AS ENROLL_DATE, TO_CHAR(PWDEDIT_DATE, 'YYYY-MM-DD') AS PWDEDIT_DATE FROM SUBWAY_USER WHERE ID = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		
		// rs
		ResultSet rs = pstmt.executeQuery();
		
		UserVo vo = null;
		if(rs.next()) {
			String userid = rs.getString("ID");
			String nick = rs.getString("NICK");
			String enrollDate = rs.getString("ENROLL_DATE");
			String pwdrditDate = rs.getString("PWDEDIT_DATE");
			
			vo = new UserVo();
			vo.setId(userid);
			vo.setNick(nick);
			vo.setEnrollDate(enrollDate);
			vo.setPwdeditDate(pwdrditDate);
		}
		
		// close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return vo;
	}

}
