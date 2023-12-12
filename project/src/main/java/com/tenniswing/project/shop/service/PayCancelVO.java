package com.tenniswing.project.shop.service;

import java.util.Date;

import lombok.Data;

@Data
public class PayCancelVO {
	// 결제 취소 번호
	private int payCancelNo;
	// 결제 취소 일자
	private Date payCancelDate;
	// 결제 취소 금액
	private int payCancelAMt;
	// 결제 취소 구분
	private String payCancelApplyPart;
	// 결제 취소 상태
	private String payCancelCsState;
	// 주문 번호
	private int orderNo;
}
