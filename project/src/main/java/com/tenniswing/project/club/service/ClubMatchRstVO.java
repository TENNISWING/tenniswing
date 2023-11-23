package com.tenniswing.project.club.service;

import java.util.Date;

import lombok.Data;

@Data
public class ClubMatchRstVO {
	private int clubMatchNo;
	private String clubMatchRst;
	private Date clubMatchDate;
	private int clubMatchAttendNum;
	private int clubMatchRecruitNo;
}
