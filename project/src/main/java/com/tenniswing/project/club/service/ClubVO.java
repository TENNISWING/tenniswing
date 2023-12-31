package com.tenniswing.project.club.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.tenniswing.project.attach.service.AttachVO;

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
	private String clubState;
	private String name;
	
	private List<String> clubTimeList;
	
	//구분코드
	private String clubGenName;
	private String clubRegionName;
	private String clubDayName;
	private String clubAgeName;
	private String clubTimeName;
	private String clubRecruitmentName;
	private String clubApproveName;
	
	private List<ClubPostVO> clubPostList;
	
	private List<ClubVO> clubMemList;

	//attach
	private List<AttachVO> attachList;
	private String attachPath;
	
	//회원
	private int clubMemNo;
	private String clubJoinApplyWrt;
	private String clubApprove;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date joinDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date quitDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date applyDate;
	
	private List<ClubVO> memInquiryList;
	
	private String memNtrp;
	private String nick;
	private String prImage;
	
	//페이징
	private int page;
	private int rn;
	private int pageUnit=7;
		
	private String sort;
	
	
	//매치
	private int clubMatchRecruitNo;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date clubMatchDate;
	private int clubMatchRecruitPsnNum;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date matchRecruitWriteDate;
	private String recruitState;
	private String div;
	private int matchOpponentNo;
	private int matchNo;
	
	private String memNo;
	private int recruitNo;
	private String pvtMatchRst;
	
	private int clubMatchNo;
	private String clubMatchRst;
	private int clubMatchAttendNum;
	private int clubMatchRstNo;
	
	
	//참여인원 카운트
	private int joinMem;
	
	
	
}
