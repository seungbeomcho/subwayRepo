package com.kh.subway.admin.controller;


import com.kh.subway.admin.service.AdminService;
import com.kh.subway.admin.vo.AdminVo;
import com.kh.subway.main.Main;

public class AdminController {
	
	private AdminService service;
	
	
	public AdminController() {
		service = new AdminService();
	}
	
	//관리자 메뉴선택
	public void selectMenu() {
		System.out.println("----- 관리자 메뉴 선택 -----");
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("3. 회원정보수정");
		System.out.println("9. 로그아웃");
		
		String num = Main.SC.nextLine();
		switch(num) {
		case "1" : join(); break;
		case "2" : login(); break;
		case "3" : modifySelectMenu(); break;
		case "9" : adminLogout(); break;
		default : System.out.println("잘못 선택 하셨습니다.");
		}
		
	}
	
	
	
	
	//회원가입(ADMIN 로그인시 가능)
	public void join() {
		try {
			if(Main.loginAdmin == null) {
				throw new Exception("ADMIN 로그인시 가능합니다.");
			}
			
			System.out.println("-----관리자 회원가입-----");
			System.out.print("아이디 : ");
			String id = Main.SC.nextLine();
			System.out.print("패스워드 : ");
			String pwd = Main.SC.nextLine();
			System.out.print("닉네임 : ");
			String nick = Main.SC.nextLine();
			System.out.print("업무명 : ");
			String partName = Main.SC.nextLine();
			
			AdminVo vo = new AdminVo();
			vo.setId(id);
			vo.setPwd(pwd);
			vo.setNick(nick);
			vo.setPartName(partName);
			
			int result = service.join(vo);
			
			if(result != 1) {
				throw new Exception();
			}
			System.out.println("관리자ID :" + vo.getNick() + "회원가입 완료.");
			
		}catch(Exception e) {
			System.out.println("회원가입 실패");
			e.printStackTrace();
		}
		
	}
	
	
	//로그인(ADMIN)
	public void login() {
		try {
			System.out.println("-----관리자 로그인-----");
			System.out.print("아이디 : ");
			String id = Main.SC.nextLine();
			System.out.print("패스워드 : ");
			String pwd = Main.SC.nextLine();
			
			AdminVo vo = new AdminVo();
			vo.setId(id);
			vo.setPwd(pwd);
			
			AdminVo admin = service.login(vo);
			
			if(admin == null) {
				throw new Exception();
			}
			
			Main.loginAdmin = admin;
			
			System.out.println("관리자 로그인 성공");
			
		}catch(Exception e) {
			System.out.println("로그인 실패");
			e.printStackTrace();
		}
		
		
	}
	
	// 관리자 정보 수정 선택
		public void modifySelectMenu() {
			System.out.println("수정하실 사항을 선택해주세요");
			System.out.println("1. 아이디");
			System.out.println("2. 닉네임");
			System.out.println("3. 업무명");
			String num = Main.SC.nextLine();
			
			switch(num) {
			case "1" : modifyId(); break;
			case "2" : modifyNick(); break;
			case "3" : modifyPartName(); break;
			default : System.out.println("잘못 선택하셨습니다.");
			}
		}
	
	
	
	
	
	
	// 관리자 아이디 수정
	public void modifyId() {
		try {
			if(Main.loginAdmin == null) {
				throw new Exception("ADMIN 로그인시 가능합니다.");
			}
			System.out.println("아이디 수정");
			System.out.println("현재 아이디" + Main.loginAdmin.getId());
			System.out.print("새로운 아이디 : ");						
			String id = Main.SC.nextLine();
			String no = Main.loginAdmin.getAdminNo();
			AdminVo vo = new AdminVo();
			vo.setId(id);
			vo.setAdminNo(no);
			
			int result = service.modifyId(vo);
			
			if(result != 1) {
				throw new Exception();
			}
			
			System.out.println("아이디가 변경 되었습니다.");
		}catch(Exception e) {
			System.out.println("아이디 변경 실패");
		}
		
		
	}
	
	
	// 관리자 닉네임 수정
	public void modifyNick() {
		try {
			if(Main.loginAdmin == null) {
				throw new Exception("ADMIN 로그인시 가능합니다.");
			}
			System.out.println("닉네임 수정");
			System.out.println("현재 닉네임" + Main.loginAdmin.getNick());
			System.out.print("새로운 닉네임 : ");						
			String nick = Main.SC.nextLine();
			String no = Main.loginAdmin.getAdminNo();
			AdminVo vo = new AdminVo();
			vo.setNick(nick);
			vo.setAdminNo(no);
			
			int result = service.modifyId(vo);
			
			if(result != 1) {
				throw new Exception();
			}
			
			System.out.println("아이디가 변경 되었습니다.");
		}catch(Exception e) {
			System.out.println("아이디 변경 실패");
		}
	}
	// 관리자 업무명 수정
	public void modifyPartName() {
		try {
			if(Main.loginAdmin == null) {
				throw new Exception("ADMIN 로그인시 가능합니다.");
			}
			System.out.println("업무명 수정");
			System.out.println("현재 업무명" + Main.loginAdmin.getPartName());
			System.out.print("새로운 업무명 : ");						
			String partName = Main.SC.nextLine();
			String no = Main.loginAdmin.getAdminNo();
			AdminVo vo = new AdminVo();
			vo.setPartName(partName);
			vo.setAdminNo(no);
			
			int result = service.modifyId(vo);
			
			if(result != 1) {
				throw new Exception();
			}
			
			System.out.println("아이디가 변경 되었습니다.");
		}catch(Exception e) {
			System.out.println("아이디 변경 실패");
		}
	}
	
	//로그아웃
	public void adminLogout() {
		Main.loginAdmin = null;
		System.out.println("로그아웃");
	}
	
	
	
	
	
}
