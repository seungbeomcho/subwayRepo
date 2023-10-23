package com.kh.subway.comment.vo;

public class CommentVo {
	private String commentNo;
	private String boardNo;
	private String userNick;
	private String content;
	private String enrollDate;
	private String modifyDate;
	private String deleteYn;
	public CommentVo() {
	}
	public CommentVo(String commentNo, String boardNo, String userNick, String content, String enrollDate,
			String modifyDate, String deleteYn) {
		this.commentNo = commentNo;
		this.boardNo = boardNo;
		this.userNick = userNick;
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
	public String getUserNick() {
		return userNick;
	}
	public void setUserNick(String userNick) {
		this.userNick = userNick;
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
		return "CommentVo [commentNo=" + commentNo + ", boardNo=" + boardNo + ", userNick=" + userNick + ", content="
				+ content + ", enrollDate=" + enrollDate + ", modifyDate=" + modifyDate + ", deleteYn=" + deleteYn
				+ "]";
	}
	
	
	
	
}
