package com.tenniswing.project.club.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ClubVO {
	private int clubNo;
	private String clubName;
	private String clubRegion;
	private String clubGen;
	private String clubAge;
	private String clubDay;
	private String clubTime;
	private String clubIntro;
	private String clubCrt;
	private int clubMemNum;
	private String clubRecruitment;
	private String memId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date clubCreateDate;
	
	//구분코드
	private String clubGenName;
	private String clubRegionName;
	private String clubDayName;
	private String clubAgeName;
	private String clubTimeName;
	private String clubRecruitmentName;
	
	private List<ClubPostVO> clubPostList;
	
}
