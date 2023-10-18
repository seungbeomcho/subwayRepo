package com.kh.subway.faq.dao;

class FaqDao {

	//작성하기
	String sql = "~~";
	PreparedStatement pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, vo.faqtitle());
	pstmt.setString(2, vo.getContent());
	pstmt.setString(3, Main.LoginMember.getNo());
	int result = pstmt.executeUpdate();
	
	
	
	
	
	
	
	
	
	
	
	
}//class
