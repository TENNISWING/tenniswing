package com.tenniswing.project.club.service;

import java.util.Date;

import lombok.Data;

@Data
public class ClubVO {
	private int clubNo;
	private String clubName;
	private String clubGen;
	private String clubAge;
	private String clubDay;
	private String clubTime;
	private String clubIntro;
	private String clubCrt;
	private int clubMemNo;
	private int clubRecruitment;
	private String memNo;
	private Date clubcreateDate;
}
