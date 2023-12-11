package com.tenniswing.project.court.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class AdminCourtCalcVO {
	private int reserveNo;
	private String crtroomName;
	private int reservePrice;
	private String reservePayNo;
	private int crtroomNo;
	private int crtDetailNo;
	private String reserveUid;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date payDate;
	private String reserveState;
	private int refundPrice;
	private String hostId;
	
	private int totalCalcPrice;
	
	private String calcStartDate;
	private String calcEndDate;
}
