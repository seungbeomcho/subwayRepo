package com.kh.subway.store.vo;

public class SubwayVo {

	private String storeNo;                     
    private String adminNo;                 
    private String tel;               
    private String storeName;
    private String address;          
    private String delYn;                   
    private String closeDate;                
    private String openDate;            
    private String modifydate;     
    private String newYn;
	
    public SubwayVo() {

	}
	
	public SubwayVo(String storeNo, String adminNo, String tel, String storeName, String address, String delYn,
			String closeDate, String openDate, String modifydate, String newYn) {
		super();
		this.storeNo = storeNo;
		this.adminNo = adminNo;
		this.tel = tel;
		this.storeName = storeName;
		this.address = address;
		this.delYn = delYn;
		this.closeDate = closeDate;
		this.openDate = openDate;
		this.modifydate = modifydate;
		this.newYn = newYn;
	}
	
	public String getStoreNo() {
		return storeNo;
	}
	
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}
	
	public String getAdminNo() {
		return adminNo;
	}
	
	public void setAdminNo(String adminNo) {
		this.adminNo = adminNo;
	}
	
	public String getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getStoreName() {
		return storeName;
	}
	
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getDelYn() {
		return delYn;
	}
	
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	
	public String getCloseDate() {
		return closeDate;
	}
	
	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}
	
	public String getOpenDate() {
		return openDate;
	}
	
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	
	public String getModifydate() {
		return modifydate;
	}
	
	public void setModifydate(String modifydate) {
		this.modifydate = modifydate;
	}
	
	public String getNewYn() {
		return newYn;
	}
	
	public void setNewYn(String newYn) {
		this.newYn = newYn;
	}
	
	public String toString() {
		return "SubwayVo [storeNo=" + storeNo + ", adminNo=" + adminNo + ", tel=" + tel + ", storeName=" + storeName
				+ ", address=" + address + ", delYn=" + delYn + ", closeDate=" + closeDate + ", openDate=" + openDate
				+ ", modifydate=" + modifydate + ", newYn=" + newYn + "]";
	}  
    
    
}
