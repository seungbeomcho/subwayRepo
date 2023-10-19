package com.kh.subway.notice.controller;

import com.kh.subway.main.Main;
import com.kh.subway.notice.service.NoticeService;
import com.kh.subway.notice.vo.NoticeVo;

public class NoticeController {
	
	
	private final NoticeService service;
	
	public NoticeController() {
		service = new NoticeService();
	}
	
	public void adminMenu() {
		
	}
	
	//메뉴선택
	public void selectMenu() {
		System.out.println("----공지사항----");
		
		System.out.println("1. 공지사항 작성");
//		System.out.println("2. 공지사항 목록");
//		System.out.println("3. 공지사항 조회");
//		System.out.println("4. 공지사항 수정");
//		System.out.println("5. 공지사항 삭제");
		
		String num = Main.SC.nextLine();
		switch(num) {
		case "1" : write(); break;
//		case "2" : noticeList(); break;
//		case "3" : noticeDetailByNo(); break;
//		case "4" : noticeModify(); break;
//		case "5" : noticeDelete(); break;
		default : System.out.println("잘못입력하셨습니다");
		}
	}

	//공지사항 작성
	public void write() {
		
		try {
			System.out.println("----공지사항 작성----");
			
			//관리자 로그인 체크
			if(Main.loginAdmin == null) {
				throw new Exception("관리자 로그인이 필요합니다.");
			}
			
			//데이터
			System.out.println("제목 : ");
			String title = Main.SC.nextLine();
			System.out.println("내용 : ");
			String content = Main.SC.nextLine();
			
			NoticeVo vo = new NoticeVo();
			vo.setTitle(title);
			vo.setContent(content);
			
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
	
	
	
	
	
	
	
	
	
	
	
	
}//CLASS
