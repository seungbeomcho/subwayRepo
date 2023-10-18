package com.kh.subway.faq.controller;

public class FaqController {
	
	//메뉴선택
	public void selectMenu() {
		System.out.println("====FAQ====");
		
		System.out.println("1. FAQ 조회");
		
		String num = Main.SC.nextLine();
		switch(num) {
		case "1" : write(); break;
		case "2" : boardList(); break;
		case "3" : boardDetailByNo(); break;
		default : System.out.println("잘못입력하셨습니다"); 
		}
				
		//목록 조회 - 조회수 (번호, 제목, 작성자 닉네임, 조회수, 작성일시)
		
		//게시글 검색 - 제목
		
		//상세 조회 - 번호 (모든 칼럼)
		/**
		 * 상세 조회
		 * 
		 * SELECT *
		 * FROM BOARD
		 * WHERE NO = ?
		 * AND DEL_YN = 'N'
		 */
				
				
				
				
				
				
		}//class

}
