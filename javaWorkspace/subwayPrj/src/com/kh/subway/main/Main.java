package com.kh.subway.main;

import java.util.Scanner;

import com.kh.subway.user.vo.UserVo;

public class Main {
	
	public static final Scanner SC = new Scanner(System.in);
	public static UserVo loginUser;
//	public static AdminVo loginAdmin;
	
	public static void main(String[] args) {
		System.out.println("==== Subway 프로젝트 ====");
		
		while(true) {
			//메뉴판
			System.out.println("Subway 프로그램입니다.");
			System.out.println("원하시는 메뉴를 입력해주세요.");
			System.out.println("┌─────────────────────────────┐");
			System.out.println("| 1. 역 정보 검색");
			System.out.println("| 2. 역 공지사항 게시판");
			System.out.println("| 3. 역 FAQ 게시판");
			System.out.println("| 4. 역 Q&A 게시판");
			System.out.println("| 5. 역 자유 게시판");
			System.out.println("| 6. 역 주변 Subway 매장 검색");
			System.out.println("| 7. 출발 - 목적지 최소환승경로 조회");
			System.out.println("| 9. 프로그램 종료");
			System.out.println("└─────────────────────────────┘");
			
			//메뉴선택
			String num = Main.SC.nextLine();
			//밑에 swith문에 본인 기능 메소드 호출 하시면 됩니다.
			//각자 컨트롤러를 위에 객채생성해서 해주시구요
//			switch (num) {
//			case "1" : ~~
//			}
		}//while
		
	}//main

}
