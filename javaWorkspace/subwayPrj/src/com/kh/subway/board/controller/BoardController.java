package com.kh.subway.board.controller;

import java.util.List;

import com.kh.subway.board.service.BoardService;
import com.kh.subway.board.vo.BoardVo;
import com.kh.subway.main.Main;

public class BoardController {
		
		private final BoardService service;
		
		public BoardController() {
			service = new BoardService();
		}
		
		public void selectMenu() {
			System.out.println("===== 자유게시판 =====");
			System.out.println("1. 글작성");
			System.out.println("2. 전체 목록 조회(최신순)");
			System.out.println("3. 상세 목록 조회(게시판넘버)");
			
			String num = Main.SC.nextLine();
			switch(num) {
			case "1" : write(); break; 
			case "2" : boardList(); break; 
			case "3" : boardDetailByNo(); break; 
			case "4" : userBoardSelect(); break; 
			default : System.out.println("잘못 입력하셨습니다.");
			}
		}
		
		public void adminMenu() {
			System.out.println("");
		}
		
		// 자유게시판 작성
		public void write() {
			try {
				if(Main.loginUser == null && Main.loginAdmin == null) {
					throw new Exception("로그인하셔야 가능합니다.");
				}
				
				System.out.println("----- 자유게시판 작성 -----");
				
				System.out.println("EX) 강남구청역 X -> 강남구청");
				System.out.print("역이름 : ");
				String stationName = Main.SC.nextLine();
				System.out.print("제목 : ");
				String title = Main.SC.nextLine();
				System.out.print("작성내용 : ");
				String content = Main.SC.nextLine();
				String uesrNo = Main.loginUser.getUserNo();
				
				
				BoardVo vo = new BoardVo();
				vo.setTitle(title);
				vo.setContent(content);
				vo.setStationName(stationName);
				vo.setUserNo(uesrNo);
				
				int result = service.write(vo);
				
				if(result != 1) {
					throw new Exception();
				}
				System.out.println("게시글 작성 완료");
			}catch(Exception e) {
				System.out.println("게시글 작성 실패");
				e.printStackTrace();
			}
			
			
		}
		// 자유게시판 목록 조회 (최신순)
		public void boardList() {
			try {
				System.out.println("자유게시판 목록 조회");
				
				List<BoardVo> voList = service.boardList();
				
				
				for(BoardVo vo : voList) {
					
					System.out.print("NO :" + vo.getBoardNo() + " ");
					System.out.print("제목 : " + vo.getTitle());
					System.out.println();
					System.out.print("내용 : " + vo.getContent());
					System.out.println();
					System.out.print("역이름 : " + vo.getStationName());
					System.out.println();
					System.out.print("작성일시 : " + vo.getEnrollDate());
					System.out.println();
					System.out.print("조회수 : " + vo.getInquiry());
					System.out.println();
					System.out.print("작성자 : " + vo.getWriterNick() + "\n");
					System.out.println("===================================");
				}
				
				
			}catch(Exception e) {
				System.out.println("목록 조회 실패");
				e.printStackTrace();
			}
		}
		
		// 자유게시판 조회 및 수정 (로그인 한 no로 조회 및 수정)
		public void userBoardSelect() {
			try{
				if(Main.loginUser == null) {
					throw new Exception("로그인 해주세요.");
				}
				
				String userNo = Main.loginUser.getUserNo();
				
				
				List<BoardVo> voList = service.userBoardSelect(userNo);
				
				for(BoardVo vo : voList) {
					System.out.print("NO :" + vo.getBoardNo() + " ");
					System.out.print("제목 : " + vo.getTitle());
					System.out.println();
					System.out.print("내용 : " + vo.getContent());
					System.out.println();
					System.out.print("역이름 : " + vo.getStationName());
					System.out.println();
					System.out.print("작성일시 : " + vo.getEnrollDate());
					System.out.println();
					System.out.print("조회수 : " + vo.getInquiry());
					System.out.println();
					System.out.print("작성자 : " + vo.getWriterNick() + "\n");
					System.out.println("===================================");
				}
				
				System.out.println("자유게시판 수정");
				System.out.println("1. 제목 수정");
				System.out.println("2. 내용 수정");
				System.out.println("3. 게시글 삭제");
				System.out.println("9. 이전으로 돌아가기");
				String num = Main.SC.nextLine();
				switch(num) {
				case "1" : titleModify(); break;
				case "2" : contentModify(); break;
				case "3" : delete(); break;
				case "9" : return;
				default : System.out.println("잘못 입력하셨습니다.");
				}
				
				
			}catch(Exception e) {
				System.out.println("게시판 수정 실패");
				e.printStackTrace();
			}
			
			
		}
		
		// 자유게시판 삭제 (로그인한 유저만, 게시판 번호)
		public void delete() {
			try {
				
				System.out.print("게시판 번호 : ");
				String no = Main.SC.nextLine();
				
				int result = service.delete(no);
				
				if(result != 1) {
					throw new Exception();
				}
				
				System.out.println("게시글 삭제 성공");
				
			}catch(Exception e) {
				System.out.println("게시판 삭제 실패");
				e.printStackTrace();
			}
		}
		
		// 상세 조회
		public void boardDetailByNo() {
			try {
				System.out.println("----- 게시판 상세 조회 -----");
				String no = Main.SC.nextLine();
				
				
				BoardVo voList = service.boardDetailByNo(no);
				
				if(voList == null) {
					throw new Exception();
				}
				
				System.out.println("게시판 번호 : " + voList.getBoardNo());
				System.out.println("게시판 제목 : " + voList.getTitle());
				System.out.println("게시판 내용 : " + voList.getContent());
				System.out.println("게시판 작성일시 : " + voList.getEnrollDate());
				System.out.println("게시판 작성자 닉네임 : " + voList.getWriterNick());
				System.out.println("게시판 역이름 : " + voList.getStationName());
				
				
				
			}catch(Exception e) {
				System.out.println("상세 조회 실패 ...");
				e.printStackTrace();
			}
			
			
			
		}
		
		// 게시글 제목 수정
		public void titleModify() {
			try {
				
				System.out.print("변경할 내용 : ");
				String title = Main.SC.nextLine();
				BoardVo vo = new BoardVo();
				vo.setTitle(title);
				vo.setBoardNo(Main.loginUser.getUserNo());
				
				int result = service.titleModify(vo);
				
				if(result != 1) {
					throw new Exception();
				}
				System.out.println("제목 수정 성공 !");
				
				
			}catch(Exception e){
				System.out.println("제목 수정 실패 ...");
				e.printStackTrace();
			}
			
		}
		
		// 게시글 내용 수정
		public void contentModify() {
			try {
				
				System.out.print("변경할 내용 : ");
				String content = Main.SC.nextLine();
				BoardVo vo = new BoardVo();
				vo.setTitle(content);
				vo.setBoardNo(Main.loginUser.getUserNo());
				
				int result = service.contentModify(vo);
				
				if(result != 1) {
					throw new Exception();
				}
				System.out.println("내용 수정 성공 !");
				
				
			}catch(Exception e){
				System.out.println("내용 수정 실패 ...");
				e.printStackTrace();
			}
		}
		
		
		
}//class









