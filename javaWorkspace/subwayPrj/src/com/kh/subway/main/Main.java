package com.kh.subway.main;

import java.util.Scanner;

import com.kh.subway.admin.controller.AdminController;
import com.kh.subway.admin.vo.AdminVo;
import com.kh.subway.board.controller.BoardController;
import com.kh.subway.faq.controller.FaqController;
import com.kh.subway.notice.controller.NoticeController;
import com.kh.subway.qna.controller.QnaController;
import com.kh.subway.station.controller.StationController;
import com.kh.subway.store.controller.SubwayController;
import com.kh.subway.user.controller.UserController;
import com.kh.subway.user.vo.UserVo;

public class Main {
	
	public static final Scanner SC = new Scanner(System.in);
	public static UserVo loginUser;
	public static AdminVo loginAdmin;
	
	public static void main(String[] args) {
		//객체
		AdminController adminController = new AdminController();
		StationController stationController = new StationController();
		BoardController boardController = new BoardController();
		QnaController qnaController = new QnaController();
		FaqController faqController = new FaqController();
		SubwayController subwayController = new SubwayController();
		NoticeController noticeController = new NoticeController();
		UserController userController = new UserController();
		
		System.out.println("==== Subway 프로젝트 ====");	
		while(true) {
			//메뉴판
			System.out.println();
			System.out.println("Subway 프로그램입니다.");
			System.out.println("원하시는 메뉴를 입력해주세요.");
			System.out.println("┌─────────────────────────────");
			System.out.println("| 1. 역 정보 검색");
			System.out.println("| 2. 출발 - 목적지 최소 환승경로 조회");
			System.out.println("| 3. 지하철 노선 전체조회");
			System.out.println("| 4. 역 공지사항 게시판");
			System.out.println("| 5. 역 FAQ 게시판");
			System.out.println("| 6. 역 Q&A 게시판");
			System.out.println("| 7. 역 자유 게시판");
			System.out.println("| 8. 역 주변 Subway 매장 검색");
			System.out.println("| 9. 유저 로그아웃");
			System.out.println("| 10. 프로그램 종료");
			System.out.println("└─────────────────────────────");
			System.out.print("번호를 입력해주세요 : ");
			//메뉴선택
			String num = Main.SC.nextLine();

			switch (num) {
			case "ADMIN" :  adminController.login(); break;
			case "LOGOUT" :  adminController.adminLogout(); break;
			case "1" : stationController.selectMenu(); break;
			case "2" : stationController.minTransferSearch(); break;
			case "3" : stationController.stationInfoView(); break;
			case "4" : noticeController.selectMenu(); break;
			case "5" : faqController.selectMenu(); break;
			case "6" : qnaController.selectMenu(); break;
			case "7" : boardController.selectMenu(); break;
			case "8" : subwayController.selectMenu(); break;
			case "9" : userController.logout();break;
			case "10" : return;
			}
			
			if(loginAdmin != null) {
				// 관리자 로그인시 자동 메뉴 연결입니다.
				System.out.println("※ 접속 관리자 부서 : " + loginAdmin.getPartName() + " ※");
				switch (loginAdmin.getAdminNo()) {
				case "1" : stationController.adminMenu(); break;
				case "2" : subwayController.adminMenu(); break;
				case "3" : faqController.adminMenu(); break;
				case "4" : noticeController.adminMenu(); break;
				case "6" : boardController.adminMenu(); break;
				
				}
			}
		}//while
	}//main

}//class
