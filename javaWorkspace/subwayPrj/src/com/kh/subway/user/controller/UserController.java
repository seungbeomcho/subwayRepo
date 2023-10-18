package com.kh.subway.user.controller;

import com.kh.subway.main.Main;
import com.kh.subway.user.service.UserService;
import com.kh.subway.user.vo.UserVo;

public class UserController {
	
	// 필드 == 멤버변수
	private final UserService service;
	
	// 기본생성자
	public UserController() {
		service = new UserService();
	}
	
	// 메뉴선택
	public void selectMenu() {
		System.out.println("===== USER =====");
		
		System.out.println("1. 회원가입");
		if(Main.loginUser == null) {
			System.out.println("2. 로그인");
			System.out.println("3. 비밀번호 변경");
		} else {
			System.out.println("4. 회원탈퇴");
			System.out.println("5. 로그아웃");
		}
		
		String num = Main.SC.nextLine();
		switch(num) {
		case "1" : join(); break;
		case "2" : if(Main.loginUser == null) {login();} break;
		case "3" : if(Main.loginUser == null) {editPwd();} break;
		case "4" : if(Main.loginUser != null) {quit();} break;
		case "5" : if(Main.loginUser != null) {logout();} break;
		default : System.out.println("잘못 입력하셨습니다.");
		}
	}
	
	// 회원가입
	public void join() {
		
		// 서비스
		try {	
			// 데이터
			System.out.print("아이디 : ");
			String id = Main.SC.nextLine();
			System.out.print("닉네임 : ");
			String nick = Main.SC.nextLine();
			
			UserVo vo = new UserVo();
			vo.setId(id);
			vo.setNick(nick);
			
			int result = service.join(vo);
			
			// 결과
			if(result == 1) {
				System.out.println("회원가입 성공!");
			} else {
				throw new Exception();
			}
			
		} catch(Exception e) {
			System.err.println("회원가입 실패");
			e.printStackTrace();
		}
		
	}
	
	// 로그인
	public void login() {
		
		try {

			System.out.println("----- 로그인 ------");
			// 데이터
			System.out.print("아이디 : ");
			String id = Main.SC.nextLine();
			System.out.print("비밀번호 : ");
			String pwd = Main.SC.nextLine();
			
			UserVo vo = new UserVo();
			vo.setId(id);
			vo.setPwd(pwd);
			
			// 서비스 호출
			UserVo x = service.login(vo);
			
			// 결과처리
			if(x == null) {
				throw new Exception();
			}
			Main.loginUser = x;
			System.out.println("로그인 성공!");
			
		} catch(Exception e) {
			System.err.println("로그인 실패");
			e.printStackTrace();
		}
		
		
	}
	
	// 로그아웃
	public void logout() {
		if(Main.loginUser == null) {
			System.err.println("로그인 하고 진행해주세요.");
			return;
		} else {
			System.out.println("로그아웃 되었습니다.");
			Main.loginUser = null;
		}
		
	}
	
	// 회원탈퇴
	public void quit() {
		
		try {
			System.out.println("----- 회원탈퇴 ------");
			
			// 로그인 여부 검사
			if(Main.loginUser == null) {
				throw new Exception("로그인을 하고 회원탈퇴를 시도하세요.");
			}
			
			// 데이터
			String no = Main.loginUser.getUserNo();
			
			// 서비스
			int result = service.quit(no);
			
			// 결과
			if(result != 1) {
				throw new Exception();
			}
			System.out.println("탈퇴되었습니다.");
			logout();
			
		} catch(Exception e) {
			System.err.println("회원탈퇴 실패");
			e.printStackTrace();
		}
		
	}
	
	// 비밀번호 찾기
	public void editPwd() {
		
		try {		
			// 데이터
			System.out.println("----- 비밀번호 변경 -----");
			System.out.print("아이디 : ");
			String id = Main.SC.nextLine();
			System.out.print("비밀번호 : ");
			String pwd = Main.SC.nextLine();
			System.out.print("새로운 비밀번호 : ");
			String newPwd = Main.SC.nextLine();
			
			UserVo vo = new UserVo();
			vo.setId(id);
			vo.setPwd(pwd);
			
			// 서비스
			int result = service.editPwd(vo, newPwd);
			
			// 결과
			if(result == 1) {
				System.out.println("변경되었습니다.");
			} else {
				throw new Exception();
			}
			
		} catch(Exception e) {
			System.err.println("회원정보가 일치하지 않습니다.");
			e.printStackTrace();
		}
		
		
	}

}
