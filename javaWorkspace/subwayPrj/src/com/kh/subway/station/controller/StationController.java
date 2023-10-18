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
			/*
			StationVo stationVo = stationService.searchStationInfo(station);
			if(stationVo == null) {
				throw new Exception("역정보를 불러오지 못했습니다. 다시 시도해주세요.");
			}
			*/
			//- 결과처리
			
		}catch (Exception e) {
			System.out.println("역정보를 불러오지 못했습니다. 다시 시도해주세요.");
			e.printStackTrace();
		}
	}
	
	// 역/노선 전체 조회 ( 역 개수 / 모든역 print)
	public void stationInfoView() {
		try {
			
			System.out.println("== 역/노선 전체 조회 ==");
			// 서비스
			List<StationVo> voList = stationService.stationInfoView();
			
			// 결과
			System.out.println("<< 1호선 >>");
			for(StationVo vo : voList) {
				if(vo.getLineNo().equals("1호선")) {
					System.out.print("[ " + vo.getStationName() + " ] --");
				}
			}
			
			System.out.println();System.out.println();
			System.out.println("<< 2호선 >>");
			for(StationVo vo : voList) {
				if(vo.getLineNo().equals("2호선")) {
					System.out.print("[ " + vo.getStationName() + " ] --");
				}
			}
			
			System.out.println();System.out.println();
			System.out.println("<< 3호선 >>");
			for(StationVo vo : voList) {
				if(vo.getLineNo().equals("3호선")) {
					System.out.print("[ " + vo.getStationName() + " ] --");
				}
			}
			
			System.out.println();System.out.println();
			System.out.println("<< 4호선 >>");
			for(StationVo vo : voList) {
				if(vo.getLineNo().equals("4호선")) {
					System.out.print("[ " + vo.getStationName() + " ] --");
				}
			}
			
			System.out.println();System.out.println();
			System.out.println("<< 5호선 >>");
			for(StationVo vo : voList) {
				if(vo.getLineNo().equals("5호선")) {
					System.out.print("[ " + vo.getStationName() + " ] --");
				}
			}
			
			System.out.println();System.out.println();
			System.out.println("<< 6호선 >>");
			for(StationVo vo : voList) {
				if(vo.getLineNo().equals("6호선")) {
					System.out.print("[ " + vo.getStationName() + " ] --");
				}
			}
			
			System.out.println();System.out.println();
			System.out.println("<< 7호선 >>");
			for(StationVo vo : voList) {
				if(vo.getLineNo().equals("7호선")) {
					System.out.print("[ " + vo.getStationName() + " ] --");
				}
			}
			
			System.out.println();System.out.println();
			System.out.println("<< 8호선 >>");
			for(StationVo vo : voList) {
				if(vo.getLineNo().equals("8호선")) {
					System.out.print("[ " + vo.getStationName() + " ] --");
				}
			}
			
			System.out.println();System.out.println();
			System.out.println("<< 9호선 >>");
			for(StationVo vo : voList) {
				if(vo.getLineNo().equals("9호선")) {
					System.out.print("[ " + vo.getStationName() + " ] --");
				}
			}
			
		} catch(Exception e) {
			System.out.println("역/노선 정보를 불러오지 못했습니다. 다시 시도해주세요.");
			e.printStackTrace();
		}
	}
	
	// 노선 추가
	public void subwayLineAdd() {
		try {
			
			// 데이터
			
			// 서비스
			
			// 결과
			
		} catch(Exception e) {
			
		}
	}
	
	// 역 추가
	public void subwayStationAdd() {
		try {
			
			// 데이터
			
			// 서비스
			
			// 결과
			
		} catch(Exception e) {
			
		}
	}
	
	// 노선 삭제
	public void subwayLineDelete() {
		try {
			
			// 데이터
			
			// 서비스
			
			// 결과
			
		} catch(Exception e) {
			
		}
	}
	
	// 역 삭제
	public void subwayStationDelete() {
		try {
			
			// 데이터
			
			// 서비스
			
			// 결과
			
		} catch(Exception e) {
			
		}
	}
	
} // class
