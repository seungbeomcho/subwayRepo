package com.kh.subway.notice.controller;

import java.util.HashMap;
import java.util.List;

import com.kh.subway.faq.vo.FaqVo;
import com.kh.subway.main.Main;
import com.kh.subway.notice.service.NoticeService;
import com.kh.subway.notice.vo.NoticeVo;

public class NoticeController {
	
	
	private final NoticeService service;
	
	public NoticeController() {
		service = new NoticeService();
	}
	
	//관리자 메뉴
		public void adminMenu() {
		boolean isMenu = true;
			while (isMenu) {
				System.out.println("---공지사항 관리자 메뉴---");
				
				System.out.println("1. 공지사항 작성");
				System.out.println("2. 공지사항 제목 수정");
				System.out.println("3. 공지사항 내용 수정");
				System.out.println("4. 공지사항 삭제");
				System.out.println("5. 로그아웃");
				System.out.println("9. 돌아가기");
				
				String num = Main.SC.nextLine();
				switch(num) {
				case "1" : write(); break;
				case "2" : titleModify(); break;
				case "3" : contentModify(); break;
				case "4" : delete(); break;
				case "5" : Logout(); break;
				case "9" : isMenu = false;break;
				
				default : System.out.println("잘못입력하셨습니다");
			}
		}
	}
	
	//관리자 로그아웃
	public void Logout() {
		Main.loginAdmin = null;
		System.out.println("로그아웃");
	}
	
	
	//공지사항 메뉴선택
	public void selectMenu() {
		boolean isMenu = true;
			while(isMenu) {
				System.out.println("----공지사항----");
				
				System.out.println("1. 공지사항 목록조회");
				System.out.println("2. 공지사항 상세조회 (번호)");
				System.out.println("3. 공지사항 검색 (제목)");
				System.out.println("4. 공지사항 검색 (내용)");
				System.out.println("9. 돌아가기");
				
				String num = Main.SC.nextLine();
				switch(num) {
				case "1" : noticeList(); break;
				case "2" : noticeDetailByNo(); break;
				case "3" : searchNoticeByTitle(); break;
				case "4" : searchNoticeByContent(); break;
				case "9" : isMenu = false;break;
				
				default : System.out.println("잘못입력하셨습니다");
			}
		}
	}


	//공지사항 작성 (관)
	public void write() {
		
		try {
			System.out.println("----공지사항 작성----");
			
			//관리자 로그인 체크
			if(Main.loginAdmin == null) {
				throw new Exception("관리자 로그인이 필요합니다.");
			}
			
			//데이터
			System.out.println("역이름 : ");
			String stationname = Main.SC.nextLine();
			System.out.println("제목 : ");
			String title = Main.SC.nextLine();
			System.out.println("내용 : ");
			String content = Main.SC.nextLine();
			
			NoticeVo vo = new NoticeVo();
			vo.setTitle(title);
			vo.setContent(content);
			vo.setStationname(stationname);
			
			//서비스
			int result = service.writer(vo);
			
			//결과
			if(result != 1) {
				throw new Exception();
			}
			System.out.println("공지사항 작성 성공");
		}catch (Exception e) {
			System.out.println("공지사항 작성 실패");
			e.printStackTrace();
		}
	}//write
	
	
	//공지사항 제목 수정
	public void titleModify() {
		
		try {
			System.out.println("----공지사항 제목 수정----");
			
			// 로그인 체크
			if(Main.loginAdmin == null) {
				throw new Exception("관리자 로그인이 필요합니다.");
			}
			
			// 데이터
			System.out.print("수정할 공지사항 번호 : ");
			String no = Main.SC.nextLine();
			System.out.print("수정할 공지사항 제목 : ");
			String title = Main.SC.nextLine();
			
			NoticeVo vo = new NoticeVo();
			vo.setNoticeno(no);
			vo.setTitle(title);
			
			// 서비스
			int result = service.titleModify(vo);
			
			// 결과
			if(result != 1) {
				throw new Exception();
			}
			System.out.println("공지사항 수정 성공");
			
		}catch(Exception e) {
			System.out.println("공지사항 수정 실패");
			e.printStackTrace();
		}
		
	}
	
	
	//공지사항 내용 수정
	public void contentModify() {
		
		try {
			System.out.println("----공지사항 내용 수정----");
			
			// 로그인 체크
			if(Main.loginAdmin == null) {
				throw new Exception("관리자 로그인이 필요합니다.");
			}
			
			// 데이터
			System.out.print("수정할 공지사항 번호 : ");
			String no = Main.SC.nextLine();
			System.out.print("수정할 공지사항 내용 : ");
			String content = Main.SC.nextLine();
			
			NoticeVo vo = new NoticeVo();
			vo.setNoticeno(no);
			vo.setContent(content);
//			vo.setAdminno(Main.loginAdmin.getAdminNo());
			
			// 서비스
			int result = service.contentModify(vo);
			
			// 결과
			if(result != 1) {
				throw new Exception();
			}
			System.out.println("공지사항 수정 성공");
			
		}catch(Exception e) {
			System.out.println("공지사항 수정 실패");
			e.printStackTrace();
		}
		
	}
	
	
	
	//공지사항 삭제
	public void delete() {
		try {
			System.out.println("---공지사항 삭제---");
			
			if(Main.loginAdmin == null) {
				throw new Exception("관리자 로그인이 필요합니다");
			}
			
			System.out.print("공지사항 번호 : ");
			String no = Main.SC.nextLine();
			
			int result = service.delete(no);
			
			if(result != 1) {
				throw new Exception();
			}
			
			System.out.println("공지사항 삭제 성공");
			
		}catch(Exception e) {
			System.out.println("공지사항 삭제 실패");
			e.printStackTrace();
		}
	}
	
	
	
	//공지사항 목록조회 (최신순)
		public void noticeList() {
			
			try {
				System.out.println("---- 공지사항 목록 ----");
				
				List<NoticeVo> voList = service.noticeList();
				
				System.out.print("번호");
				System.out.print(" / ");
				System.out.print("제목");
				System.out.print(" / ");
				System.out.print("조회수");
				System.out.print(" / ");
				System.out.print("작성일자");
				System.out.println();
				
				for(NoticeVo vo : voList) {
					System.out.print(vo.getNoticeno());
					System.out.print("/");
					System.out.print(vo.getTitle());
					System.out.print("/");
					System.out.print(vo.getInqiry());
					System.out.print("/");
					System.out.print(vo.getPosttime());
					System.out.println();
				}
				
			}catch (Exception e) {
				System.out.println("공지사항 목록 조회 실패");
				e.printStackTrace();
			}
		} //noticeList
		
		
		//공지사항 상세 조회 (번호)
		public void noticeDetailByNo() {
			
			try {
				System.out.println("----공지사항 상세조회----");
				
				//데이터
				System.out.println("조회할 공지사항 번호 : ");
				String num = Main.SC.nextLine();
				
				//서비스 호출
				NoticeVo vo = service.noticeDetailByNo(num);

				
				//결과처리
				if(vo == null) {
					throw new Exception("공지사항 상세조회 실패");
				}
				System.out.println("--------------------------");
				System.out.println("글번호 : " + vo.getNoticeno());
				System.out.println("역번호 : " + vo.getStationno());
				System.out.println("제목 : " + vo.getTitle());
				System.out.println("조회수 : " + vo.getInqiry());
				System.out.println("작성일시 : " + vo.getPosttime());
				System.out.println("내용 : " + vo.getContent());
				
				
			}catch (Exception e) {
				System.out.println("공지사항 상세 조회 실패");
				e.printStackTrace();
				
			}
		} //noticeDetailByNo
	
	
		//공지사항 검색 (제목)
		private void searchNoticeByTitle() {
			
			try {
				System.out.println("----공지사항 제목 검색----");
				
				// 데이터
				System.out.print("검색할 제목을 입력하세요 : ");
				String searchValue = Main.SC.nextLine();
				
				// 서비스
				List<NoticeVo> voList = service.searchNoticeByTitle(searchValue);
				
				
				// 결과
				if(voList.size() == 0) {
//					System.out.println("검색결과가 없습니다.");
					throw new Exception("검색 결과가 없습니다");
				}
				
				for(NoticeVo vo : voList) {
					System.out.print("NO :" + vo.getNoticeno() + " ");
					System.out.print("제목 : " + vo.getTitle());
					System.out.println();
					System.out.print("내용 : " + vo.getContent());
					System.out.println();
					System.out.print("역이름 : " + vo.getStationname());
					System.out.println();
					System.out.print("작성일시 : " + vo.getPosttime());
					System.out.println();
					System.out.print("조회수 : " + vo.getInqiry());
					System.out.println();
					System.out.println("===================================");
				}
				
			}catch(Exception e) {
				System.out.println("검색 실패");
				e.printStackTrace();
			}
		}
	
		
		//공지사항 검색 (내용)
		private void searchNoticeByContent() {
			
			try {
				System.out.println("----공지사항 내용 검색----");
				
				// 데이터
				System.out.print("검색할 내용을 입력하세요 : ");
				String searchValue = Main.SC.nextLine();
				
				// 서비스
				List<NoticeVo> voList = service.searchNoticeByContent(searchValue);
				
				
				// 결과
				if(voList.size() == 0) {
//					System.out.println("검색결과가 없습니다.");
					throw new Exception("검색결과가 없습니다");
				}
				
				for(NoticeVo vo : voList) {
					System.out.print("NO :" + vo.getNoticeno() + " ");
					System.out.print("제목 : " + vo.getTitle());
					System.out.println();
					System.out.print("내용 : " + vo.getContent());
					System.out.println();
					System.out.print("역이름 : " + vo.getStationname());
					System.out.println();
					System.out.print("작성일시 : " + vo.getPosttime());
					System.out.println();
					System.out.print("조회수 : " + vo.getInqiry());
					System.out.println();
					System.out.println("===================================");
				}
				
			}catch(Exception e) {
				System.out.println("검색 실패");
				e.printStackTrace();
			}
		}
	
}//CLASS