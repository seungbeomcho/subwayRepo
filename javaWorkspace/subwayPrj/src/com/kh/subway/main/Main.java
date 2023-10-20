package com.kh.subway.main;

import java.util.Scanner;

import com.kh.subway.admin.controller.AdminController;
import com.kh.subway.admin.vo.AdminVo;
import com.kh.subway.board.controller.BoardController;
import com.kh.subway.faq.controller.FaqController;
import com.kh.subway.qna.controller.QnaController;
import com.kh.subway.station.controller.StationController;
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
		
		System.out.println("==== Subway 프로젝트 ====");	
		while(true) {
			//메뉴판
			System.out.println("Subway 프로그램입니다.");
			System.out.println("원하시는 메뉴를 입력해주세요.");
			System.out.println("┌─────────────────────────────┐");
			System.out.println("| 1. 역 정보 검색");
			System.out.println("| 2. 지하철 노선 전체조회");
			System.out.println("| 3. 역 공지사항 게시판");
			System.out.println("| 4. 역 FAQ 게시판");
			System.out.println("| 5. 역 Q&A 게시판");
			System.out.println("| 6. 역 자유 게시판");
			System.out.println("| 7. 역 주변 Subway 매장 검색");
			System.out.println("| 8. 출발 - 목적지 최소환승경로 조회");
			System.out.println("| 9. 프로그램 종료");
			System.out.println("└─────────────────────────────┘");
			System.out.print("번호를 입력해주세요 : ");
			
			//메뉴선택
			String num = Main.SC.nextLine();

			switch (num) {
			case "ADMIN" :  adminController.selectMenu(); break;
			case "1" : stationController.selectMenu(); break;
//			case "2" : stationController.stationInfoView(); break;
//			case "3" : noticeController.selectMenu(); break;
//			case "4" : faqController.selectMenu(); break;
//			case "5" : qnaController.selectMenu(); break;
//			case "6" : boardController.selectMenu(); break;
//			case "7" : subwayController.selectMenu(); break;
			case "9" : return;
			}
			
			if(loginAdmin.getAdminNo() != null) {
				// 관리자 로그인시 자동 메뉴 연결입니다.
				// switch문에서 본인기능 주석 해제해서 사용하면 됩니다.
				switch (loginAdmin.getAdminNo()) {
				case "1" : stationController.adminMenu(); break;
//				case "2" : subwayController.adminMenu(); break;
//				case "3" : faqController.adminMenu(); break;
//				case "4" : noticeController.adminMenu(); break;
//				case "5" : qnaController.adminMenu(); break;
//				case "6" : boardController.adminMenu(); break;
				
				}
			}
		}//while
		

		
	}//main

}
