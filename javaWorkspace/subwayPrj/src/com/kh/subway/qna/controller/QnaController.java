package com.kh.subway.qna.controller;

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
		System.out.println("===== QNA =====");

		System.out.println("1. 질의내용 작성"); 
		System.out.println("2. 질의답변 작성"); 
		System.out.println("3. 질의내용 목록 조회 <최신순>");
		System.out.println("4. 질의내용 상세 조회 <번호>");
		System.out.println("5. 질의답변 목록 조회 <최신순>");
		System.out.println("6. 질의답변 상세 조회 <번호>");

		String num = Main.SC.nextLine();
		switch (num) {
		case "1": write(); break;
		case "2": reWrite(); break;
		case "3": qnaList(); break;
		case "4": qnaDetailByNo(); break;
//		case "5": reQnaList(); break;
//		case "6": reQnaDetailByNo(); break;
		default: System.out.println("잘못 입력하셨습니다.");
		}
	}

	/**
	 * 
	 * 작성(회원전용)
	 * 
	 * 제목, 내용, 입력받아서 BOARD 테이블에 INSERT
	 * 
	 * INSERT INTO BOARD(NO, TITLE, CONTENT, WRITER_NO) VALUES(시퀸스, 제목, 내용, 현재 로그인
	 * 유저 정보)
	 * 
	 */

	// 작성 (회원 전용)
	public void write() {

		try {
			System.out.println("------질의내용 작성------");

			// 로그인 여부 확인
			if (Main.loginUser == null) {
				throw new Exception("로그인 하고 오세요! 회원만 가능합니다.");
			}
			// 데이터
			System.out.println("질의제목 : ");
			String title = Main.SC.nextLine();
			System.out.println("질의내용 : ");
			String content = Main.SC.nextLine();

			QnaVo vo = new QnaVo();
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
			
			QnaVo vo = null;
			// 로그인 여부 확인
			if (Main.loginAdmin == null) {
				throw new Exception("로그인 하고 오세요! 관리자만 가능합니다.");
			}else {
				String adminNo = Main.loginAdmin.getAdminNo();
				// 데이터
				System.out.println("답변제목 : ");
				String reTitle = Main.SC.nextLine();
				System.out.println("답변내용 : ");
				String reContent = Main.SC.nextLine();

				vo = new QnaVo();
				
				vo.setReTitle(reTitle);
				vo.setReContent(reContent);
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
	// 삭제 (작성자 본인만)

	// 수정하기 (제목, 내용)
	/**
	 * 목록 조회 - 등록일(번호, 제목, 작성자닉네임, 조회수, 작성일시)
	 * 
	 * SELECT * FROM BOARD WHERE DEL_YN = 'N' ORDER BY NO DESC;
	 */

	public void qnaList() {

		try {
			System.out.println("------------질의내용 목록(최신순)------------");

			// 데이터 - 입력받을 것이 없기 때문에 생략

			// 서비스 호출
			List<QnaVo> voList = service.qnaList();
			// 결과
			System.out.print("번호");
			System.out.print("/");
			System.out.print("제목");
			System.out.print("/");
			System.out.print("닉네임");
			System.out.print("/");
			System.out.print("조회수");
			System.out.print("/");
			System.out.print("작성일자");
			System.out.println();

			for (QnaVo vo : voList) {
				System.out.print(vo.getQnaNo());
				System.out.print("/");
				System.out.print(vo.getTitle());
				System.out.print("/");
				System.out.print(vo.getUserNo());
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
	// 목록 조회 - 조회수(번호, 제목, 작성자닉네임, 조회수, 작성일시)

	// 제목으로 검색 - 제목
	// 제목으로 검색 - 작성자 닉네임
	// 제목으로 검색 - 내용

	/**
	 * 상세 조회 - 번호를 이용해서 모든 칼럼 조회
	 * 
	 * SELECT * FROM BOARD WHERE NO = ? AND DEL_YN = 'N'
	 * 
	 */
	public void qnaDetailByNo() {

		try {
			System.out.println("----------질의내용 상세 조회(번호)------------");

			// 데이터
			System.out.print("조회할 질의 번호 : ");
			String num = Main.SC.nextLine();
			// 서비스
			QnaVo vo = service.qnaDetailByNo(num);
			// 결과
			if (vo == null) {
				throw new Exception();
			}
			
			System.out.println("------------------------------");
			System.out.println("글번호 : " + vo.getQnaNo());
			System.out.println("질의제목 : " + vo.getTitle());
			System.out.println("질의내용 : " + vo.getContent());
			System.out.println("닉네임 : " + vo.getWriterNick());
			System.out.println("조회수 : " + vo.getInquiry());
			System.out.println("작성일시 : " + vo.getPostTime());
			System.out.println("유저번호 : " + vo.getUserNo());
			System.out.println("------------------------------");
			
		} catch (Exception e) {
			System.out.println("질의내용 상세 조회 실패...");
			e.printStackTrace();
		}
	}
}
