package com.kh.subway.faq.controller;

public class FaqController {
	
	//필드
	private final FaqService service;
	
	public FaqController() {
		service = new FaqService();
	}
	
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
				
		//faq 게시글 수정
		public void exit() {
			
		}
				
				
				
				
				
				
		}//class

}
