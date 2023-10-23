package com.kh.subway.comment.controller;

import com.kh.subway.comment.service.CommentService;
import com.kh.subway.main.Main;

public class CommentController {
	
	private CommentService service;
	
	public CommentController() {
		service = new CommentService();
	}
	
	
	public void leaveComment() {
		try {
			System.out.println("댓글 입력 : ");
			String comment = Main.SC.nextLine();
			
			int result = service.leaveComment(comment);
			
			if(result != 1) {
				throw new Exception();
			}
			
			
		}catch(Exception e) {
			System.out.println("댓글 입력 실패");
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
}
