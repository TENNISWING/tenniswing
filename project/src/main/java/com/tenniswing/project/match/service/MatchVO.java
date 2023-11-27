package com.tenniswing.project.match.service;

import java.util.Date;

import lombok.Data;

@Data
public class MatchVO {
	private int matchNo;
	private String memNo;
	private Date matchDate;
	private Date Time;
	private String gameWay;
	private String progState;
	private String ntrp;
	private String lv;
	private String gen;
	private int crtUseAmt;
	private int recruitPsnNum;
	private String detailCtt;
	private int crtroomNo;
	
	private String clubName;
	private int clubNo;
	
	private String contName;
	private Date startDate;
	private Date endDate;
	private int contAttendCost;
	
	private int rsPrice;
		
	private String crtroomName;
	private String crtroomLocation;
	
	private int clubMatchRecruitPsnNum;
	private Date matchRecruitWriteDate;
	
}
