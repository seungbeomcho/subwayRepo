package com.kh.subway.qna.controller;

import java.util.HashMap;
import java.util.List;

import com.kh.subway.board.vo.BoardVo;
import com.kh.subway.main.Main;
import com.kh.subway.qna.service.QnaService;
import com.kh.subway.qna.vo.QnaVo;
import com.kh.subway.user.controller.UserController;
import com.kh.subway.user.vo.UserVo;

public class QnaController {

	// 필드
	private final QnaService service;

	public QnaController() {
		service = new QnaService();
	}

	// 메뉴 선택
	public void selectMenu() {
		
		
		System.out.println("1. 유저 회원가입 및 로그인");
		System.out.println("2. QNA 게시판<유저 전용>");
		System.out.println("3. QNA 게시판<관리자 전용>");
		System.out.println("9. QNA 게시판 나가기");
		
		UserController user = new UserController();
		
		String num1 = Main.SC.nextLine();
		switch (num1) {
		case "1": user.selectMenu(); break;
		case "2": UserQna(); break;
		case "3": AdminQna(); break;
		case "9": return;
		default: System.out.println("잘못 입력하셨습니다.");
		}
		}


	private void UserQna() {
		
		System.out.println("===== 유저 전용 게시판 =====");
		
		System.out.println("1. 질의 작성"); 
		System.out.println("2. 질의 목록 조회 <최신순>");
		System.out.println("3. 질의 상세 조회 <번호>");
		System.out.println("4. 질의 검색<제목>");
		System.out.println("5. 질의 삭제<유저번호 조회>");
		System.out.println("6. 질의 제목 및 내용 수정");
		System.out.println("9. 나가기");
		
		String num3= Main.SC.nextLine();
		switch(num3) {
		    case "1": write(); break;
		    case "2": writeList(); break;
			case "3": reWriteDetailByNo(); break;
			case "4": searchWriteByTitle(); break;
			case "5": writeDelete(); break;
			case "6": correct(); break;
			case "9": return;
			default : System.out.println("번호를 다시 입력해주세요.");
		}
	}
	
	public void correct() {
		System.out.println("===== 수정하기(유저) =====");
		
		System.out.println("1. 제목 수정하기"); 
		System.out.println("2. 내용 수정하기");
		
		String num= Main.SC.nextLine();
		switch(num) {
		    case "1": titleCorrect(); break;
		    case "2": contentCorrect(); break;
		    case "9": return;
		    default : System.out.println("번호를 다시 입력해주세요.");
		}
	}


	//회원게시글 수정(제목)
	private void titleCorrect() {
		
		
			try {
				
				System.out.println("=======게시글 수정_(유저 전용 제목)=========");
				QnaVo vo = new QnaVo();
				if(Main.loginUser != null) {
					System.out.print("변경할 QNA 번호 : ");
					String qnaNo = Main.SC.nextLine();
					System.out.print("변경할 제목 : ");
					String title = Main.SC.nextLine();
					vo.setTitle(title);
					vo.setQnaNo(qnaNo);
				}
				
				int result = service.titleCorrect(vo);
				
				if(result != 1) {
					throw new Exception();
				}
				System.out.println("게시글 제목 수정 성공 !");
				
				
			}catch(Exception e){
				System.out.println("게시글 제목 수정 실패 ...");
				e.printStackTrace();
			}
			
		}
	//회원게시글 수정(내용)
	 private void contentCorrect() {
		
		 try {
				QnaVo vo = new QnaVo();
				if(Main.loginUser != null) {
					System.out.print("변경할 QNA 번호 : ");
					String qnaNo = Main.SC.nextLine();
					System.out.print("변경할 내용 : ");
					String content = Main.SC.nextLine();
					vo.setContent(content);
					vo.setQnaNo(qnaNo);
				}
				
				int result = service.contentCorrect(vo);
				
				if(result != 1) {
					throw new Exception();
				}
				System.out.println("게시글 내용 수정 성공 !");
				
				
			}catch(Exception e){
				System.out.println("게시글 내용 수정 실패 ...");
				e.printStackTrace();
			}
	}

	private void AdminQna() {
			System.out.println("===== 관리자 전용 게시판 =====");
		
			System.out.println("1. 답변 작성"); 
			System.out.println("2. 답변 목록 조회 <최신순>");
			System.out.println("3. 질의답변 상세 조회 <번호, 유저 사용 불가>");
			System.out.println("4. 답변 검색<제목>");
			System.out.println("5. 답변 삭제<관리자 번호조회>");
			System.out.println("6. 답변 제목 및 내용 수정");
			System.out.println("9. 나가기");
			
			String num = Main.SC.nextLine();
		   switch(num) {
			case "1": reWrite(); break;
			case "2": reWriteList(); break;
			case "3": reWriteDetailByNo(); break;
			case "4": searchReWriteByTitle(); break;
			case "5": reWriteDelete(); break;
			case "6": reCorrect(); break;
			case "9": return;
			default : System.out.println("번호를 다시 입력해주세요.");
		   }
	}
	
	public void reCorrect() {
		
		System.out.println("===== 수정하기(관리자) =====");
		
		System.out.println("1. 제목 수정하기"); 
		System.out.println("2. 내용 수정하기");
		
		String num= Main.SC.nextLine();
		switch(num) {
		    case "1": reTitleCorrect(); break;
		    case "2": reContentCorrect(); break;
		    case "9": return;
		    default : System.out.println("번호를 다시 입력해주세요.");
	}
	}
	
	private void reTitleCorrect() {
	
		 try {
				QnaVo vo = new QnaVo();
				if(Main.loginAdmin != null) {
					System.out.print("변경할 QNA_ADMIN 번호 : ");
					String qnaAdminNo = Main.SC.nextLine();
					System.out.print("변경할 답변제목 : ");
					String reTitle = Main.SC.nextLine();
					vo.setReTitle(reTitle);
					vo.setQnaAdminNo(qnaAdminNo);
				}
				
				int result = service.reTitleCorrect(vo);
				
				if(result != 1) {
					throw new Exception();
				}
				System.out.println("게시글 제목 수정 성공 !");
				
				
			}catch(Exception e){
				System.out.println("게시글 제목 수정 실패 ...");
				e.printStackTrace();
			}
	}
	
	private void reContentCorrect() {
		
		try {
			QnaVo vo = new QnaVo();
			if(Main.loginAdmin != null) {
				System.out.print("변경할 QNA_ADMIN 번호 : ");
				String qnaAdminNo = Main.SC.nextLine();
				System.out.print("변경할 내용 : ");
				String reContent = Main.SC.nextLine();
				vo.setReContent(reContent);
				vo.setQnaAdminNo(qnaAdminNo);
			}
			
			int result = service.reContentCorrect(vo);
			
			if(result != 1) {
				throw new Exception();
			}
			System.out.println("게시글 내용 수정 성공 !");
			
			
		}catch(Exception e){
			System.out.println("게시글 내용 수정 실패 ...");
			e.printStackTrace();
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
			System.out.println("역이름 : ");
			String stationNo = Main.SC.nextLine();
			System.out.println("질의제목 : ");
			String title = Main.SC.nextLine();
			System.out.println("질의내용 : ");
			String content = Main.SC.nextLine();

			QnaVo vo = new QnaVo();
			vo.setUserNo(Main.loginUser.getUserNo());
			vo.setStationNo(stationNo);
			vo.setTitle(title);
			vo.setContent(content);

			// 서비스
			int result = service.write(vo);

			// 결과
			if (result == 1) {
				System.out.println("질의 작성 성공!");
			} else {
				throw new Exception();
			}

		} catch (Exception e) {
			System.out.println("질의 작성 실패...");
			e.printStackTrace();
		}
	}
	// 답변 (관리자 전용)
	public void reWrite() {

		try {
			System.out.println("------질의답변 작성(관리자)------");
			
			QnaVo vo = new QnaVo();
			// 로그인 여부 확인
			if (Main.loginAdmin == null) {
				throw new Exception("로그인 하고 오세요! 관리자만 가능합니다.");
			}else {
				System.out.println("답변할 QNA_NO : ");
				String qnaNo = Main.SC.nextLine();
				// 데이터
				System.out.println("답변제목 : ");
				String reTitle = Main.SC.nextLine();
				System.out.println("답변내용 : ");
				String reContent = Main.SC.nextLine();
				
				vo.setQnaNo(qnaNo);
				vo.setReTitle(reTitle);
				vo.setReContent(reContent);
				vo.setAdminNo(Main.loginAdmin.getAdminNo());
			}
			// 서비스
			int result = service.reWrite(vo);

			// 결과
			if (result == 1) {
				System.out.println("질의답변 작성 성공!");
			} else {
				throw new Exception();
			}

		} catch (Exception e) {
			System.out.println("질의답변 작성 실패...");
			e.printStackTrace();
		}
	}
	
	//목록조회<질의>
	public void writeList() {

		try {
			System.out.println("------------질의목록(최신순)------------");
			
			// 로그인 여부 확인
			if (Main.loginUser == null) {
				throw new Exception("로그인 하고 오세요! 회원만 가능합니다.");
			}

			// 서비스 호출
			List<QnaVo> voList = service.writeList();
			// 결과
			System.out.print("QNA번호");
			System.out.print("/");
			System.out.print("유저번호");
			System.out.print("/");
			System.out.print("닉네임");
			System.out.print("/");
			System.out.print("역번호");
			System.out.print("/");
			System.out.print("역이름");
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
				System.out.print(vo.getStationNo());
				System.out.print("/");
				System.out.print(vo.getStationName());
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
			System.out.println("질의목록 조회 실패...");
			e.printStackTrace();
		}

	}
	
	 // 상세 조회 - 번호를 이용해서 모든 칼럼 조회
	public void writeDetailByNo() {

		try {
			System.out.println("----------질의 상세조회(QNA번호)------------");
			
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
			System.out.println("QNA번호 : " + vo.getQnaNo());
			System.out.println("역 이름 : " + vo.getStationName());
			System.out.println("닉네임 : " + vo.getWriterNick());
			System.out.println("질의제목 : " + vo.getTitle());
			System.out.println("질의내용 : " + vo.getContent());
			System.out.println("답변제목 : " + vo.getReTitle());
			System.out.println("답변내용 : " + vo.getReContent());
			System.out.println("조회수 : " + vo.getInquiry());
			System.out.println("질의일자 : " + vo.getPostTime());
			System.out.println("답변일자 : " + vo.getRePostTime());
			System.out.println("------------------------------");
			
		} catch (Exception e) {
			System.out.println("질의내용 상세 조회 실패...");
			e.printStackTrace();
		}
	}
	public void reWriteList() {
		try {
			System.out.println("------------질의답변 목록(관리자)------------");
			
				// 로그인 여부 확인
				if (Main.loginAdmin == null) {
					throw new Exception("로그인 하고 오세요! 관리자만 가능합니다.");
				}

			// 서비스 호출
			List<QnaVo> voList = service.reWriteList();
			// 결과
			System.out.print("QNA_ADMIN 번호");
			System.out.print("/");
			System.out.print("질의답변 제목");
			System.out.print("/");
			System.out.print("질의답변 내용");
			System.out.print("/");
			System.out.print("조회수");
			System.out.print("/");
			System.out.print("관리자 작성일자");
			System.out.print("/");
			System.out.println();

			for (QnaVo vo : voList) {
				System.out.print(vo.getQnaAdminNo());
				System.out.print("/");
				System.out.print(vo.getReTitle());
				System.out.print("/");
				System.out.print(vo.getReContent());
				System.out.print("/");
				System.out.print(vo.getInquiry());
				System.out.print("/");
				System.out.print(vo.getRePostTime());
				System.out.print("/");

				System.out.println();
			}

		} catch (Exception e) {
			System.out.println("질의내용 목록 조회 실패...");
			e.printStackTrace();
		}
	}
	

	public void searchWriteByTitle() {
		try {
			System.out.println("--------------회원 질의_검색(제목)--------------");
			
			// 로그인 여부 확인
			if (Main.loginUser == null) {
				throw new Exception("로그인 하고 오세요! 회원만 가능합니다.");
			}
			
			//데이터
			System.out.println("검색할 질의제목 : ");
			String searchValue = Main.SC.nextLine();
			//서비스 호출
			List<QnaVo>voList = service.searchWriteByTitle(searchValue);
			//결과 처리
			if(voList.size() == 0) {
				System.out.println("검색 결과가 없습니다.");
			}
			System.out.print("QNA번호");
			System.out.print("/");
			System.out.print("유저번호");
			System.out.print("/");
			System.out.print("닉네임");
			System.out.print("/");
			System.out.print("역이름");
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
				System.out.print(vo.getStationName());
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
			}catch(Exception e) {
				System.out.println("게시글 검색 실패..");
				e.printStackTrace();
	}
}
	
	public void reWriteDetailByNo() {

		try {
			System.out.println("----------질의답변 상세조회(관리자 전용, 유저 사용 불가)------------");

			
			// 데이터
			System.out.print("조회할 QNA 번호 : ");
			String num = Main.SC.nextLine();
			// 서비스
			QnaVo vo = service.reWriteDetailByNo(num);
			
			// 결과
			if (vo == null) {
				throw new Exception();
			}
			
			System.out.println("------------------------------");
			System.out.println("질문 QNA 번호 : " + vo.getQnaNo());
			System.out.println("역이름 : " + vo.getStationName());
			System.out.println("질문제목 : " + vo.getTitle());
			System.out.println("질문내용 : " + vo.getContent());
			System.out.println("답변제목 : " + vo.getReTitle());
			System.out.println("답변내용 : " + vo.getReContent());
			System.out.println("작성자 닉네임 : " + vo.getWriterNick());
			System.out.println("조회수 : " + vo.getInquiry());
			System.out.println("유저 작성일자 : " + vo.getPostTime());
			System.out.println("관리자 작성일자 : " + vo.getRePostTime());
			System.out.println("------------------------------");
			
		} catch (Exception e) {
			System.out.println("답변내용 상세 조회 실패...");
			e.printStackTrace();
		}
}	
	
	public void searchReWriteByTitle() {
		try {
			System.out.println("---------------게시글_제목 검색(관리자)--------------");
			
			// 로그인 여부 확인
			if (Main.loginAdmin == null) {
				throw new Exception("로그인 하고 오세요! 관리자만 가능합니다.");
			}
			//데이터
			System.out.println("검색할 답변 제목 : ");
			String searchValue = Main.SC.nextLine();
			//사비스 호출
			List<QnaVo>voList = service.searchReWriteByTitle(searchValue);
			//결과 처리
			if(voList.size() == 0) {
				System.out.println("검색 결과가 없습니다.");
			}
			System.out.print("QNA_ADMIN 번호");
			System.out.print("/");
			System.out.print("답변 제목");
			System.out.print("/");
			System.out.print("답변 내용");
			System.out.print("/");
			System.out.print("조회수");
			System.out.print("/");
			System.out.print("관리자 작성일자");
			System.out.println();

			for (QnaVo vo : voList) {
				System.out.print(vo.getQnaAdminNo());
				System.out.print("/");
				System.out.print(vo.getReTitle());
				System.out.print("/");
				System.out.print(vo.getReContent());
				System.out.print("/");
				System.out.print(vo.getInquiry());
				System.out.print("/");
				System.out.print(vo.getRePostTime());

				System.out.println();
			}
			}catch(Exception e) {
				System.out.println("게시글 제목 검색 실패..");
				e.printStackTrace();
	}
	}
	
	public void writeDelete() {
		try {
			if(Main.loginUser == null) {
				throw new Exception("유저 로그인 안해서 에러 발생");
			}
			//데이터
			System.out.println("--------게시글 삭제--------");
			
			System.out.println("QNA 번호 : ");
			String num = Main.SC.nextLine();
			//서비스
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("QNA_NO", num);
			int result = service.writeDelete(map);
			//결과
			if(result != 1) {
				throw new Exception();
			}
			System.out.println("게시글 삭제 완료..!!!");
		}catch(Exception e) {
			System.out.println("게시글 삭제 실패...");
			e.printStackTrace();
		}
	}

	public void reWriteDelete() {
		try {
			if(Main.loginAdmin == null) {
				throw new Exception("관리자 로그인 안해서 에러 발생");
			}
			//데이터
			System.out.println("QNA_ADMIN 번호 : ");
			String num = Main.SC.nextLine();
			
			//서비스
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("QNA_ADMIN_NO", num);
			int result = service.reWriteDelete(map);
			
			//결과
			if(result != 1) {
				throw new Exception();
			}
			System.out.println("게시글 삭제 완료..!!!");
		}catch(Exception e) {
			System.out.println("게시글 삭제 실패...");
			e.printStackTrace();
		}
	}

}
