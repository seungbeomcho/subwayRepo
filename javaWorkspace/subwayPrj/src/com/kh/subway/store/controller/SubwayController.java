package com.kh.subway.store.controller;

import java.util.HashMap;
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
	
	//관리자 메뉴
	public void adminMenu() {
		while(true) {
			System.out.println("1. 매장 이름 수정 (관리자만)");
			System.out.println("2. 매장 주소 수정 (관리자만)");
			System.out.println("3. 폐업 매장 삭제 (관리자만)");
			System.out.println("4. 신규 매장 추가 (관리자만)");
			System.out.println("5. 매장 변동사항 상세 조회(관리자만)");
			System.out.println("6. 메인으로 돌아가기");
			
			
			String num = Main.SC.nextLine();
			switch(num) {
			case "1" : changeStoreName(); break;
			case "2" : changeStoreAddress(); break;
			case "3" : closeStore(); break;
			case "4" : newStore(); break;
			case "5" : subwayDetailList(); break;
			case "6" : return;
		    default : System.out.println("잘못 입력하셨습니다.");
			}
		}
		
	}
	//메뉴선택
	public void selectMenu() {
		while(true) {
			System.out.println("=====SUBWAY STORE=====");
			System.out.println("1. 역 주위 서브웨이 매장 전체 조회");
			System.out.println("2. 역 주위 서브웨이 매장 조회 (역 이름)");
			System.out.println("3. 뒤로가기");
			
			
			String num = Main.SC.nextLine();
			switch(num) {
			case "1" : subwayList(); break;
			case "2" : subwayListByName(); break;
			case "3" : return;
		
			default : System.out.println("잘못 입력하셨습니다."); 
			}
		}
		
	}
	
	//서브웨이 매장 전체 조회
	public void subwayList() {
		try {
			System.out.println("================서브웨이 매장 전체 조회================");
			
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
				System.out.println();
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
	
	//서브웨이 매장 상세 조회(매장명, 개업여부, 폐업여부, 개업일시, 폐업일시, 수정일시)
	public void subwayDetailList() {
		try {
			System.out.println("===== 매장 변경사항 상세 조회 =====");
			
			//서비스 호출
			List<SubwayVo> voList = service.subwayDetailList();
			
			//결과처리
			System.out.print("매장명");
			System.out.print(" | ");
			System.out.print("주소");
			System.out.print(" | ");
			System.out.print("신규여부");
			System.out.print(" | ");
			System.out.print("폐업여부");
			System.out.print(" | ");
			System.out.print("개업일시");
			System.out.print(" | ");
			System.out.print("폐업일시");
			System.out.print(" | ");
			System.out.print("수정일시");
			for(SubwayVo vo : voList) {
				System.out.println();
				System.out.print(vo.getStoreName());
				System.out.print(" | " );
				System.out.print(vo.getAddress());
				System.out.print(" | " );
				System.out.print(vo.getNewYn());
				System.out.print(" | " );
				System.out.print(vo.getDelYn());
				System.out.print(" | " );
				System.out.print(vo.getOpenDate());
				System.out.print(" | " );
				System.out.print(vo.getCloseDate());
				System.out.print(" | " );
				System.out.print(vo.getModifydate());
				System.out.println();
			}
			
		}catch(Exception e) {
			System.out.println("매장 변경사항 상세조회 실패 ...");
			e.printStackTrace();
		}
		
	}
	//서브웨이 매장 상세 조회 (역 이름)
	public void subwayListByName() {
		
		try {
			System.out.println("===== 역 주위 매장 조회 (역 이름) =====");
			
			//데이터
			System.out.println("조회할 매장 주위 역 이름(ex : 강남역 X -> 강남O) : ");
			
			String name = Main.SC.nextLine();
			
			//서비스 호출
			List<SubwayVo> voList = service.subwayListByName(name);
			
			//결과 처리
			if(voList.size() == 0) {
				throw new Exception("잘못 입력하셨습니다.");
			}	
			for(SubwayVo vo : voList) {
				//System.out.println("일련번호 : " + vo.getStoreNo());
				System.out.println("전화번호 : " + vo.getTel());
				System.out.println("매장명 : " + vo.getStoreName());
				System.out.println("주소 : " + vo.getAddress());
				System.out.println();
			}
		}catch(Exception e) {
			System.out.println("역 주위 매장 상세조회 실패 ...");
			e.printStackTrace(); 
		}
	}
	

	//관리자 계정으로 매장 이름 수정
	public void changeStoreName() {
		
		try {		
			System.out.println("----- 매장 이름 수정 -----");
			
			//데이터
			System.out.println("정보 변동사항이 있는 매장 일련번호를 입력하세요 : ");
			String storeNo = Main.SC.nextLine();
			SubwayVo subwayVo = service.printStoreInfo(storeNo);
			System.out.println("현재 매장이름 : " + subwayVo.getStoreName());
			System.out.println("현재 매장주소 : " + subwayVo.getAddress());
			System.out.println("전화번호 : " + subwayVo.getTel());
			
			System.out.println("변경할 매장 이름을 입력하세요 : ");
			String storeName = Main.SC.nextLine();
				
			SubwayVo vo = new SubwayVo();	
			vo.setStoreNo(storeNo);
			vo.setStoreName(storeName);
				
			//서비스 호출
			int result = service.changeStoreName(vo);
			
			//결과처리
			if(result == 1) {
				System.out.println("매장 이름 변경 성공");
			}else {
				throw new Exception();
			}
		}catch(Exception e) {
			System.out.println("매장 이름 변경 실패");
			e.printStackTrace();
		}		
	}
	
	//관리자 계정으로 매장 주소 수정
	public void changeStoreAddress() {
		
		try {
			System.out.println("----- 매장 주소 수정 -----");
			
			//데이터
			System.out.println("주소를 변경할 매장 일련번호를 입력하세요 : ");
			String storeNo = Main.SC.nextLine();
			SubwayVo subwayVo = service.printStoreInfo(storeNo);
			System.out.println("현재 매장이름 : " + subwayVo.getStoreName());
			System.out.println("현재 매장주소 : " + subwayVo.getAddress());
			System.out.println("전화번호 : " + subwayVo.getTel());
			
			System.out.println("변경할 주소지를 입력하세요 : ");
			String address = Main.SC.nextLine();
			
			SubwayVo vo = new SubwayVo();
			vo.setStoreNo(storeNo);
			vo.setAddress(address);
			
			//서비스 호출
			int result = service.changeStoreAddress(vo);
			
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
	/**
	 * 폐업 매장 삭제(관리자만)
	 * 
	 * UPDATE SUBWAY
	 * 	SET 
	 * 		DEL_YN = 'Y'
	 * 		,CLOSE_DATE = SYSDATE
	 * 		,MODIFY_DATE = SYSDATE
	 * WHERE STORE_NO = ?
	 */
	
	//폐업 매장 등록(삭제)
	public void closeStore() {
		
		try {
			System.out.println("=====폐업한 매장 정보 삭제=====");
			
			//관리자 여부 체크
			
			//데이터
			System.out.println("폐업한 매장 일련 번호를 입력하세요. : ");
			String num = Main.SC.nextLine();
			
			//서비스 호출
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("storeNo", num);
			int result = service.closeStore(map);
			
			//결과 처리
			if(result != 1) {
				throw new Exception();
			}
			System.out.println("폐업 매장 등록(삭제) 완료");
		}catch(Exception e) {
			System.out.println("폐업 매장 등록(삭제) 실패");
			e.printStackTrace();
		}
		
	}
	
	//신규 매장 추가
	public void newStore() {
		
		try {
			System.out.println("===== 신규 매장 추가 =====");

			//데이터
			System.out.println("신규 매장 근처 역 이름를 입력하세요 : ");
			String stationName = Main.SC.nextLine();
			System.out.println("신규 매장 일련 번호를 입력하세요 : ");
			String storeNo = Main.SC.nextLine();
			System.out.println("신규 매장 이름을 입력하세요 : ");
			String storeName = Main.SC.nextLine();
			System.out.println("신규 매장 전화번호를 입력하세요 : ");
			String tel = Main.SC.nextLine();
			System.out.println("신규 매장 주소를 입력하세요 : ");
			String address = Main.SC.nextLine();
			
			SubwayVo vo = new SubwayVo();
			vo.setStationName(stationName);
			vo.setStoreNo(storeNo);
			vo.setStoreName(storeName);
			vo.setTel(tel);
			vo.setAddress(address);
			
			//서비스 호출
			int result = service.newStore(vo);
			
			//결과 처리
			if(result != 1) {
				throw new Exception();
			}
			System.out.println("신규 매장 추가 성공");
		}catch(Exception e) {
			System.out.println("매장 추가 실패 ...");
			e.printStackTrace();
		}
	}
	
}
