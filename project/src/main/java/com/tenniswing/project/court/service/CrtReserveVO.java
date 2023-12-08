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
	private String reserveState;
	private String reserveStateName;
	private String reservePayNo;
	private int crtroomNo;
	private int crtDetailNo;
	private String memId;
	private String reserveUid;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date payDate;
	
	// 예약 상세 테이블
	private int reserveDetailNo;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date reserveDate;
	private String reserveTime;
	private String tReserveTime;
	
	// 화면단 뿌려줄 때 사용
	private String crtroomName;
	private String crtroomLocation;
	private String crtNo;
	
	// 아작스로 예약 가능시간 뿌려줄 때 사용
	private String crtIndoorOutdoor;
	private String reserveTimeName;
	private String crtIndoorOutdoorName;
	
	// 예약 구분코드
	private String subcategoryCode;
	private String name;
	
	// 환불 테이블
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date cancelDate;
	private int refundPrice;
	private int reserveCancelNo;
	private int refundCharge;
	private String refundReason;
	
	// 정산 테이블
	private int calcNo;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date calcDate;
	private int calcPrice;
	private int payPrice;
	private String hostId;
	private String calcState;
	private String calcStateName;
	private int calcComm;
	private int payCase;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date calcStartDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date calcEndDate;
}
