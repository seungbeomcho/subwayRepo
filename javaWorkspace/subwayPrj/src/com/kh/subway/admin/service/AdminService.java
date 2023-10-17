package com.kh.subway.admin.service;

import java.sql.Connection;

import com.kh.subway.admin.dao.AdminDao;
import com.kh.subway.admin.vo.AdminVo;

import javaJDBCTEMPLATE.JDBCTemplate;

public class AdminService {
	
	private AdminDao dao;
	
	public AdminService() {
		dao = new AdminDao();
	}

	public AdminVo login(AdminVo vo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		AdminVo admin = dao.login(conn , vo);
		
		JDBCTemplate.close(conn);
		
		
		return admin;
	}

	public int quit(String no) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.quit(conn , no);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		
		return result;
	
	}
}
