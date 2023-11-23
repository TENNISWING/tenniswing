package com.tenniswing.project.club.service;

import java.util.Date;

import lombok.Data;

@Data
public class ClubPostVO {
	private int clubPostNo;
	private String clubPostTitle;
	private String clubPostCtt;
	private int clubPostHit;
	private Date clubPostWriteDate;
	private Date clubPostEditDate;
	private String memNo;
	private int clubNo;
}
