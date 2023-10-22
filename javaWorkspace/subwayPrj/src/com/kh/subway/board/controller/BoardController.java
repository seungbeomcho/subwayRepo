package com.kh.subway.board.controller;

import java.util.List;

import com.kh.subway.admin.controller.AdminController;
import com.kh.subway.board.service.BoardService;
import com.kh.subway.board.vo.BoardVo;
import com.kh.subway.main.Main;
import com.kh.subway.user.controller.UserController;

public class BoardController {
		
		private final BoardService service;
		private final UserController uc;
		private final AdminController ac;
		
		public BoardController() {
			service = new BoardService();
			ac = new AdminController();
			uc = new UserController();
		}
		//메뉴 선택
		public void selectMenu() {
			System.out.println("===== 자유게시판 =====");
			System.out.println("0. 로그아웃");
			System.out.println("1. 로그인");
			System.out.println("2. 글작성");
			System.out.println("3. 전체 목록 조회(최신순)");
			System.out.println("4. 상세 목록 조회 (게시판넘버)");
			System.out.println("5. 본인 게시판 조회 및 수정");
			System.out.println("6. 게시글 검색(제목+내용)");
			System.out.println("7. 게시글 검색(역이름)");
			System.out.println("8. 게시글 검색(닉네임)");
			System.out.println("9. 돌아가기");
			
			
			String num = Main.SC.nextLine();
			switch(num) {
			case "0" : uc.logout(); break; 
			case "1" : uc.login(); break; 
			case "2" : write(); break; 
			case "3" : boardList(); break; 
			case "4" : boardDetailByNo(); break; 
			case "5" : userBoardSelect(); break; 
			case "6" : searchBoardByTitleContent(); break; 
			case "7" : searchBoardByStationName(); break; 
			case "8" : searchBoardByNick(); break; 
			case "9" : return; 
			default : System.out.println("잘못 입력하셨습니다.");
			}
		}
		
		//관리자 메뉴
		public void adminMenu() {
			System.out.println("===== 자유게시판 관리자 메뉴 =====");
			System.out.println("1. 게시판 수정");
			System.out.println("2. 게시판 삭제");
			System.out.println("9. 로그아웃");
			
			String num = Main.SC.nextLine();
			switch(num) {
			case "1" : adminBoardDetailByNo(); break;
			case "2" : delete(); break;
			case "9" : ac.adminLogout(); break;
			default : System.out.println("잘못 입력했습니다.");
			}
			
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
				
				List<BoardVo> voList = service.userBoardSelect(Main.loginUser.getUserNo());
				
				for(BoardVo vo : voList) {
					System.out.println("NO :" + vo.getBoardNo() + " ");
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
				System.out.println("3. 역이름 수정");
				System.out.println("4. 게시글 삭제");
				System.out.println("9. 이전으로 돌아가기");
				String num = Main.SC.nextLine();
				switch(num) {
				case "1" : titleModify(); break;
				case "2" : contentModify(); break;
				case "3" : stationNameModify(); break;
				case "4" : delete(); break;
				case "9" : return;
				default : System.out.println("잘못 입력하셨습니다.");
				}
				
				
			}catch(Exception e) {
				System.out.println("게시판 조회 및 수정 실패");
				e.printStackTrace();
			}
			
			
		}
		

		
		// 게시판 상세 조회(게시판 no로 조회)
		public void boardDetailByNo() {
			try {
				System.out.println("----- 게시판 상세 조회 -----");
				System.out.println("게시판 넘버 : ");
				String no = Main.SC.nextLine();
				
				BoardVo voList = service.boardDetailByNo(no);
				
				if(voList == null) {
					throw new Exception("해당 게시글이 없습니다");
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
		
		//게시판 조회 및 수정(게시판 no로 상세 조회 하고 수정)
		public void adminBoardDetailByNo() {
			try {
				System.out.println("----- 게시판 상세 조회 -----");
				System.out.println("게시판 번호 : ");
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
				
				System.out.println();
				
				
				System.out.println("자유게시판 수정");
				System.out.println("1. 제목 수정");
				System.out.println("2. 내용 수정");
				System.out.println("3. 역이름 수정");
				System.out.println("9. 이전으로 돌아가기");
				String num = Main.SC.nextLine();
				switch(num) {
				case "1" : titleModify(); break;
				case "2" : contentModify(); break;
				case "3" : stationNameModify(); break;
				case "9" : return;
				default : System.out.println("잘못 입력하셨습니다.");
				}
				
			}catch(Exception e) {
				System.out.println("상세 조회 실패 ...");
				e.printStackTrace();
			}
			
			
			
		}
		
		// 게시글 제목 수정
		public void titleModify() {
			try {
				BoardVo vo = new BoardVo();
				if(Main.loginUser != null) {
					System.out.print("변경할 내용 : ");
					String title = Main.SC.nextLine();
					vo.setTitle(title);
					vo.setBoardNo(Main.loginUser.getUserNo());
					
				}else {
					System.out.println("변경할 게시물 NO : ");
					String no = Main.SC.nextLine();
					System.out.print("변경할 내용 : ");
					String title = Main.SC.nextLine();
					vo.setTitle(title);
					vo.setBoardNo(no);
				}
				
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
				BoardVo vo = new BoardVo();
				if(Main.loginUser != null) {
					System.out.print("변경할 내용 : ");
					String content = Main.SC.nextLine();
					vo.setContent(content);
					vo.setBoardNo(Main.loginUser.getUserNo());
				}else {
					System.out.println("변경할 게시물 NO : ");
					String no = Main.SC.nextLine();
					System.out.print("변경할 내용 : ");
					String content = Main.SC.nextLine();
					vo.setContent(content);
					vo.setBoardNo(no);
				}
				
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
		
		// 게시글 역이름 수정
		public void stationNameModify() {
			try {
				BoardVo vo = new BoardVo();
				if(Main.loginUser != null) {
					System.out.print("변경할 내용 : ");
					String stationName = Main.SC.nextLine();
					vo.setStationName(stationName);
					vo.setBoardNo(Main.loginUser.getUserNo());
				}else {
					System.out.println("변경할 게시물 NO : ");
					String no = Main.SC.nextLine();
					System.out.print("변경할 내용 : ");
					String stationName = Main.SC.nextLine();
					vo.setStationName(stationName);
					vo.setBoardNo(no);
				}
				
				int result = service.stationNameModify(vo);
				
				if(result != 1) {
					throw new Exception();
				}
				System.out.println("내용 수정 성공 !");
				
				
			}catch(Exception e){
				System.out.println("내용 수정 실패 ...");
				e.printStackTrace();
			}
		}
		
		//게시판 검색(제목+내용)
		public void searchBoardByTitleContent() {
			try {
				System.out.println("검색하실 내용 : ");
				String searchValue = Main.SC.nextLine();
				
				List<BoardVo> voList = service.searchBoardByTitleContent(searchValue);
				
				if(voList.size() == 0) {
					throw new Exception("검색하신 내용과 일치하는 게시글이 없습니다.");
				}
				
				
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
				System.out.println("게시판 검색 실패 ...");
				e.printStackTrace();
			}
		}
		
		
		//게시판 검색(역이름)
		public void searchBoardByStationName() {
			try {
				
				System.out.println("검색하실 내용 : ");
				String searchValue = Main.SC.nextLine();
				
				List<BoardVo> voList = service.searchBoardByStationName(searchValue);
				
				
				if(voList.size() == 0) {
					throw new Exception("검색하신 내용과 일치하는 게시글이 없습니다.");
				}
				
				
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
				System.out.println("게시판 검색 실패 ...");
				e.printStackTrace();
			}
		}
		
		
		//게시판 검색(닉네임)
		public void searchBoardByNick() {
			try {
				
				System.out.println("검색하실 내용 : ");
				String searchValue = Main.SC.nextLine();
				
				List<BoardVo> voList = service.searchBoardByNick(searchValue);
				
				if(voList.size() == 0) {
					throw new Exception("검색하신 닉네임으로 작성한 게시글이 없습니다.");
				}
				
				
				
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
				System.out.println("게시판 검색 실패 ...");
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
		
		// 자유게시판 삭제 (로그인한 유저만, 게시판 번호)
		public void deleteUser() {
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
		
}//class









