package com.kh.subway.user.service;

import java.sql.Connection;

import com.kh.subway.user.dao.UserDao;
import com.kh.subway.user.vo.UserVo;

import javaJDBCTEMPLATE.JDBCTemplate;


public class UserService {
	
	// 필드 == 멤버변수
	private final UserDao dao;
	
	// 기본생성자
	public UserService() {
		dao = new UserDao();
	}

	// 회원가입
	public int join(UserVo vo) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// DAO
		int result = dao.join(conn, vo);
		
		// tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		// close
		JDBCTemplate.close(conn);
		
		return result;
		
	}

	// 로그인
	public UserVo login(UserVo vo) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		UserVo userVo = dao.login(conn, vo);
		
		// close
		JDBCTemplate.close(conn);
		
		
		return userVo;
		
	}

	// 회원탈퇴
	public int quit(UserVo vo) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		int result = dao.quit(conn, vo);
		
		// tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		// close
		JDBCTemplate.close(conn);
		
		return result;
		
	}

	// 비밀번호 변경
	public int editPwd(UserVo vo, String newPwd) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		int result = dao.editPwd(conn, vo, newPwd);
		
		// tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		// close
		JDBCTemplate.close(conn);
		
		return result;
		
	}

	public int editNick(UserVo vo) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		int result = dao.editNick(conn, vo);
		
		// tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		// close
		JDBCTemplate.close(conn);
		
		return result;
	}

	//  회원정보 조회
	public UserVo getUserInfo(String id) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao
		UserVo vo = dao.getUserInfo(conn, id);
		
		// close
		JDBCTemplate.close(conn);
		
		return vo;
	}
	
	

}
