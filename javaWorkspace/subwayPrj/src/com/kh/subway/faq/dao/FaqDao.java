package com.kh.subway.faq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kh.subway.faq.vo.FaqVo;

import javaJDBCTEMPLATE.JDBCTemplate;

public class FaqDao {

	
	//faq 목록 (최신순)
	public List<FaqVo> faqList (Connection conn)throws Exception{
		
	
		//SQL
		String sql = "";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		List<FaqVo> voList = new ArrayList<FaqVo>();
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;   
		
		
		
		
		
	}
	}
	
	
	
	
//}//class
