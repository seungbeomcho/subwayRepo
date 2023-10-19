package com.kh.subway.notice.vo;

public class NoticeVo {
	
	private String noticeno;
	private String stationno;
	private String adminno;
	private String posttime;
	private String title;
	private String content;
	private String inqiry;
	private String deleteyn;
	private String modifydate;
	
	public NoticeVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoticeVo(String noticeno, String stationno, String adminno, String posttime, String title, String content,
			String inqiry, String deleteyn, String modifydate) {
		super();
		this.noticeno = noticeno;
		this.stationno = stationno;
		this.adminno = adminno;
		this.posttime = posttime;
		this.title = title;
		this.content = content;
		this.inqiry = inqiry;
		this.deleteyn = deleteyn;
		this.modifydate = modifydate;
	}

	public String getNoticeno() {
		return noticeno;
	}

	public void setNoticeno(String noticeno) {
		this.noticeno = noticeno;
	}

	public String getStationno() {
		return stationno;
	}

	public void setStationno(String stationno) {
		this.stationno = stationno;
	}

	public String getAdminno() {
		return adminno;
	}

	public void setAdminno(String adminno) {
		this.adminno = adminno;
	}

	public String getPosttime() {
		return posttime;
	}

	public void setPosttime(String posttime) {
		this.posttime = posttime;
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

	public String getInqiry() {
		return inqiry;
	}

	public void setInqiry(String inqiry) {
		this.inqiry = inqiry;
	}

	public String getDeleteyn() {
		return deleteyn;
	}

	public void setDeleteyn(String deleteyn) {
		this.deleteyn = deleteyn;
	}

	public String getModifydate() {
		return modifydate;
	}

	public void setModifydate(String modifydate) {
		this.modifydate = modifydate;
	}

	@Override
	public String toString() {
		return "NoticeVo [noticeno=" + noticeno + ", stationno=" + stationno + ", adminno=" + adminno + ", posttime="
				+ posttime + ", title=" + title + ", content=" + content + ", inqiry=" + inqiry + ", deleteyn="
				+ deleteyn + ", modifydate=" + modifydate + "]";
	}
	
	

}
