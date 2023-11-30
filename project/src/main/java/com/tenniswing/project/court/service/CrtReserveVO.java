package com.tenniswing.project.court.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CrtReserveVO {
	
	// 예약 테이블
	private int reserveNo;
	private int reservePrice;
	private int reserveState;
	private int reservePayNo;
	private int crtroomNo;
	private int crtDetailNo;
	private String memId;
	
	// 예약 상세 테이블
	private int reserveDetailNo;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date reserveDate;
	private String reserveTime;
	private String tReserveTime;
	
	//
	private String crtroomName;
	private String crtroomLocation;
	private String crtNo;
}
