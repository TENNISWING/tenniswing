package com.tenniswing.project.club.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

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
	
	//구분코드
	private String clubGenName;
	private String clubRegionName;
	private String clubDayName;
	private String clubAgeName;
	private String clubTimeName;
	private String clubRecruitmentName;
	
	private List<ClubPostVO> clubPostList;

	//attach
	private List<MultipartFile> files = new ArrayList<>();
	private List<AttachVO> attachList;
	private String attachPath;
	
	//회원
	private String clubJoinApplyWrt;
	private String clubApprove;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date joinDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date quitDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date applyDate;
	
	
}
