package com.kh.subway.station.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
			System.out.println("| ※ 1 ~ 9 호선에 존재하는 역만 조회가 가능합니다 ※");
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
	
	// 관리자 셀렉트 메뉴
	public void adminMenu() {

		System.out.println("== 관리자 메뉴 ==");
		System.out.println("1. 신규 역 추가");
		System.out.println("2. 역 삭제");
		System.out.println("3. 역 정보 수정");
		System.out.println("0. 돌아가기");
		
		String num = Main.SC.nextLine();
		
		switch(num) {
		case "1" : subwayStationAdd(); break;
		case "2" : subwayStationDelete(); break;
		case "3" : subwayStationEdit(); break;
		case "4" : stationInfoView(); break;
		case "0" : return;
		default : System.out.println("다시 시도해주세요.");
		}
		
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
	
	public void minTransferSearch() {
		try {
			System.out.println("==== 최소환승 경로 안내 ====");
			//데이터
			System.out.print("출발지를 입력하세요 : ");
			String startStation = Main.SC.nextLine();
			System.out.print("목적지를 입력하세요 : ");
			String endStation = Main.SC.nextLine();
			
			
			//서비스호출
			//동일호선 체크
			String lineNo = stationService.checkSameLine(startStation, endStation);
			if(lineNo != null) {
				System.out.println("동일 호선 "+ lineNo +"호선 입니다. 환승하지 않습니다.");
				List<String> transitList= stationService.transitStationList(lineNo, startStation, endStation);
								
				for(int i = 0; i<transitList.size();i++) {
					if(i != 0 && i % 5 == 0) {
						System.out.println("\t");
					}
					System.out.print("[" + transitList.get(i) + "]");
					if( i != transitList.size()-1) {						
						System.out.print(" -> ");
					}
				}
				System.out.println();
				System.out.print("========= [도착] =========");
				
			} else if(lineNo == null){
				//==============================환승====================================
				String transferStation = null;
				
				List<String> transferStationList = stationService.searchTransferStation(startStation,endStation);
				
				//환승 가능역 개수에 따라 분류
				if(transferStationList.size() == 1) {			
					transferStation = transferStationList.get(0);
				} else if(transferStationList.size() > 1) {
					System.out.println("환승역 리스트 입니다.");
					int listNum = 1;
					for(String name : transferStationList) {
						System.out.println(listNum + "번 : " + name);
						listNum++;
					}
					System.out.println("원하시는 환승역 번호를 입력해주세요 : ");
					int userNum = Main.SC.nextInt();
					
					transferStation = transferStationList.get(userNum-1);
				}
				
				
				//출발지부터 - 경유역까지
				String startToTranslineNo = stationService.checkSameLine(startStation, transferStation);
				List<String> startToTransList= stationService.transitStationList(startToTranslineNo, startStation, transferStation);
				
				//결과처리
				//출발역부터 환승역까지
				for(int i = 0; i<startToTransList.size();i++) {
					if(i != 0 && i % 5 == 0) {
						System.out.println("\t");
					}
					System.out.print("[" + startToTransList.get(i) + "] -> ");
				}
				
				//환승
				System.out.println("\n");
				System.out.print("========= [환승] =========");
				System.out.println("\n");
				
				
				//경유역부터 도착지까지
				String transToEndlineNo = stationService.checkSameLine(transferStation, endStation);
				List<String> transToEndList= stationService.transitStationList(transToEndlineNo, transferStation, endStation);
				
				for(int i = 0; i<transToEndList.size();i++) {
					if(i != 0 && i % 5 == 0) {
						System.out.println("\t");
					}
					System.out.print("[" + transToEndList.get(i) + "]");
					if( i != transToEndList.size()-1) {						
						System.out.print(" -> ");
					}
				}
				System.out.println();
				System.out.print("========= [도착] =========");
				
			}
			
		} catch (Exception e) {
			System.out.println("경로 탐색에 실패했습니다.");
			e.printStackTrace();
		}
	}
//	병합 지점========================================================================================
	
	// 역/노선 전체 조회 ( 역 개수 / 모든역 print)
	public void stationInfoView() {
		try {
			
			System.out.println("== 역/노선 전체 조회 ==");
			
			// 서비스
			List<StationVo> voList = stationService.stationInfoView();
			// 라인번호 중복제거 저장
			Set<String> uniqueLineNos = new HashSet<String>();

			for (StationVo vo : voList) {
			    uniqueLineNos.add(vo.getLineNo());
			}
			int numberOfLines = uniqueLineNos.size();
			
			// 제일 큰 라인번호 찾기
			int maxLineNo = Integer.MIN_VALUE;
			for (String lineNo : uniqueLineNos) {
			    int lineNoAsInt = Integer.parseInt(lineNo);
			    if (lineNoAsInt > maxLineNo) {
			        maxLineNo = lineNoAsInt;
			    }
			}
			
			// 결과
			List<String> lineList = new ArrayList<String>(); 
			for(int i = 1; i <= maxLineNo; i++) {
				int count = 0;
				
				System.out.println("<< " + i + "호선 >>");
				for(StationVo vo : voList) {
					if(vo.getLineNo().equals(Integer.toString(i))) {
						if(vo.getLineNo().length() != 0) {
							System.out.print("[ " + vo.getStationName() + " ] --");
							count++;
						}
						
					}
				}
				
				if(count != 0) {
					System.out.println("\n총 " + count + "개의 역이 " + i + "호선에 속해 있습니다.");
					System.out.println();
				} else {
					System.out.println("해당라인에 역이 없습니다.");
					System.out.println();
				}
				
				
			}
			
		} catch(Exception e) {
			System.out.println("역/노선 정보를 불러오지 못했습니다. 다시 시도해주세요.");
			e.printStackTrace();
		}
	}//stationInfoView
	
	// 역 추가
	public void subwayStationAdd() {
		try {

			System.out.println("== 신규 역 추가 ==");
			
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
	}//subwayStationAdd
	
	//역 삭제
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
	}//subwayStationDelete
	
	//역수정
	public void subwayStationEdit() {
		try {
			
			// 데이터
			System.out.println();			
			System.out.print("수정할 노선번호 : ");
			String lineNo = Main.SC.nextLine();
			System.out.print("수정할 역 이름 : ");
			String station = Main.SC.nextLine();
						
			StationVo vo = new StationVo();
			vo.setLineNo(lineNo);
			vo.setStationName(station);
			
			System.out.println("== 수정 메뉴 ==");
			
			System.out.println("1. 역 이름 수정");
			System.out.println("2. 노선번호 수정");
			System.out.println("3. 환승여부 수정");
			System.out.println("4. 출구 개수 수정");
			System.out.println("5. 평일 첫차시간 수정");			
			System.out.println("6. 평일 막차시간 수정");			
			System.out.println("7. 주말 첫차시간 수정");			
			System.out.println("8. 주말 막차시간 수정");			
			System.out.println("9. 화장실 내외위치 수정");	
			System.out.println("0. 돌아가기");
			
			String num = Main.SC.nextLine();
			
			switch(num) {
			case "1" : editStationName(vo); break;
			case "2" : editStationNo(vo); break;
			case "3" : editTransferYn(vo); break;
			case "4" : editExitCount(vo); break;
			case "5" : editWeekStartTime(vo); break;
			case "6" : editWeekEndTime(vo); break;
			case "7" : editHolStartTime(vo); break;
			case "8" : editHolEndTime(vo); break;
			case "9" : editTolietIo(vo); break;
			case "0" : return;
			default : System.out.println("잘못입력하셨습니다. 다시 시도해주세요.");
			}
			
			System.out.println("수정되었습니다!");
			System.out.println();
			
		} catch(Exception e) {
			System.err.println("역 정보 수정에 실패했습니다.");
			e.printStackTrace();
		}
	}//subwayStationEdit
	
	// 역 이름 수정
	private void editStationName(StationVo vo) throws Exception {
		System.out.print("새 역 이름 : ");
		String editStationName = Main.SC.nextLine();
		
		int result = stationService.stationNameEdit(vo, editStationName);
		
		if(result == 1) {
			System.out.println("역 이름이 변경되었습니다.");
		} else {
			throw new Exception();
		}
	}
	
	// 노선 번호 수정
	private void editStationNo(StationVo vo) throws Exception {
		System.out.print("새 노선 번호 : ");
		String editStationNo = Main.SC.nextLine();
		
		int result = stationService.editStationNo(vo, editStationNo);

		if(result == 1) {
			System.out.println("노선 번호가 변경되었습니다.");
		} else {
			throw new Exception();
		}
	}

	// 환승여부 수정
	private void editTransferYn(StationVo vo) throws Exception {
		System.out.print("환승여부 수정(Y/N) : ");
		String editTransferYn = Main.SC.nextLine();
		
		int result = stationService.editTransferYn(vo, editTransferYn);

		if(result == 1) {
			System.out.println("환승여부가 변경되었습니다.");
		} else {
			throw new Exception();
		}
	}

	// 출구 개수 수정
	private void editExitCount(StationVo vo) throws Exception {
		System.out.print("출구 개수 수정 : ");
		String editExitCount = Main.SC.nextLine();
		
		int result = stationService.editExitCount(vo, editExitCount);

		if(result == 1) {
			System.out.println("출구 개수가 변경되었습니다.");
		} else {
			throw new Exception();
		}
	}

	// 평일 첫차시간 수정
	private void editWeekStartTime(StationVo vo) throws Exception {
		System.out.print("평일 첫차시간 수정(ex:0520) : ");
		String editWeekStartTime = Main.SC.nextLine();
		
		int result = stationService.editWeekStartTime(vo, editWeekStartTime);

		if(result == 1) {
			System.out.println("평일 첫차시간이 변경되었습니다.");
		} else {
			throw new Exception();
		}
	}

	// 평일 막차시간 수정
	private void editWeekEndTime(StationVo vo) throws Exception {
		System.out.print("평일 막차시간 수정(ex:0520) : ");
		String editWeekEndTime = Main.SC.nextLine();
		
		int result = stationService.editWeekEndTime(vo, editWeekEndTime);

		if(result == 1) {
			System.out.println("평일 막차시간이 변경되었습니다.");
		} else {
			throw new Exception();
		}
	}

	// 주말 첫차시간 수정
	private void editHolStartTime(StationVo vo) throws Exception {
		System.out.print("주말 첫차시간 수정(ex:0520) : ");
		String editHolStartTime = Main.SC.nextLine();
		
		int result = stationService.editHolStartTime(vo, editHolStartTime);

		if(result == 1) {
			System.out.println("주말 첫차시간이 변경되었습니다.");
		} else {
			throw new Exception();
		}
	}

	// 주말 막차시간 수정
	private void editHolEndTime(StationVo vo) throws Exception {
		System.out.print("주말 막차시간 수정(ex:0520) : ");
		String editHolEndTime = Main.SC.nextLine();
		
		int result = stationService.editHolEndTime(vo, editHolEndTime);

		if(result == 1) {
			System.out.println("주말 막차시간이 변경되었습니다.");
		} else {
			throw new Exception();
		}
	}
	
	// 화장실 내외위치 수정
	private void editTolietIo(StationVo vo) throws Exception {
		System.out.print("화장실 내외위치 수정(I/O) : ");
		String editTolietIo = Main.SC.nextLine();
		
		int result = stationService.editTolietIo(vo, editTolietIo);

		if(result == 1) {
			System.out.println("화장실 내외위치가 변경되었습니다.");
		} else {
			throw new Exception();
		}
	}
	
}
