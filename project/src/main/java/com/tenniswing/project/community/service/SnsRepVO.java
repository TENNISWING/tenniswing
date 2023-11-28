package com.tenniswing.project.community.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class SnsRepVO {
	//sns 댓글
	private int snsRepNo;
	private String snsRepCtt;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date snsRepWriteDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date snsRepEditDate;
	private int snsWrtNo;
	private String memNo;
	private String name;
	// + 글번호, 멤버no
	
	private List<SnsRepVO> snsRRepList;
	
}
