package com.tenniswing.project.club.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ClubRepVO {
	private int clubRepNo;
	private String clubRepCtt;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date clubRepWriteDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date clubRepEditDate;
	private String memId;
	private int clubPostNo;
	
	private String name;
	
	private List<ClubRepVO> clubRRepList;
}
