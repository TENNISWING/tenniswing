package com.tenniswing.project.club.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ClubMatchVO {
	private int clubMatchNo;
	private String clubMatchRst;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date clubMatchDate;
	private int clubMatchAttendNum;
	private int clubMatchRecruitNo;
	
	
	private int clubMatchRecruitPsnNum;
	private int clubNo;
	private Date matchRecruitWriteDate;
	private String recruitState;
	private String div;
	private int matchOpponentNo;
	private int matchNo;
	
	
	private int recruitNo;
	private String memNo;
	private String pvtMatchRst;
	
	
	
	
}
