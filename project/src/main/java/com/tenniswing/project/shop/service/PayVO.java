package com.tenniswing.project.shop.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PayVO {
	// 결제 번호
	private String payNo;
	// 결제 수단
	private String payMathod = "t1";
	// 결제 일자
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date payDate;
	// 결제 상태
	private String payState;
	// 결제 금액
	private int payAmt;
	// 주문 번호
	private int orderNo;
}
