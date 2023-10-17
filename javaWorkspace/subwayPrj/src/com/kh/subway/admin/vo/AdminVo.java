package com.kh.subway.admin.vo;

public class AdminVo {
	
	private String adminNo;
	private String id;
	private String pwd;
	private String nick;
	private String deleteYn;
	private String partName;
	
	public AdminVo() {
	}
	public AdminVo(String adminNo, String id, String pwd, String nick, String deleteYn, String partName) {
		super();
		this.adminNo = adminNo;
		this.id = id;
		this.pwd = pwd;
		this.nick = nick;
		this.deleteYn = deleteYn;
		this.partName = partName;
	}
	public String getAdminNo() {
		return adminNo;
	}
	public void setAdminNo(String adminNo) {
		this.adminNo = adminNo;
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
	public String getDeleteYn() {
		return deleteYn;
	}
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	@Override
	public String toString() {
		return "AdminVo [adminNo=" + adminNo + ", id=" + id + ", pwd=" + pwd + ", nick=" + nick + ", deleteYn="
				+ deleteYn + ", partName=" + partName + "]";
	}
	
}
