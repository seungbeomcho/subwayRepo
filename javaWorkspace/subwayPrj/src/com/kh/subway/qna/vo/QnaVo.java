package com.kh.subway.qna.vo;

public class QnaVo {

	private String qnaNo;
	private String qnaAdminNo;
	private String stationNo;
	private String adminNo;
	private String userNo;
	private String postTime;
	private String title;
	private String content;
	private String reTitle;
	private String reContent;
	private String inquiry; 
	private String deleteYn;
	private String writerNick;
	private String StationName;
	private String rePostTime;
	public String getQnaNo() {
		return qnaNo;
	}
	public void setQnaNo(String qnaNo) {
		this.qnaNo = qnaNo;
	}
	public String getQnaAdminNo() {
		return qnaAdminNo;
	}
	public void setQnaAdminNo(String qnaAdminNo) {
		this.qnaAdminNo = qnaAdminNo;
	}
	public String getStationNo() {
		return stationNo;
	}
	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}
	public String getAdminNo() {
		return adminNo;
	}
	public void setAdminNo(String adminNo) {
		this.adminNo = adminNo;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getPostTime() {
		return postTime;
	}
	public void setPostTime(String postTime) {
		this.postTime = postTime;
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
	public String getReTitle() {
		return reTitle;
	}
	public void setReTitle(String reTitle) {
		this.reTitle = reTitle;
	}
	public String getReContent() {
		return reContent;
	}
	public void setReContent(String reContent) {
		this.reContent = reContent;
	}
	public String getInquiry() {
		return inquiry;
	}
	public void setInquiry(String inquiry) {
		this.inquiry = inquiry;
	}
	public String getDeleteYn() {
		return deleteYn;
	}
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	public String getWriterNick() {
		return writerNick;
	}
	public void setWriterNick(String writerNick) {
		this.writerNick = writerNick;
	}
	public String getStationName() {
		return StationName;
	}
	public void setStationName(String stationName) {
		StationName = stationName;
	}
	public String getRePostTime() {
		return rePostTime;
	}
	public void setRePostTime(String rePostTime) {
		this.rePostTime = rePostTime;
	}
	public QnaVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QnaVo(String qnaNo, String qnaAdminNo, String stationNo, String adminNo, String userNo, String postTime,
			String title, String content, String reTitle, String reContent, String inquiry, String deleteYn,
			String writerNick, String stationName, String rePostTime) {
		super();
		this.qnaNo = qnaNo;
		this.qnaAdminNo = qnaAdminNo;
		this.stationNo = stationNo;
		this.adminNo = adminNo;
		this.userNo = userNo;
		this.postTime = postTime;
		this.title = title;
		this.content = content;
		this.reTitle = reTitle;
		this.reContent = reContent;
		this.inquiry = inquiry;
		this.deleteYn = deleteYn;
		this.writerNick = writerNick;
		StationName = stationName;
		this.rePostTime = rePostTime;
	}
	@Override
	public String toString() {
		return "QnaVo [qnaNo=" + qnaNo + ", qnaAdminNo=" + qnaAdminNo + ", stationNo=" + stationNo + ", adminNo="
				+ adminNo + ", userNo=" + userNo + ", postTime=" + postTime + ", title=" + title + ", content="
				+ content + ", reTitle=" + reTitle + ", reContent=" + reContent + ", inquiry=" + inquiry + ", deleteYn="
				+ deleteYn + ", writerNick=" + writerNick + ", StationName=" + StationName + ", rePostTime="
				+ rePostTime + "]";
	}
	
}