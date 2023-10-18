package com.kh.subway.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kh.subway.admin.vo.AdminVo;

import javaJDBCTEMPLATE.JDBCTemplate;

public class AdminDao {
	
	public AdminVo login(Connection conn, AdminVo vo) throws Exception {
		
		String sql = "SELECT * FROM ADMIN WHERE ID = ? AND PWD = ? AND DELETE_YN = 'N'";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getId());
		pstmt.setString(2, vo.getPwd());
		ResultSet rs = pstmt.executeQuery();
		
		AdminVo admin = null;
		// rs -> vo
		if(rs.next()) {
			String adminNo = rs.getString("ADMIN_NO");
			String id = rs.getString("ID");
			String pwd = rs.getString("PWD");
			String nick = rs.getString("NICK");
			String deleteYn = rs.getString("DELETE_YN");
			String partName = rs.getString("PART_NAME");
//			String enrollDate = rs.getString("ENROLL_DATE");
			
			admin = new AdminVo();
			admin.setAdminNo(adminNo);
			admin.setId(id);
			admin.setPwd(pwd);
			admin.setNick(nick);
			admin.setDeleteYn(deleteYn);
			admin.setPartName(partName);
//			admin.setEnrollDate(enrollDate);
			
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		
		return admin;
	}

	public int join(Connection conn, AdminVo vo) throws Exception {
		
		String sql = "INSERT INTO ADMIN(ADMIN_NO , ID , PWD , NICK , PART_NAME) VALUES (SEQ_ADMIN_NO.NAXTVAL, ? , '1234' , ? , ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getId());
		pstmt.setString(2, vo.getNick());
		pstmt.setString(3, vo.getPartName());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	//회원정보 수정 (아이디)
	public int modifyId(Connection conn, AdminVo vo) throws Exception {
		String sql = "UPDATE ADMIN SET ID = ? WHERE ADMIN_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getId());
		pstmt.setString(2, vo.getAdminNo());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}

	//회원정보 수정 (닉네임)
	public int modifyNick(Connection conn, AdminVo vo) throws Exception {
		String sql = "UPDATE ADMIN SET NICK = ? WHERE ADMIN_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getNick());
		pstmt.setString(2, vo.getAdminNo());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}
	
	//회원정보 수정 (업무명)
	public int modifyPartName(Connection conn, AdminVo vo) throws Exception {
		String sql = "UPDATE ADMIN SET PART_NAME = ? WHERE ADMIN_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getPartName());
		pstmt.setString(2, vo.getAdminNo());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}
	
	
}//class
































