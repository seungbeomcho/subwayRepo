package com.kh.subway.board.vo;

public class BoardVo {
	
	private String boardNo;
	private String stationNo;
	private String stationName;
	private String userNo;
	private String commentNo;
	private String boardComment;
	private String title;
	private String content;
	private String enrollDate;
	private String modifyDate;
	private String inquiry;
	private String writerNick;
	private String deleteYn;
	private String commentCount;
	
	public BoardVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoardVo(String boardNo, String stationNo, String stationName, String userNo, String commentNo,
			String boardComment, String title, String content, String enrollDate, String modifyDate, String inquiry,
			String writerNick, String deleteYn, String commentCount) {
		super();
		this.boardNo = boardNo;
		this.stationNo = stationNo;
		this.stationName = stationName;
		this.userNo = userNo;
		this.commentNo = commentNo;
		this.boardComment = boardComment;
		this.title = title;
		this.content = content;
		this.enrollDate = enrollDate;
		this.modifyDate = modifyDate;
		this.inquiry = inquiry;
		this.writerNick = writerNick;
		this.deleteYn = deleteYn;
		this.commentCount = commentCount;
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

	public String getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(String commentNo) {
		this.commentNo = commentNo;
	}

	public String getBoardComment() {
		return boardComment;
	}

	public void setBoardComment(String boardComment) {
		this.boardComment = boardComment;
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

	public String getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(String commentCount) {
		this.commentCount = commentCount;
	}

	@Override
	public String toString() {
		return "BoardVo [boardNo=" + boardNo + ", stationNo=" + stationNo + ", stationName=" + stationName + ", userNo="
				+ userNo + ", commentNo=" + commentNo + ", boardComment=" + boardComment + ", title=" + title
				+ ", content=" + content + ", enrollDate=" + enrollDate + ", modifyDate=" + modifyDate + ", inquiry="
				+ inquiry + ", writerNick=" + writerNick + ", deleteYn=" + deleteYn + ", commentCount=" + commentCount
				+ "]";
	}
	
	
	
}
