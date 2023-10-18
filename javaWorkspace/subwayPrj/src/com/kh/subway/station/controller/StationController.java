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
	
	// 역/노선 전체 조회 ( 역 개수 / 모든역 print)
	public void stationInfoView() {
		try {
			
			System.out.println("== 역/노선 전체 조회 ==");
			
			// 서비스
			List<StationVo> voList = stationService.stationInfoView();
			
			// 결과
			for(int i = 1; i < 10; i++) {
				int count = 0;
				
				System.out.println("<< " + i + "호선 >>");
				for(StationVo vo : voList) {
					if(vo.getLineNo().equals( i + "호선")) {
						System.out.print("[ " + vo.getStationName() + " ] --");
						count++;
					}
				}
				System.out.println("\n총 " + count + "개의 역이 " + i + "호선에 속해 있습니다.");
				System.out.println();
				
			}
			
		} catch(Exception e) {
			System.out.println("역/노선 정보를 불러오지 못했습니다. 다시 시도해주세요.");
			e.printStackTrace();
		}
	}
	
	// 노선 추가
	public void subwayLineAdd() {
		try {
			
			// 어드민 체크 여부
			
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
	
	// 역 추가
	public void subwayStationAdd() {
		try {

			System.out.println("== 신규 역 추가 ==");
			
			// 어드민 체크 여부
			
			// 데이터
			System.out.print("지하철역 번호*필수 : ");
			String stationNo = Main.SC.nextLine();
			System.out.print("역 이름*필수 : ");
			String stationName = Main.SC.nextLine();
			System.out.print("노선번호*필수 : ");
			String lineNo = Main.SC.nextLine();
			System.out.print("환승여부(Y/N)*필수 : ");
			String transferYn = Main.SC.nextLine();
			System.out.print("출구 개수*필수 : ");
			String exitCount = Main.SC.nextLine();
			System.out.print("평일 첫차시간(ex:0520) : ");
			String weekStartTime = Main.SC.nextLine();
			System.out.print("평일 막차시간(ex:0520) : ");
			String weekEndTime = Main.SC.nextLine();
			System.out.print("주말 첫차시간(ex:0520) : ");
			String holStartTime = Main.SC.nextLine();
			System.out.print("주말 막차시간(ex:0520) : ");
			String holEndTime = Main.SC.nextLine();
			System.out.print("화장실 내외위치(O/I) : ");
			String tolietIo = Main.SC.nextLine();
			
			StationVo vo = new StationVo();
			vo.setStationNo(stationNo);
			vo.setStationName(stationName);
			vo.setLineNo(lineNo);
			vo.setTransferYn(transferYn);
			vo.setExitCount(exitCount);
			vo.setWeekStartTime(weekStartTime);
			vo.setWeekEndTime(weekEndTime);
			vo.setHolStartTime(holStartTime);
			vo.setHolEndTime(holEndTime);
			vo.setToiletIo(tolietIo);
			
			// 서비스
			int result = stationService.subwayStationAdd(vo);
			
			// 결과
			if(result != 1) {
				throw new Exception();
			}
			System.out.println("신설 역이 추가되었습니다.");
			
		} catch(Exception e) {
			System.out.println("신설 역을 추가하는 중 오류가 발생했습니다. 다시 시도해주세요.");
			e.printStackTrace();
		}
	}
	
	// 역 삭제
	public void subwayStationDelete() {
		try {
			
			// 데이터
			System.out.print("삭제할 호선번호 : ");
			String lineNo = Main.SC.nextLine();
			System.out.print("삭제할 역 이름(ex:강남) : ");
			String stationName = Main.SC.nextLine();
			
			StationVo vo = new StationVo();
			vo.setLineNo(lineNo);
			vo.setStationName(stationName);
			
			// 서비스
			int result = stationService.subwayStationDelete(vo);
			
			// 결과
			if(result != 1) {
				throw new Exception();
			}
			System.out.println(stationName + "역 정보가 삭제되었습니다.");
			
		} catch(Exception e) {
			System.err.println("역 정보 삭제에 실패했습니다.");
			e.printStackTrace();
		}
	}
	
} // class
