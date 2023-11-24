package com.tenniswing.project.club.service;

import java.util.Date;

import lombok.Data;

@Data
public class ClubPostRepVO {
	private int clubRepNo;
	private String clubRepCtt;
	private Date clubRepWriteDate;
	private Date clubRepEditDate;
	private String memNo;
	private int clubPostNo;
}
