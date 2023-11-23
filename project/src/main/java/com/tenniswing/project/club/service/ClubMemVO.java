package com.tenniswing.project.club.service;

import java.util.Date;

import lombok.Data;

@Data
public class ClubMemVO {
	private int clubNo;
	private String clubJoinApplyWrt;
	private String clubApprove;
	private String memNo;
	private Date joinDate;
	private Date QuitDate;
	private Date applyDate;
}
