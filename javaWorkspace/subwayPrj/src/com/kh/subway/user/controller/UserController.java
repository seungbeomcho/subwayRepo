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
		} else {
			System.out.println("3. 마이페이지");
			System.out.println("4. 로그아웃");
		}
		
		String num = Main.SC.nextLine();
		switch(num) {
		case "1" : join(); break;
		case "2" : if(Main.loginUser == null) {login();} break;
		case "3" : if(Main.loginUser != null) {mypage();} break;
		case "4" : if(Main.loginUser != null) {logout();} break;
		default : System.out.println("잘못 입력하셨습니다.");
		}
	}
	
	// 마이페이지
	private void mypage() {
		
		// 
		System.out.println("== 마이페이지 ==");
		
		// 유저정보 출력
		getUserInfo();
		
		System.out.println("=============");
		System.out.println("1. 비밀번호 변경");
		System.out.println("2. 닉네임 변경");
		System.out.println("3. 회원탈퇴");
		
		System.out.print("메뉴번호 : ");
		String num = Main.SC.nextLine();
		
		switch(num) {
		case "1" : editPwd(); break;
		case "2" : editNick(); break;
		case "3" : quit(); break;
		}
		
	}

	// 유저 정보 출력
	private void getUserInfo() {
		
		try {
			
			String id = Main.loginUser.getId();
			
			// 서비스
			UserVo vo = service.getUserInfo(id);
			
			// 결과
			System.out.println("아이디 : " + vo.getId());
			System.out.println("닉네임 : " + vo.getNick());
			System.out.println("가입일시 : " + vo.getEnrollDate());
			System.out.println("수정일시 : " + vo.getPwdeditDate());
			
		} catch(Exception e) {
			System.err.println("회원정보를 불러오는데 실패했습니다.");
			e.printStackTrace();
		}
		
	}

	// 닉네임 변경
	private void editNick() {
		
		try {
			
			// 데이터
			System.out.print("비밀번호 확인 : ");
			String pwd = Main.SC.nextLine();
			System.out.print("새 닉네임 : ");
			String newNick = Main.SC.nextLine();
			
			UserVo vo = new UserVo();
			vo.setId(Main.loginUser.getId());
			vo.setPwd(pwd);
			vo.setNick(newNick);
			
			// 서비스
			int result = service.editNick(vo);
			
			// 결과
			if(result == 1) {
				System.out.println("닉네임이 변경되었습니다.");
			} else {
				throw new Exception();
			}
			
		} catch(Exception e) {
			System.err.println("닉네임 변경이 실패하였습니다.");
			e.printStackTrace();
		}
		
	}

	// 회원가입
	private void join() {
		
		try {	
			// 데이터
			System.out.print("아이디 : ");
			String id = Main.SC.nextLine();
			System.out.print("비밀번호 : ");
			String pwd = Main.SC.nextLine();
			System.out.print("닉네임 : ");
			String nick = Main.SC.nextLine();
			
			UserVo vo = new UserVo();
			vo.setId(id);
			vo.setPwd(pwd);
			vo.setNick(nick);

			// 서비스
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
	private void login() {
		
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
	private void logout() {
		if(Main.loginUser == null) {
			System.err.println("로그인 하고 진행해주세요.");
			return;
		} else {
			System.out.println("로그아웃 되었습니다.");
			Main.loginUser = null;
		}
		
	}
	
	// 회원탈퇴
	private void quit() {
		
		try {
			System.out.println("----- 회원탈퇴 ------");
			
			// 로그인 여부 검사
			if(Main.loginUser == null) {
				throw new Exception("로그인을 하고 회원탈퇴를 시도하세요.");
			}
			
			// 데이터
			String no = Main.loginUser.getUserNo();
			System.out.print("비밀번호 확인 : ");
			String pwd = Main.SC.nextLine();
			
			UserVo vo = new UserVo();
			vo.setUserNo(no);
			vo.setId(Main.loginUser.getId());
			vo.setPwd(pwd);
			
			// 서비스
			int result = service.quit(vo);
			
			// 결과
			if(result != 1) {
				throw new Exception();
			}
			System.out.println("정상적으로 탈퇴되었습니다.");
			logout();
			
		} catch(Exception e) {
			System.err.println("탈퇴를 실패하였습니다.");
			e.printStackTrace();
		}
		
	}
	
	// 비밀번호 변경
	private void editPwd() {
		
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
				System.out.println("닉네임이 변경되었습니다.");
			} else {
				throw new Exception();
			}
			
		} catch(Exception e) {
			System.err.println("회원정보가 일치하지 않습니다.");
			e.printStackTrace();
		}
		
		
	}

}
