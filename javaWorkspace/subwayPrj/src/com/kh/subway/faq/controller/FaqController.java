package com.kh.subway.faq.controller;

import java.util.List;

import com.kh.subway.faq.service.FaqService;
import com.kh.subway.faq.vo.FaqVo;
import com.kh.subway.main.Main;

public class FaqController {
	
	
	private final FaqService service;
	
	public FaqController() {
		service = new FaqService();
	}
	
	//관리자 메뉴
	public void adminMenu() {
		
	}
	
	//FAQ 메뉴선택
	public void selectMenu() {
		System.out.println("----FAQ----");
		
		System.out.println("1. FAQ 작성");
		System.out.println("2. FAQ 목록조회");
		System.out.println("3. FAQ 상세조회");
//		System.out.println("3. FAQ 수정");
//		System.out.println("3. FAQ 삭제");
		
		String num = Main.SC.nextLine();
		switch(num) {
		case "1" : write(); break;
		case "2" : faqList(); break;
		case "3" : faqDetailByNo(); break;
//		case "4" : faqModify(); break;
//		case "5" : faqDelete(); break;
		default : System.out.println("잘못입력하셨습니다"); 
		}
}
	
	
		//FAQ 게시글 작성 (관리자 전용)
		public void write() {
			
			try {
				System.out.println("---FAQ 작성---");
				
				//로그인 여부 체크
				if(Main.loginAdmin == null) {
					throw new Exception("관리자 로그인이 필요합니다.");
				}
				
				System.out.println("제목 : ");
				String title = Main.SC.nextLine();
				System.out.println("내용 : ");
				String content = Main.SC.nextLine();
				
				FaqVo vo = new FaqVo();
				vo.setFaqtitle(title);
				vo.setContent(content);
				
				int result = service.writer(vo);
				
				if(result != 1){
					throw new Exception();
				}
				System.out.println("게시글 작성 성공");
			}catch(Exception e) {
				System.out.println("게시글 작성 실패");
				e.printStackTrace();
				}
		}//write
			
		
		//FAQ 목록조회 (최신순)
		public void faqList() {
			
			try {
				System.out.println("---- FAQ 목록 ----");
				
				List<FaqVo> voList = service.faqList();
				
				System.out.print("제목");
				System.out.print(" / ");
				System.out.print("번호");
				System.out.print(" / ");
				System.out.print("작성일자");
				System.out.println();
				
				for(FaqVo vo : voList) {
					System.out.print(vo.getFaqno());
					System.out.println("/");
					System.out.println(vo.getFaqtitle());
					System.out.println("/");
					System.out.println(vo.getPosttime());
					
					System.out.println();
				}
				
			}catch (Exception e) {
				System.out.println("FAQ 목록 조회 실패");
				e.printStackTrace();
			}
		} //faqList
		
		
		/**
	       * FAQ 상세 조회 (번호)
	       * 번호, 역번호,  제목, 조회수, 작성일자, 내용
	       */
	      public void faqDetailByNo() {
	         
	         try {
	            System.out.println("----FAQ 상세조회----");
	            
	            //데이터
	            System.out.println("조회할 FAQ 번호 : ");
	            String NUM = Main.SC.nextLine();
	            
	            //서비스
	            FaqVo vo = service.faqDetailByNo(NUM);
	            
	            //결과처리
	            if(vo != null) {
	               throw new Exception("FAQ 상세조회 실패");
	            }
	            System.out.println("FAQ 번호 : " + vo.getFaqno());
	            System.out.println("FAQ 역 번호 : " + vo.getStationno());
	            System.out.println("FAQ 제목 : " + vo.getFaqtitle());
	            System.out.println("FAQ 작성일시 : " + vo.getPosttime());
	            System.out.println("FAQ 내용 : " + vo.getContent());
	            
	         }catch (Exception e) {
	            System.out.println("FAQ 상세조회가 불가합니다");
	            e.printStackTrace();
	         }
	      } //faqDetailByNo
	
		
		//FAQ 수정
		
		//FAQ 삭제
		
		//faq 목록 (최신순)
		      
			

}//class
