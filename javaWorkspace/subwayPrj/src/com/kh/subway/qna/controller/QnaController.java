package com.kh.subway.qna.controller;

import java.util.HashMap;
import java.util.List;

import com.kh.subway.main.Main;
import com.kh.subway.qna.service.QnaService;
import com.kh.subway.qna.vo.QnaVo;

public class QnaController {

	// 필드
	private final QnaService service;

	public QnaController() {
		service = new QnaService();
	}

	// 메뉴 선택
	public void selectMenu() {
		
		
		System.out.println("1. 유저 전용 게시판");
		System.out.println("2. 관리자 전용 게시판");
		
		String num1 = Main.SC.nextLine();
		switch (num1) {
		case "1": UserQna(); break;
		case "2": AdminQna(); break;
		default: System.out.println("잘못 입력하셨습니다.");
		}
		}


	private void UserQna() {
		
		System.out.println("===== 유저 전용 게시판 =====");
		
		System.out.println("1. 질의내용 작성"); 
		System.out.println("2. 질의내용 목록 조회 <최신순>");
		System.out.println("3. 질의내용 상세 조회 <번호>");
		System.out.println("4. 질의내용 검색<제목>");
		System.out.println("5. 질의내용 삭제<번호조회>");
		
		String num3= Main.SC.nextLine();
		switch(num3) {
		    case "1": write(); break;
		    case "2": writeList(); break;
			case "3": writeDetailByNo(); break;
			case "4": searchWriteByTitle(); break;
			case "5": writeDelete(); break;
		}
	}
	
	private void AdminQna() {
			System.out.println("===== 관리자 전용 게시판 =====");
		
			System.out.println("1. 질의답변 작성"); 
			System.out.println("2. 질의답변 목록 조회 <최신순>");
			System.out.println("3. 질의답변 상세 조회 <번호>");
			System.out.println("4. 질의답변 검색<제목>");
			System.out.println("5. 질의답변 삭제<번호조회>");
			
			String num = Main.SC.nextLine();
		   switch(num) {
			case "1": reWrite(); break;
			case "2": reWriteList(); break;
			case "3": reWriteDetailByNo(); break;
			case "4": searchReWriteByTitle(); break;
			case "5": reWriteDelete(); break;
		   }
	}
	
	// 작성 (회원 전용)
	public void write() {

		try {
			System.out.println("------질의내용 작성------");

			// 로그인 여부 확인
			if (Main.loginUser == null) {
				throw new Exception("로그인 하고 오세요! 회원만 가능합니다.");
			}
			// 데이터
			System.out.println("유저번호 : ");
			String userNo = Main.SC.nextLine();
			System.out.println("질의제목 : ");
			String title = Main.SC.nextLine();
			System.out.println("질의내용 : ");
			String content = Main.SC.nextLine();

			QnaVo vo = new QnaVo();
			vo.setUserNo(userNo);
			vo.setTitle(title);
			vo.setContent(content);

			// 서비스
			int result = service.write(vo);

			// 결과
			if (result == 1) {
				System.out.println("질의내용 작성 성공!");
			} else {
				throw new Exception();
			}

		} catch (Exception e) {
			System.out.println("질의내용 작성 실패...");
			e.printStackTrace();
		}
	}
	// 답변 (관리자 전용)
	public void reWrite() {

		try {
			System.out.println("------질의답변 작성------");
			
			QnaVo vo = new QnaVo();
			// 로그인 여부 확인
			if (Main.loginAdmin == null) {
				throw new Exception("로그인 하고 오세요! 관리자만 가능합니다.");
			}else {
				System.out.println("답변 대상 질문의 번호를 입력하세요.");
				String userNo = Main.SC.nextLine();
				// 데이터
				System.out.println("관리자 번호 : ");
				String adminNo = Main.SC.nextLine();
				System.out.println("답변제목 : ");
				String reTitle = Main.SC.nextLine();
				System.out.println("답변내용 : ");
				String reContent = Main.SC.nextLine();

				
				vo.setReTitle(reTitle);
				vo.setReContent(reContent);
				vo.setUserNo(userNo);
			}
			// 서비스
			int result = service.reWrite(vo);

			// 결과
			if (result == 1) {
				System.out.println("질의내용 작성 성공!");
			} else {
				throw new Exception();
			}

		} catch (Exception e) {
			System.out.println("질의내용 작성 실패...");
			e.printStackTrace();
		}
	}
	
	//목록조회<질의>
	public void writeList() {

		try {
			System.out.println("------------질의내용 목록(최신순)------------");
			
			// 로그인 여부 확인
			if (Main.loginUser == null) {
				throw new Exception("로그인 하고 오세요! 회원만 가능합니다.");
			}

			// 서비스 호출
			List<QnaVo> voList = service.writeList();
			// 결과
			System.out.print("전체번호");
			System.out.print("/");
			System.out.print("유저번호");
			System.out.print("/");
			System.out.print("닉네임");
			System.out.print("/");
			System.out.print("질의 제목");
			System.out.print("/");
			System.out.print("질의 내용");
			System.out.print("/");
			System.out.print("조회수");
			System.out.print("/");
			System.out.print("작성일자");
			System.out.println();

			for (QnaVo vo : voList) {
				System.out.print(vo.getQnaNo());
				System.out.print("/");
				System.out.print(vo.getUserNo());
				System.out.print("/");
				System.out.print(vo.getWriterNick());
				System.out.print("/");
				System.out.print(vo.getTitle());
				System.out.print("/");
				System.out.print(vo.getContent());
				System.out.print("/");
				System.out.print(vo.getInquiry());
				System.out.print("/");
				System.out.print(vo.getPostTime());

				System.out.println();
			}

		} catch (Exception e) {
			System.out.println("질의내용 목록 조회 실패...");
			e.printStackTrace();
		}

	}
	
	 // 상세 조회 - 번호를 이용해서 모든 칼럼 조회
	public void writeDetailByNo() {

		try {
			System.out.println("----------질의내용 상세 조회(번호)------------");
			
			// 로그인 여부 확인
			if (Main.loginUser == null) {
				throw new Exception("로그인 하고 오세요! 회원만 가능합니다.");
			}

			// 데이터
			System.out.print("조회할 유저 번호 : ");
			String num = Main.SC.nextLine();
			// 서비스
			QnaVo vo = service.writeDetailByNo(num);
			// 결과
			if (vo == null) {
				throw new Exception();
			}
			
			System.out.println("------------------------------");
			System.out.println("유저번호 : " + vo.getUserNo());
			System.out.println("질의제목 : " + vo.getTitle());
			System.out.println("질의내용 : " + vo.getContent());
			System.out.println("닉네임 : " + vo.getWriterNick());
			System.out.println("조회수 : " + vo.getInquiry());
			System.out.println("작성일시 : " + vo.getPostTime());
			System.out.println("전체번호 : " + vo.getQnaNo());
			System.out.println("------------------------------");
			
		} catch (Exception e) {
			System.out.println("질의내용 상세 조회 실패...");
			e.printStackTrace();
		}
	}
	public void reWriteList() {
		try {
			System.out.println("------------질의답변 목록(최신순)------------");
			
				// 로그인 여부 확인
				if (Main.loginAdmin == null) {
					throw new Exception("로그인 하고 오세요! 관리자만 가능합니다.");
				}

			// 서비스 호출
			List<QnaVo> voList = service.reWriteList();
			// 결과
			System.out.print("관리자 번호");
			System.out.print("/");
			System.out.print("질의답변 제목");
			System.out.print("/");
			System.out.print("질의답변 내용");
			System.out.print("/");
			System.out.print("조회수");
			System.out.print("/");
			System.out.print("작성일자");
			System.out.println();

			for (QnaVo vo : voList) {
				System.out.print(vo.getAdminNo());
				System.out.print("/");
				System.out.print(vo.getReTitle());
				System.out.print("/");
				System.out.print(vo.getReContent());
				System.out.print("/");
				System.out.print(vo.getInquiry());
				System.out.print("/");
				System.out.print(vo.getPostTime());

				System.out.println();
			}

		} catch (Exception e) {
			System.out.println("질의내용 목록 조회 실패...");
			e.printStackTrace();
		}
	}
	

	public void searchWriteByTitle() {
		try {
			System.out.println("--------------회원게시글_검색--------------");
			
			// 로그인 여부 확인
			if (Main.loginUser == null) {
				throw new Exception("로그인 하고 오세요! 회원만 가능합니다.");
			}
			
			//데이터
			System.out.println("검색할 유저번호(USER_NO) : ");
			String searchValue = Main.SC.nextLine();
			//사비스 호출
			List<QnaVo>voList = service.searchWriteByTitle(searchValue);
			//결과 처리
			if(voList.size() == 0) {
				System.out.println("검색 결과가 없습니다.");
			}
			for(QnaVo vo : voList) {
				System.out.println(vo);
			}
			}catch(Exception e) {
				System.out.println("게시글 검색 실패..");
				e.printStackTrace();
	}
}
	
	public void reWriteDetailByNo() {

		try {
			System.out.println("----------질의답변 상세 조회(관리자 번호)------------");

			// 로그인 여부 확인
			if (Main.loginAdmin == null) {
				throw new Exception("로그인 하고 오세요! 관리자만 가능합니다.");
			}
			
			// 데이터
			System.out.print("조회할 관리자 번호 : ");
			String num = Main.SC.nextLine();
			// 서비스
			QnaVo vo = service.reWriteDetailByNo(num);
			// 결과
			if (vo == null) {
				throw new Exception();
			}
			
			System.out.println("------------------------------");
			System.out.println("관리자 번호 : " + vo.getAdminNo());
			System.out.println("답변제목 : " + vo.getReTitle());
			System.out.println("답변내용 : " + vo.getReContent());
			System.out.println("조회수 : " + vo.getInquiry());
			System.out.println("작성일시 : " + vo.getPostTime());
			System.out.println("------------------------------");
			
		} catch (Exception e) {
			System.out.println("답변내용 상세 조회 실패...");
			e.printStackTrace();
		}
}	
	
	public void searchReWriteByTitle() {
		try {
			System.out.println("---------------관리자 게시글_검색---------------");
			
			// 로그인 여부 확인
			if (Main.loginAdmin == null) {
				throw new Exception("로그인 하고 오세요! 관리자만 가능합니다.");
			}
			//데이터
			System.out.println("검색할 관리자번호(ADMIN_NO) : ");
			String num = Main.SC.nextLine();
			String searchValue = Main.loginAdmin.getAdminNo();
			//사비스 호출
			List<QnaVo>voList = service.searchReWriteByTitle(searchValue);
			//결과 처리
			if(voList.size() == 0) {
				System.out.println("검색 결과가 없습니다.");
			}
			for(QnaVo vo : voList) {
				System.out.println(vo);
			}
			}catch(Exception e) {
				System.out.println("관리자 게시글 검색 실패..");
				e.printStackTrace();
	}
	}
	
	public void writeDelete() {
		try {
			if(Main.loginUser == null) {
				throw new Exception("유저 로그인 안해서 에러 발생");
			}
			//데이터
			System.out.println("유저 게시글 번호 : ");
			String num = Main.SC.nextLine();
			String userNo = Main.loginUser.getUserNo();
			
			//서비스
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("qnaNum", num);
			map.put("loginUser", userNo);
			int result = service.writeDelete(map);
			
			//결과
			if(result != 1) {
				throw new Exception();
			}
			System.out.println("유저 게시글 삭제 완료..!!!");
		}catch(Exception e) {
			System.out.println("유저 게시글 삭제 실패...");
			e.printStackTrace();
		}
	}

	public void reWriteDelete() {
		try {
			if(Main.loginAdmin == null) {
				throw new Exception("관리자 로그인 안해서 에러 발생");
			}
			//데이터
			System.out.println("관리자 게시글 번호 : ");
			String num = Main.SC.nextLine();
			String adminNo = Main.loginAdmin.getAdminNo();
			
			//서비스
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("qnaNum", num);
			map.put("loginAdmin", adminNo);
			int result = service.reWriteDelete(map);
			
			//결과
			if(result != 1) {
				throw new Exception();
			}
			System.out.println("관리자 게시글 삭제 완료..!!!");
		}catch(Exception e) {
			System.out.println("관리자 게시글 삭제 실패...");
			e.printStackTrace();
		}
	}
	}