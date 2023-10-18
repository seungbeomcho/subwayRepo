package com.kh.subway.station.controller;

import com.kh.subway.main.Main;
import com.kh.subway.station.service.StationService;
import com.kh.subway.station.vo.StationVo;

public class StationController {
	//필드
	private final StationService stationService;
	
	public StationController() {
		stationService = new StationService();
	}

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
			StationVo stationVo = stationService.searchStationInfo(station);
			if(stationVo == null) {
				throw new Exception("역정보를 불러오지 못했습니다. 다시 시도해주세요.");
			}
			//- 결과처리
			
		}catch (Exception e) {
			System.out.println("역정보를 불러오지 못했습니다. 다시 시도해주세요.");
			e.printStackTrace();
		}
	}
}
