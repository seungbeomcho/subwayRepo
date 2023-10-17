package com.kh.subway.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.subway.user.vo.UserVo;

import javaJDBCTEMPLATE.JDBCTemplate;

public class UserDao {

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

	public int quit(Connection conn, String no) throws Exception {
		
		// sql
		String sql = "UPDATE SUBWAY_USER SET DEL_YN = 'Y' WHERE NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		int result = pstmt.executeUpdate();
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;
	}

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

}
