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
	//관리자 로그인
	public AdminVo login(AdminVo vo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		AdminVo admin = dao.login(conn , vo);
		
		JDBCTemplate.close(conn);
		
		
		return admin;
	}
	//회원가입(관리자 로그인시 가능)
	public int join(AdminVo vo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result =dao.join(conn , vo);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	//회원정보 수정 (아이디)
	public int modifyId(AdminVo vo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.modifyId(conn , vo);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	
	//회원정보 수정 (닉네임)
		public int modifyNick(AdminVo vo) throws Exception {
			
			Connection conn = JDBCTemplate.getConnection();
			
			int result = dao.modifyId(conn , vo);
			
			if(result == 1) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			
			JDBCTemplate.close(conn);
			
			return result;
		}
	
	
		//회원정보 수정 (업무명)
		public int modifyPartName(AdminVo vo) throws Exception {
			
			Connection conn = JDBCTemplate.getConnection();
			
			int result = dao.modifyId(conn , vo);
			
			if(result == 1) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			
			JDBCTemplate.close(conn);
			
			return result;
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}//class
































