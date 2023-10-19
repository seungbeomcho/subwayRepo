package com.kh.subway.qna.vo;

public class QnaVo {

	private String qnaNo;
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
	public String getQnaNo() {
		return qnaNo;
	}
	public void setQnaNo(String qnaNo) {
		this.qnaNo = qnaNo;
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
	public QnaVo() {
	}
	public QnaVo(String qnaNo, String stationNo, String adminNo, String userNo, String postTime, String title,
			String content, String reTitle, String reContent, String inquiry, String deleteYn, String writerNick) {
		super();
		this.qnaNo = qnaNo;
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
	}
	@Override
	public String toString() {
		return "QnaVo [qnaNo=" + qnaNo + ", stationNo=" + stationNo + ", adminNo=" + adminNo + ", userNo=" + userNo
				+ ", postTime=" + postTime + ", title=" + title + ", content=" + content + ", reTitle=" + reTitle
				+ ", reContent=" + reContent + ", inquiry=" + inquiry + ", deleteYn=" + deleteYn + ", writerNick="
				+ writerNick + "]";
	}
	
}