package com.kh.subway.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kh.subway.admin.vo.AdminVo;
import com.kh.subway.main.Main;

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
			
			admin = new AdminVo();
			admin.setAdminNo(adminNo);
			admin.setId(id);
			admin.setPwd(pwd);
			admin.setNick(nick);
			admin.setDeleteYn(deleteYn);
			admin.setPartName(partName);
			
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		
		return admin;
	}

	public int quit(Connection conn , String no) throws Exception {
		
		String sql = "UPDATE ADMIN SET DELETE_YN = 'Y' WHERE ADMIN_NO = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	
}
