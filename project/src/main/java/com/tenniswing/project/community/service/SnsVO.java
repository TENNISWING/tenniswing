package com.tenniswing.project.community.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SnsVO {
	private int snsWrtNo;
	private String snsTitle;
	private String snsCtt; // sns 내용
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date snsWriteDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date snsEditDate;
	private String snsTag;
	private int snsHit;
	private String memNo;
	private int snsGrpNo;
	
	//sns 좋아요
	private int likeNo;
	
	//sns 그룹
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date grpCreateDate;
	private int postNum;
	private String grpName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date grpEditDate;
	
	//sns 댓글
	private int snsRepNo;
	private String snsRepCtt;
	private Date snsRepWriteDate;
	private Date snsRepEditDate;
	
	// sns 대댓글
	private int snsRRepNo;
	private String snsRRepCtt;
	private String snsRRepEditDate;
	private String snsRRepWriteDate;
	
	


}
