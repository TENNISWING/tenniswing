package com.tenniswing.project.community.service;

import java.util.Date;
import java.util.List;

import lombok.Data;
@Data
public class SnsRepVO {
	//sns 댓글
	private int snsRepNo;
	private String snsRepCtt;
	private Date snsRepWriteDate;
	private Date snsRepEditDate;
	private int snsWrtNo;
	private String memNo;
	// + 글번호, 멤버no
	
	private List<SnsRepVO> snsRRepList;
	
}
