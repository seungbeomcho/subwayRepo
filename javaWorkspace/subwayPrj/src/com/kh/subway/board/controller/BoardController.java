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
			
		}
		
		public void adminMenu() {
			
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
					
					System.out.printf(vo.getBoardNo());
					System.out.printf("%50s" ,"제목 : " + vo.getTitle());
					System.out.printf(vo.getContent());
					System.out.printf(vo.getStationName());
					System.out.printf(vo.getEnrollDate());
					System.out.printf(vo.getInquiry());
					System.out.printf(vo.getWriterNick());
					System.out.println();
				}
				
				
			}catch(Exception e) {
				System.out.println("목록 조회 실패");
				e.printStackTrace();
			}
		}
		
//		// 자유게시판 수정( no 로그인한 유저가 작성한 것에 해당하는것)
//		public void editBoardUser() {
//			try{
//				if(Main.loginUser == null) {
//					throw new Exception("로그인 해주세요.");
//				}
//				System.out.println("자유게시판 수정");
//				String no = Main.loginUser.getUserNo();
//				System.out.print("제목 : ");
//				String title = Main.SC.nextLine();
//				System.out.print("내용 : ");
//				String content = Main.SC.nextLine();
//				
//				BoardVo vo = new BoardVo();
//				vo.setUserNo(no);
//				vo.setTitle(title);
//				vo.setContent(content);
//				
//				int result = service.editBoardUser(vo);
//				
//				if(result != 1) {
//					throw new Exception();
//				}
//				System.out.println("게시글이 수정 되었습니다.");
//				System.out.print("제목 : " + vo.getTitle());
//				System.out.print("내용 : " + vo.getContent());
//				
//			}catch(Exception e) {
//				System.out.println("게시판 수정 실패");
//				e.printStackTrace();
//			}
			
			
//		}
		
		// 자유게시판 삭제 (로그인한 유저만, 게시판 번호)
		public void delete() {
			try {
				if(Main.loginAdmin == null && Main.loginUser == null) {
					throw new Exception("로그인해야 가능한 기능입니다.");
				}
				
				System.out.print("게시판 번호 : ");
				String no = Main.SC.nextLine();
				
				
				
				
				
				
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
		
		
		
		
		
		
}//class


































