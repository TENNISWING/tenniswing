package com.tenniswing.project.match.service;

import java.util.Date;

import lombok.Data;

@Data
public class MatchVO {
	private int matchNo;
	private String memNo;	
	private String memId;	
	private Date matchDate;
	private int time;
	private String gameWay;
	private String gameWayName;
	private String progState;
	private String progStateName;
	private String ntrp;
	private String lv;
	private String lvName;
	private String gen;
	private String genName;
	private int crtUseAmt;
	private int crtUseAmtName;
	private int recruitPsnNum;
	private int recruitPsnNumName;
	private String detailCtt;
	private int crtroomNo;
	
	private String clubName;
	private int clubNo;
	
	private String contName;
	private Date startDate;
	private Date endDate;
	private int contAttendCost;
	
	private int rsPrice;
	
	private int page;
	private int rn;
	
	private String crtroomName;
	private String crtroomLocation;
	private String crtroomLocationName;
	
	private int clubMatchRecruitPsnNum;
	private Date matchRecruitWriteDate;
	
}
