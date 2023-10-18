package com.kh.subway.board.vo;

public class BoardVo {
	
	private String boardNo;
	private String stationNo;
	private String stationName;
	private String userNo;
	private String title;
	private String content;
	private String enrollDate;
	private String modifyDate;
	private String inquiry;
	private String writerNick;
	private String deleteYn;
	
	
	
	
	public BoardVo() {
	}




	public BoardVo(String boardNo, String stationNo, String stationName, String userNo, String title, String content,
			String enrollDate, String modifyDate, String inquiry, String writerNick, String deleteYn) {
		super();
		this.boardNo = boardNo;
		this.stationNo = stationNo;
		this.stationName = stationName;
		this.userNo = userNo;
		this.title = title;
		this.content = content;
		this.enrollDate = enrollDate;
		this.modifyDate = modifyDate;
		this.inquiry = inquiry;
		this.writerNick = writerNick;
		this.deleteYn = deleteYn;
	}




	public String getBoardNo() {
		return boardNo;
	}




	public void setBoardNo(String boardNo) {
		this.boardNo = boardNo;
	}




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




	public String getUserNo() {
		return userNo;
	}




	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}




	public String getTitle() {
		return title;
	}




	public void setTitle(String title) {
		this.title = title;
	}




	public String getContent() {
		return content;
	}




	public void setContent(String content) {
		this.content = content;
	}




	public String getEnrollDate() {
		return enrollDate;
	}




	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}




	public String getModifyDate() {
		return modifyDate;
	}




	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}




	public String getInquiry() {
		return inquiry;
	}




	public void setInquiry(String inquiry) {
		this.inquiry = inquiry;
	}




	public String getWriterNick() {
		return writerNick;
	}




	public void setWriterNick(String writerNick) {
		this.writerNick = writerNick;
	}




	public String getDeleteYn() {
		return deleteYn;
	}




	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}




	@Override
	public String toString() {
		return "BoardVo [boardNo=" + boardNo + ", stationNo=" + stationNo + ", stationName=" + stationName + ", userNo="
				+ userNo + ", title=" + title + ", content=" + content + ", enrollDate=" + enrollDate + ", modifyDate="
				+ modifyDate + ", inquiry=" + inquiry + ", writerNick=" + writerNick + ", deleteYn=" + deleteYn + "]";
	}
	
	
	
}
