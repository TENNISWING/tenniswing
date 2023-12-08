package com.tenniswing.project.court.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CrtRefundVO {
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date cancelDate;
	private int refundPrice;
	private int refundCharge;
	private int reserveNo;
	private int reserveCancelNo;
	private String refundReason;
	
	private String reservePayNO; //imp_uid
	private String reserveUid;   //merchant_uid
}
