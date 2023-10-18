package com.kh.subway.board.controller;

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
		// 자유게시판 수정(no 조회해서 일치하는것만 수정)
		public void edit() {
			//로그인 여부 확인 (유저 로그인 정보 필요)
//			String no = 로그인 유저 정보
			
			
		}
		// 자유게시판 목록 조회
		
		// 자유게시판 삭제 (로그인한 유저만,
	
	
}
