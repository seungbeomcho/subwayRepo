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
		
		
		// 자유게시판 작성
		public void write() {
			try {
				System.out.println("----- 자유게시판 작성 -----");
				
				System.out.print("제목 : ");
				String title = Main.SC.nextLine();
				System.out.print("작성내용 : ");
				String content = Main.SC.nextLine();
				
				BoardVo vo = new BoardVo();
				vo.setTitle(title);
				vo.setContent(content);
				
				int result = service.write(vo);
				
				if(result != 1) {
					throw new Exception();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			
		}
		// 자유게시판 수정(no 조회해서 일치하는것만 수정 , 관리자는 전부 수정 가능)
		public void editManager() {
			try {
				if(Main.loginUser == null && Main.loginAdmin == null) {
					throw new Exception("로그인 하셔야 가능한 기능입니다");
				}else if(Main.loginUser != null) {
					
					
				}else if(Main.loginAdmin != null) {
					
				}
				
				
			}catch(Exception e) {
				
			}
			
			
		}
		// 자유게시판 목록 조회 (작성 시간 순)
		public void boardList() {
			try {
				System.out.println("자유게시판 목록 조회");
				
				List<BoardVo> voList = service.boardList();
				
				for(BoardVo vo : voList) {
					System.out.println(vo);
				}
				
				
			}catch(Exception e) {
				
			}
		}
		
		
		
		// 자유게시판 삭제 (로그인한 유저만,
	
	
}
