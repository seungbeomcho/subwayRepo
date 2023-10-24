package com.kh.subway.comment.vo;

public class CommentVo {
	private String commentNo;
	private String boardNo;
	private String userNo;
	private String adminNo;
	private String content;
	private String enrollDate;
	private String modifyDate;
	private String deleteYn;
	public CommentVo() {
	}
	public CommentVo(String commentNo, String boardNo, String userNo, String adminNo, String content, String enrollDate,
			String modifyDate, String deleteYn) {
		super();
		this.commentNo = commentNo;
		this.boardNo = boardNo;
		this.userNo = userNo;
		this.adminNo = adminNo;
		this.content = content;
		this.enrollDate = enrollDate;
		this.modifyDate = modifyDate;
		this.deleteYn = deleteYn;
	}
	public String getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(String commentNo) {
		this.commentNo = commentNo;
	}
	public String getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(String boardNo) {
		this.boardNo = boardNo;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getAdminNo() {
		return adminNo;
	}
	public void setAdminNo(String adminNo) {
		this.adminNo = adminNo;
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
	public String getDeleteYn() {
		return deleteYn;
	}
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	@Override
	public String toString() {
		return "CommentVo [commentNo=" + commentNo + ", boardNo=" + boardNo + ", userNo=" + userNo + ", adminNo="
				+ adminNo + ", content=" + content + ", enrollDate=" + enrollDate + ", modifyDate=" + modifyDate
				+ ", deleteYn=" + deleteYn + "]";
	}
	
	
	
	
	
}
