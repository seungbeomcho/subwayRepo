package com.kh.subway.comment.controller;

import com.kh.subway.comment.service.CommentService;
import com.kh.subway.comment.vo.CommentVo;
import com.kh.subway.main.Main;

public class CommentController {
	
	private CommentService service;
	
	public CommentController() {
		service = new CommentService();
	}
	
	
	public void leaveComment(String boardNo) {
		try {
			CommentVo vo = null;
			if(Main.loginUser != null) {
				System.out.println("댓글 입력 : ");
				String comment = Main.SC.nextLine();
				vo = new CommentVo();
				vo.setContent(comment);
				vo.setUserNo(Main.loginUser.getUserNo());
				vo.setBoardNo(boardNo);
			}else if(Main.loginAdmin != null) {
				System.out.println("댓글 입력 : ");
				String comment = Main.SC.nextLine();
				vo = new CommentVo();
				vo.setBoardNo(boardNo);
				vo.setContent(comment);
				vo.setAdminNo(Main.loginAdmin.getAdminNo());
			}
			
			int result = service.leaveComment(vo);
			
			if(result != 1) {
				throw new Exception();
			}
			System.out.println("댓글 작성 성공 !");
			
		}catch(Exception e) {
			System.out.println("댓글 입력 실패");
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
}
