package com.kh.subway.faq.vo;

public class FaqVo {
	
	private String faqno;
	private String stationno;
	private String adminno;
	private String faqtitle;
	private String content;
	private String inquiry;
	private String posttime;
	private String deleteyn;
	private String modifydate;
	
	public FaqVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FaqVo(String faqno, String stationno, String adminno, String faqtitle, String content, String inquiry,
			String posttime, String deleteyn, String modifydate) {
		super();
		this.faqno = faqno;
		this.stationno = stationno;
		this.adminno = adminno;
		this.faqtitle = faqtitle;
		this.content = content;
		this.inquiry = inquiry;
		this.posttime = posttime;
		this.deleteyn = deleteyn;
		this.modifydate = modifydate;
	}

	public String getFaqno() {
		return faqno;
	}

	public void setFaqno(String faqno) {
		this.faqno = faqno;
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

	public String getFaqtitle() {
		return faqtitle;
	}

	public void setFaqtitle(String faqtitle) {
		this.faqtitle = faqtitle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getInquiry() {
		return inquiry;
	}

	public void setInquiry(String inquiry) {
		this.inquiry = inquiry;
	}

	public String getPosttime() {
		return posttime;
	}

	public void setPosttime(String posttime) {
		this.posttime = posttime;
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
		return "FaqVo [faqno=" + faqno + ", stationno=" + stationno + ", adminno=" + adminno + ", faqtitle=" + faqtitle
				+ ", content=" + content + ", inquiry=" + inquiry + ", posttime=" + posttime + ", deleteyn=" + deleteyn
				+ ", modifydate=" + modifydate + "]";
	}
	
	
	
	
	
}
