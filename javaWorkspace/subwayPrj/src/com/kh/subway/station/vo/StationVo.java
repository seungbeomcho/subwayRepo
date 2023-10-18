package com.kh.subway.station.vo;

public class StationVo {
	//필드
	private String stationNo;
	private String stationName;
	private String lineNo;
	private String transferYn;
	private String exitCount;
	private String weekStartTime;
	private String weekEndTime;
	private String holStartTime;
	private String holEndTime;
	private String toiletIo;
	private String transferLineNo;
	
	//기본 생성자
	public StationVo() {
	}
	
	//매개변수 생성자
	public StationVo(String stationNo, String stationName, String lineNo, String transferYn, String exitCount,
			String weekStartTime, String weekEndTime, String holStartTime, String holEndTime, String toiletIo,
			String transferLineNo) {
		this.stationNo = stationNo;
		this.stationName = stationName;
		this.lineNo = lineNo;
		this.transferYn = transferYn;
		this.exitCount = exitCount;
		this.weekStartTime = weekStartTime;
		this.weekEndTime = weekEndTime;
		this.holStartTime = holStartTime;
		this.holEndTime = holEndTime;
		this.toiletIo = toiletIo;
		this.transferLineNo = transferLineNo;
	}
	
	//getter/setter
	public String getStationNo() {
		return stationNo;
	}
	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getLineNo() {
		return lineNo;
	}
	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}
	public String getTransferYn() {
		return transferYn;
	}
	public void setTransferYn(String transferYn) {
		this.transferYn = transferYn;
	}
	public String getExitCount() {
		return exitCount;
	}
	public void setExitCount(String exitCount) {
		this.exitCount = exitCount;
	}
	public String getWeekStartTime() {
		return weekStartTime;
	}
	public void setWeekStartTime(String weekStartTime) {
		this.weekStartTime = weekStartTime;
	}
	public String getWeekEndTime() {
		return weekEndTime;
	}
	public void setWeekEndTime(String weekEndTime) {
		this.weekEndTime = weekEndTime;
	}
	public String getHolStartTime() {
		return holStartTime;
	}
	public void setHolStartTime(String holStartTime) {
		this.holStartTime = holStartTime;
	}
	public String getHolEndTime() {
		return holEndTime;
	}
	public void setHolEndTime(String holEndTime) {
		this.holEndTime = holEndTime;
	}
	public String getToiletIo() {
		return toiletIo;
	}
	public void setToiletIo(String toiletIo) {
		this.toiletIo = toiletIo;
	}
	public String getTransferLineNo() {
		return transferLineNo;
	}
	public void setTransferLineNo(String transferLineNo) {
		this.transferLineNo = transferLineNo;
	}
	
	//toString
	@Override
	public String toString() {
		return "StationVo [stationNo=" + stationNo + ", stationName=" + stationName + ", lineNo=" + lineNo
				+ ", transferYn=" + transferYn + ", exitCount=" + exitCount + ", weekStartTime=" + weekStartTime
				+ ", weekEndTime=" + weekEndTime + ", holStartTime=" + holStartTime + ", holEndTime=" + holEndTime
				+ ", toiletIo=" + toiletIo + ", transferLineNo=" + transferLineNo + "]";
	}
	
	
	
	
}
