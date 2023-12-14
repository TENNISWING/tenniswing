package com.tenniswing.project.match.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.tenniswing.project.attach.service.AttachVO;

import lombok.Data;

@Data
public class MatchVO {
	private int matchNo;
	private String memNo;	
	private String memId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date matchDate;
	private String time;
	private String age;
	private String gameWay;
	private String gameWayName;
	private String progState;
	private String progStateName;
	private String ntrp;
	private String ntrpName;
	private String lv;
	private String lvName;
	private String gen;
	private String genName;
	private int crtUseAmt;
	private int crtUseAmtName;
	private int recruitPsnNum;
	private int recruitPsnNumName;
	private String detailCtt;
	private Integer crtroomNo;
	
	private String clubName;
	private int clubNo;
	private String clubMatchRst;
	private String clubMatchRstName;
	private String div;
	
	
	private String contName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	private int contAttendCost;
	
	private int rsPrice;
	
	private int page;
	private int rn;
	private int pageUnit=5;
	
	private String crtroomName;
	private String crtroomLocation;
	private String crtroomLocationName;
	
	private int clubMatchRecruitPsnNum;
	private Date matchRecruitWriteDate;
	
	private String attachPath; 
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date inDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updateDate;
	
	private String name;
	
	// attach
	private List<AttachVO> attachList;
	
	private String nick;
	
	
	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date today;
	
}
