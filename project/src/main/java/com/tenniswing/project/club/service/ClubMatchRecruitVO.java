package com.tenniswing.project.club.service;

import java.util.Date;

import lombok.Data;

@Data
public class ClubMatchRecruitVO {
	private int clubMatchRecruitNo;
	private int clubMatchRecruitPsnNo;
	private int clubNo;
	private Date matchRecruitWriteDate;
	private String recruitState;
	private String div;
	private int matchOpponentNo;
	private int matchNo;
}
