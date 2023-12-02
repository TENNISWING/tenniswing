package com.tenniswing.project.court.service;

import java.util.Date;

import lombok.Data;

@Data
public class CrtRefundVO {
	private Date cancelDate;
	private int refundPrice;
	private int refundCharge;
	private int reserveNo;
	private int reserveCancelNo;
	
	private String reservePayNO; //imp_uid
	private String reserveUid;   //merchant_uid
}
