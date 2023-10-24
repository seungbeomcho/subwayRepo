package com.kh.subway.comment.controller;

import com.kh.subway.comment.service.CommentService;
import com.kh.subway.comment.vo.CommentVo;
import com.kh.subway.main.Main;

public class CommentController {
	
	private CommentService service;
	
	public CommentController() {
		service = new CommentService();
	}
	
	//댓글작성
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
		
		//댓글 수정
		public void editComment() {
			try {
				if(Main.loginUser == null && Main.loginAdmin == null) {
					throw new Exception("로그인해야 가능합니다.");
				}
				int result = 0;
				CommentVo vo = new CommentVo();
				if(Main.loginAdmin != null) {
					System.out.print("수정할 댓글 번호 : ");
					String commentNo = Main.SC.nextLine();
					System.out.print("수정할 내용 : ");
					String commentEdit = Main.SC.nextLine();
					vo.setComment(commentEdit);
					vo.setUserNo(Main.loginAdmin.getAdminNo());
					vo.setCommentNo(commentNo);
					result = service.editComment(vo);
					
				}else if(Main.loginUser != null) {
					System.out.print("수정할 댓글 번호 : ");
					String commentNo = Main.SC.nextLine();
					System.out.print("수정할 내용 : ");
					String commentEdit = Main.SC.nextLine();
					vo.setComment(commentEdit);
					vo.setUserNo(Main.loginUser.getUserNo());
					vo.setCommentNo(commentNo);
					result = service.editComment(vo);
				}

				
				if(result != 1) {
					throw new Exception();
				}
				
				System.out.println("댓글 수정 성공");
				
			}catch(Exception e) {
				System.out.println("댓글 수정 실패");
				e.printStackTrace();
			}

		
		
	}
	
		//댓글 삭제
		public void delete() {
			try {
				int result = 0;
				CommentVo vo = new CommentVo();
				if(Main.loginUser != null) {
					System.out.print("삭제할 댓글 번호 : ");
					String num = Main.SC.nextLine();
					vo.setCommentNo(num);
					vo.setUserNo(Main.loginUser.getUserNo());
					result = service.delete(vo);
				}else if(Main.loginAdmin != null) {
					System.out.print("삭제할 댓글 번호 : ");
					String num = Main.SC.nextLine();
					vo.setCommentNo(num);
					result = service.delete(vo);
				}

				
				if(result != 1) {
					throw new Exception();
				}
				
				System.out.println("댓글 삭제 성공");
				
			}catch(Exception e) {
				System.out.println("댓글 삭제 실패");
				e.printStackTrace();
			}

			
			
		
		}
	
}
