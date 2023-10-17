package com.kh.subway.admin.controller;

import java.util.Scanner;

import com.kh.subway.admin.service.AdminService;
import com.kh.subway.admin.vo.AdminVo;
import com.kh.subway.main.Main;

public class AdminController {
	
	private Scanner sc;
	private AdminService service;
	
	
	public AdminController() {
		sc = new Scanner(System.in);
		service = new AdminService();
	}
	
	
	//로그인(ADMIN)
	public void login() {
		try {
			System.out.println("-----관리자 로그인-----");
			System.out.print("아이디 : ");
			String id = sc.nextLine();
			System.out.print("패스워드 : ");
			String pwd = sc.nextLine();
			
			AdminVo vo = new AdminVo();
			vo.setId(id);
			vo.setPwd(pwd);
			
			AdminVo admin = service.login(vo);
			
			if(admin == null) {
				throw new Exception();
			}
			
			Main.loginAdmin = admin;
			
			System.out.println("로그인 성공");
			
		}catch(Exception e) {
			System.out.println("로그인 실패");
		}
		
		
	}
	
	public void quit() {
		
		try {
			System.out.println("-----관리자계정탈퇴------");
			if(Main.loginAdmin == null) {
				throw new Exception("관리자 아이디 로그인 진행하세요");
			}
			
			String no = Main.loginAdmin.getAdminNo();
			
			int result = service.quit(no);
			
			if(result != 1) {
				throw new Exception();
			}
			System.out.println("탈퇴 성공");
		}catch(Exception e) {
			System.out.println("탈퇴 실패");
			e.printStackTrace();
		}
	}
	
}
