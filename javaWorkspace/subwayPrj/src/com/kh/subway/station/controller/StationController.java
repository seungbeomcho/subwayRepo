package com.kh.subway.station.controller;

import java.util.List;

import com.kh.subway.main.Main;
import com.kh.subway.station.service.StationService;
import com.kh.subway.station.vo.StationVo;

public class StationController {
	//필드
	private final StationService stationService;
	
	public StationController() {
		stationService = new StationService();
	}

	boolean menu = true;
	//메뉴 선택
	public void selectMenu() {
		
		while(menu) {
			//메뉴판
			System.out.println("원하시는 메뉴를 입력해주세요.");
			System.out.println("┌─────────────────────────────────────────────────────────┐");
			System.out.println("| ※ 1 ~ 8 호선에 존재하는 역만 조회가 가능합니다 ※");
			System.out.println("| 1. 역 정보 검색");
			System.out.println("|  - 노선명, 환승 여부, 환승 가능 노선, 출구 개수, 화장실 개찰구 내외 안내");
			System.out.println("| 2. 시간표 조회");
			System.out.println("|  - 평일 첫차 - 막차시간, 주말 첫차 - 막차 시간");
			System.out.println("| 9. 뒤로가기");
			System.out.println("└─────────────────────────────────────────────────────────┘");
			System.out.print("번호를 입력해주세요 : ");
			
			//메뉴선택
			String num = Main.SC.nextLine();
			
			switch (num) {
			case "1" : searchStationInfo(); break;
			case "2" : searchTimetable(); break;
			case "9" : menu = false;
			}
		}
	}//selectMenu
	
	//어드민메뉴
	public void adminMenu() {
		System.out.println("스테이션 관리자 호출");
	}//adminMenu
	
	//역정보검색
	public void searchStationInfo() {
		try {
			//- 데이터
			System.out.println();			
			System.out.println("== 역 정보 검색 ==");
			System.out.println("검색할 역의 이름을 입력해주세요.");
			System.out.println("( 역삼역 X , 역삼 O )");
			System.out.print("역의 이름 : ");
			
			String station = Main.SC.nextLine();
			
			//- 서비스 호출
			List<StationVo> voList = stationService.searchStationInfo(station);
			StationVo stationInfo= voList.get(0);
			
			//- 결과처리
			System.out.println();
			System.out.println();	
			System.out.println("┌──────────────────────────────────────────┐");
			System.out.println(" 역명 : " + stationInfo.getStationName());
			System.out.println(" 노선명 : " + stationInfo.getLineNo());
			System.out.println(" 환승 여부 : " + stationInfo.getTransferYn());
			if(stationInfo.getTransferYn().equals("Y")) {
			System.out.print(" 환승가능노선 : ");
				for(StationVo vo : voList) {
					System.out.print(vo.getLineNo() + "호선|");
				}
			}
			System.out.println(" 출구 개수 : " + stationInfo.getExitCount());
			System.out.print(" 화장실 개찰구 내/외 안내 : ");
			switch (stationInfo.getToiletIo()) {
			case "I": System.out.print("개찰구 내 위치합니다."); break;
			case "O": System.out.print("개찰구 밖 위치합니다."); break;
			case "A": System.out.print("개찰구 내외 모두 위치합니다."); break;
			}
			System.out.println();
			System.out.println("└──────────────────────────────────────────┘");
			System.out.println();
			
		}catch (Exception e) {
			System.out.println("역정보를 불러오지 못했습니다. 다시 시도해주세요.");
			e.printStackTrace();
		}
	}//searchStationInfo
	
	//시간표 조회
	public void searchTimetable() {
		try {
			//데이터
			System.out.println("== 시간표 조회 ==");
			System.out.println("조회할 역이름과 호선을 입력해주세요.");
			System.out.println("!역이름과 호선의 숫자만 입력해주세요!");
			System.out.println("EX) 역삼역 X , 역삼 O \n");
			System.out.print("역이름 : ");
			
			String station = Main.SC.nextLine();
			
			System.out.println();
			System.out.print("검색 가능호선 : | ");
			List<StationVo> voList = stationService.searchStationInfo(station);
			for(StationVo vo : voList) {
				System.out.print(vo.getLineNo() + "호선 | ");
			}
			System.out.println("\n! 숫자만 입력해주세요 !");
			System.out.println();
			System.out.print("호선 : ");
			
			String num = Main.SC.nextLine();
			
			StationVo timeVo = new StationVo();
			timeVo.setStationName(station);
			timeVo.setLineNo(num);
						
			//서비스
			StationVo rsVo = stationService.searchTimetable(timeVo);
			
			//결괴처리
			if(rsVo == null) {
				throw new Exception();
			}
			
			System.out.println("────────────────────");
			System.out.println("평일 첫차시간 : " + rsVo.getWeekStartTime().substring(0,2) + "시 " + rsVo.getWeekStartTime().substring(2,4) + "분");
			System.out.println("평일 막차시간 : " + rsVo.getWeekEndTime().substring(0,2) + "시 " + rsVo.getWeekEndTime().substring(2,4) + "분");
			System.out.println("주말 첫차시간 : " + rsVo.getHolStartTime().substring(0,2) + "시 " + rsVo.getHolStartTime().substring(2,4) + "분");
			System.out.println("주말 막차시간 : " + rsVo.getHolEndTime().substring(0,2) + "시 " + rsVo.getHolEndTime().substring(2,4) + "분");
			System.out.println("────────────────────");
				
		} catch (Exception e) {
			System.out.println("시간표 조회 실패");
			e.printStackTrace();
		}
		
	}//searchTimetable
	
//	public void minTransferSearch() {
//		try {
//			System.out.println("==== 최소환승 경로 안내 ====");
//			//데이터
//			System.out.print("출발지를 입력하세요 : ");
//			String startStation = Main.SC.nextLine();
//			System.out.print("목적지를 입력하세요 : ");
//			String endStation = Main.SC.nextLine();
//			
//			
//			//서비스호출
//			
//			//결과처리
//			
//		} catch (Exception e) {
//			System.out.println("경로 탐색에 실패했습니다.");
//			e.printStackTrace();
//		}
//	}
//	
//	
//	
	
	
	
	
	
	
}
