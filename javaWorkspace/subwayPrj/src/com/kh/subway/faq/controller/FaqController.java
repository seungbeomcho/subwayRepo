package com.kh.subway.faq.controller;

import java.util.HashMap;
import java.util.List;

import com.kh.subway.board.vo.BoardVo;
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
		System.out.println("---FAQ 관리자 메뉴---");
		
		System.out.println("1. FAQ 작성");
		System.out.println("2. FAQ 제목 수정");
		System.out.println("3. FAQ 내용 수정");
		System.out.println("4. FAQ 삭제");
		System.out.println("5. 로그아웃");
		System.out.println("9. 돌아가기");
		
		String num = Main.SC.nextLine();
		switch(num) {
		case "1" : write(); break;
		case "2" : titleModify(); break;
		case "3" : contentModify(); break;
		case "4" : delete(); break;
		case "5" : Logout(); break;
		case "9" : return;
		
		default : System.out.println("잘못입력하셨습니다");
		}
		
		
	}
	
	//관리자 로그아웃
	public void Logout() {
		Main.loginAdmin = null;
		System.out.println("로그아웃");
	}
	
	
	//FAQ 메뉴선택
	public void selectMenu() {
		System.out.println("----FAQ----");
		
		System.out.println("1. FAQ 목록조회");
		System.out.println("2. FAQ 상세조회 (번호)");
		System.out.println("3. FAQ 제목 검색");
		System.out.println("4. FAQ 내용 검색");
		System.out.println("9. 돌아가기");
		
		String num = Main.SC.nextLine();
		switch(num) {
		case "1" : faqList(); break;
		case "2" : faqDetailByNo(); break;
		case "3" : searchFaqByTitle(); break;
		case "4" : searchFaqByContent(); break;
		case "9" : return;

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
				
				System.out.print("번호");
				System.out.print(" / ");
				System.out.print("제목");
				System.out.print(" / ");
				System.out.print("조회수");
				System.out.print(" / ");
				System.out.print("작성일자");
				System.out.println();
				
				for(FaqVo vo : voList) {
					System.out.print(vo.getFaqno());
					System.out.print("/");
					System.out.print(vo.getFaqtitle());
					System.out.print("/");
					System.out.print(vo.getInquiry());
					System.out.print("/");
					System.out.print(vo.getPosttime());
					System.out.println();
				}
				
			}catch (Exception e) {
				System.out.println("FAQ 목록 조회 실패");
				e.printStackTrace();
			}
		} //faqList
		
		
		//FAQ 상세 조회 (번호)
		public void faqDetailByNo() {
			
			try {
				System.out.println("----FAQ 상세조회----");
				
				//데이터
				System.out.println("조회할 FAQ 번호 : ");
				String num = Main.SC.nextLine();
				
				//서비스
				FaqVo vo = service.faqDetailByNo(num);
				
				//결과처리
				if(vo == null) {
					throw new Exception("FAQ 상세조회 실패");
				}
				System.out.println("--------------------------");
				System.out.println("FAQ 번호 : " + vo.getFaqno());
				System.out.println("FAQ 역 번호 : " + vo.getStationno());
				System.out.println("FAQ 제목 : " + vo.getFaqtitle());
				System.out.println("FAQ 조회수 : " + vo.getInquiry());
				System.out.println("FAQ 작성일시 : " + vo.getPosttime());
				System.out.println("FAQ 내용 : " + vo.getContent());
				
			}catch (Exception e) {
				System.out.println("FAQ 상세조회가 불가합니다");
				e.printStackTrace();
			}
		} //faqDetailByNo
		
		
		//FAQ 검색 (제목)
		public void searchFaqByTitle() {
			
			try {
				System.out.println("----FAQ 제목 검색----");
				
				// 데이터
				System.out.print("검색할 제목을 입력하세요 : ");
				String searchValue = Main.SC.nextLine();
				
				// 서비스
				List<FaqVo> voList = service.searchFaqByTitle(searchValue);
				
				
				// 결과
				if(voList.size() == 0) {
//					System.out.println("검색결과가 없습니다.");
					throw new Exception("검색 결과가 없습니다");
				}
				
				for(FaqVo vo : voList) {
					System.out.println(vo);
				}
				
			}catch(Exception e) {
				System.out.println("검색 실패");
				e.printStackTrace();
			}
		}
		
		
		//FAQ 검색 (내용)
		public void searchFaqByContent() {
			
			try {
				System.out.println("----FAQ 내용 검색----");
				
				// 데이터
				System.out.print("검색할 내용을 입력하세요 : ");
				String searchValue = Main.SC.nextLine();
				
				// 서비스
				List<FaqVo> voList = service.searchFaqByContent(searchValue);
				
				
				// 결과
				if(voList.size() == 0) {
//					System.out.println("검색결과가 없습니다.");
					throw new Exception("검색 결과가 없습니다");
				}
				
				for(FaqVo vo : voList) {
					System.out.println(vo);
				}
				
			}catch(Exception e) {
				System.out.println("검색 실패");
				e.printStackTrace();
			}
		}

		
		
		//FAQ 제목 수정
		public void titleModify() {
			
			try {
				System.out.println("----FAQ 제목 수정----");
				
				// 로그인 체크
				if(Main.loginAdmin == null) {
					throw new Exception("관리자 로그인이 필요합니다.");
				}
				
				// 데이터
				System.out.print("수정할 FAQ 번호 : ");
				String no = Main.SC.nextLine();
				System.out.print("수정할 FAQ 제목 : ");
				String title = Main.SC.nextLine();
//				System.out.print("수정할 FAQ 내용 : ");
//				String content = Main.SC.nextLine();
				
				FaqVo vo = new FaqVo();
				vo.setFaqno(no);
				vo.setFaqtitle(title);
//				vo.setContent(content);
//				vo.setAdminno(Main.loginAdmin.getAdminNo());
				
				// 서비스
				int result = service.titleModify(vo);
				
				// 결과
				if(result != 1) {
					throw new Exception();
				}
				System.out.println("FAQ 수정 성공");
				
			}catch(Exception e) {
				System.out.println("FAQ 수정 실패");
				e.printStackTrace();
			}
			
		}
		
		
		//FAQ 제목 수정
		public void contentModify() {
			
			try {
				System.out.println("----FAQ 내용 수정----");
				
				// 로그인 체크
				if(Main.loginAdmin == null) {
					throw new Exception("관리자 로그인이 필요합니다.");
				}
				
				// 데이터
				System.out.print("수정할 FAQ 번호 : ");
				String no = Main.SC.nextLine();
				System.out.print("수정할 FAQ 내용 : ");
				String content = Main.SC.nextLine();
				
				FaqVo vo = new FaqVo();
				vo.setFaqno(no);
				vo.setContent(content);
//				vo.setAdminno(Main.loginAdmin.getAdminNo());
				
				// 서비스
				int result = service.contentModify(vo);
				
				// 결과
				if(result != 1) {
					throw new Exception();
				}
				System.out.println("FAQ 수정 성공");
				
			}catch(Exception e) {
				System.out.println("FAQ 수정 실패");
				e.printStackTrace();
			}
			
		}
		
		
		//FAQ 삭제 
		public void delete() {
			try {
				System.out.println("---FAQ 삭제---");
				
				if(Main.loginAdmin == null) {
					throw new Exception("관리자 로그인이 필요합니다");
				}
				
				//데이터
				System.out.println("FAQ 번호 : ");
				String num = Main.SC.nextLine();
				String adminNo = Main.loginAdmin.getAdminNo();
				
				//서비스
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("faqNum", num);
				map.put("loginAdminNo", adminNo);
				int result = service.delete(map);
				
				//결과
				if(result != 1) {
					throw new Exception();
				}
				System.out.println("삭제완료");
			}catch(Exception e) {
				System.out.println("삭제실패");
				e.printStackTrace();
				
			}
		}
			
			

}//class