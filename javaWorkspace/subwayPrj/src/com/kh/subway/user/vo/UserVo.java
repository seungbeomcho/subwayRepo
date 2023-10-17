package com.kh.subway.user.vo;

public class UserVo {
	
	private String userNo;
	private String id;
	private String pwd;
	private String nick;
	private String enrollDate;
	private String delYn;
	private String pwdeditDate;
	
	public UserVo() {
		
	}
	public UserVo(String userNo, String id, String pwd, String nick, String enrollDate, String delYn,
			String pwdeditDate) {
		this.userNo = userNo;
		this.id = id;
		this.pwd = pwd;
		this.nick = nick;
		this.enrollDate = enrollDate;
		this.delYn = delYn;
		this.pwdeditDate = pwdeditDate;
	}
	
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	public String getPwdeditDate() {
		return pwdeditDate;
	}
	public void setPwdeditDate(String pwdeditDate) {
		this.pwdeditDate = pwdeditDate;
	}
	
	@Override
	public String toString() {
		return "UserVo [userNo=" + userNo + ", id=" + id + ", pwd=" + pwd + ", nick=" + nick + ", enrollDate="
				+ enrollDate + ", delYn=" + delYn + ", pwdeditDate=" + pwdeditDate + "]";
	}

}
