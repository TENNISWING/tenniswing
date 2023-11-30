package com.tenniswing.project.club.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ClubPostVO {
	private int clubPostNo;
	private String clubPostTitle;
	private String clubPostCtt;
	private int clubPostHit;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date clubPostWriteDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date clubPostEditDate;
	private String memId;
	private int clubNo;
	
	
}
