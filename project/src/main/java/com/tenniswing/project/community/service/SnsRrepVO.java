package com.tenniswing.project.community.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class SnsRrepVO {
	//대댓글
	private int snsRepNo;
	private int snsRrepNo;
	private String snsRrepCtt;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date snsRrepWriteDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date snsRrepEditDate;
	private String memId;
	private String name;
	
}
