package com.kh.subway.store.controller;

import java.util.List;

import com.kh.subway.main.Main;
import com.kh.subway.store.service.SubwayService;
import com.kh.subway.store.vo.SubwayVo;

	public class SubwayController {

	//필드
	private final SubwayService service;
	
	//기본 생성
	public SubwayController() {
		service = new SubwayService();
	}
	
	//메뉴선택
	public void selectMenu() {
		System.out.println("=====SUBWAY STORE=====");
		System.out.println("1. 역 주위 서브웨이 매장 전체 조회");
		System.out.println("2. 역 주위 서브웨이 매장 조회 (역 이름)");
		System.out.println("3. 매장 정보 수정 (관리자만)");
		System.out.println("4. 폐업 매장 삭제 (관리자만)");
		System.out.println("5. 신규 매장 추가 (관리자만)");
		
		String num = Main.SC.nextLine();
		switch(num) {
		case "1" : subwayList(); break;
		case "2" : subwayListByName(); break;
		case "3" : break;
		default : System.out.println("잘못 입력하셨습니다."); 
		
		}
	}
	
	//서브웨이 매장 전체 조회
	public void subwayList() {
		try {
			System.out.println("=====서브웨이 매장 전체 조회=====");
			
			//데이터
			
			//서비스 호출
			List<SubwayVo> voList = service.subwayList();
			
			//결과처리
			System.out.print("매장번호");
			System.out.print(" | ");
			System.out.print("전화번호");
			System.out.print(" | ");
			System.out.print("매장명");
			System.out.print(" | ");
			System.out.print("매장주소");
			for(SubwayVo vo : voList) {
				System.out.print(vo.getStoreNo());
				System.out.print(" | " );
				System.out.print(vo.getTel());
				System.out.print(" | " );
				System.out.print(vo.getStoreName());
				System.out.print(" | " );
				System.out.print(vo.getAddress());
				
				System.out.println();
			}
			
		}catch(Exception e) {
			System.out.println("서브웨이 매장 전체 조회 실패 ...");
			e.printStackTrace();
		}
	}
	
	//서브웨이 매장 상세 조회 (역 이름)
	public void subwayListByName() {
		
		try {
			System.out.println("역 주위 매장 조회 (역 이름)");
			
			//데이터
			System.out.println("조회할 매장 주위 역 이름 : ");
			String name = Main.SC.nextLine();
			
			//서비스 호출
			SubwayVo vo = service.subwayListByName(name);
			
			//결과 처리
			if(vo == null) {
				throw new Exception("역 주위 매장 상세조회 실패 ...");
			}
			System.out.println("====================================");
			System.out.println("매장일련번호 " + vo.getStoreNo());
			System.out.println("매장전화번호 " + vo.getTel());
			System.out.println("매장명 " + vo.getStoreName());
			System.out.println("매장 주소 " + vo.getAddress());
			System.out.println("====================================");
		}catch(Exception e) {
			System.out.println("역 주위 매장 상세조회 실패 ...");
			e.printStackTrace();
		}
	}

	//관리자 계정으로 매장정보 수정
	public void editStore() {
	
		try {
			//관리자 계정인지 체크
			if(Main.loginAdmin == null) {
				throw new Exception("관리자 로그인이 필요한 서비스입니다.");
			}
			//데이터
			System.out.println("매장 번호 : ");
			String storeNo = Main.SC.nextLine();
			System.out.println("변경사항이 있는 매장명 : ");
			String storeName = Main.SC.nextLine();
			System.out.println("변경할 주소지 : ");
			String address = Main.SC.nextLine();
			
			SubwayVo vo = new SubwayVo();
			vo.setStoreNo(storeNo);
			vo.setStoreName(storeName);
			vo.setAddress(address);
			
			//서비스 호출
			int result = service.editStore(vo);
			
			//결과처리
			if(result == 1) {
				System.out.println("매장 주소지 변경 성공");
			}else {
				throw new Exception();
			}
		}catch(Exception e) {
			System.out.println("매장 주소지 변경 실패");
			e.printStackTrace();
		}
	}
	
}
