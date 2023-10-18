package com.kh.subway.faq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.kh.subway.faq.vo.FaqVo;

public class FaqDao {

	
	//faq 목록 (최신순)
	public List<FaqVo> faqList (Connection conn){
		
	
		//SQL
		String sql = "";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		List<FaqVo> voList = new ArrayList<FaqVo>();
		while
		
		//close
		
		
		
		
		
		
		
	}
	
	
	
	
}//class
