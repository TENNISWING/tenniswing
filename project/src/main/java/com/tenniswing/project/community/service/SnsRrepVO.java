package com.tenniswing.project.community.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class SnsRrepVO {
	//대댓글
	private int snsRrepNo;
	private String snsRrepCtt;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date snsRRrepWriteDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date snsRrepEditDate;
	private String memId;
	private int snsRepNo;
}
