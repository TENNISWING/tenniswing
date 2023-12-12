package com.tenniswing.project.shop.service;

import lombok.Data;

@Data
public class OrderDetailVO {
	// 주문 상세 번호
	private int orderDetailNo;
	// 주문 상세 가격
	private int orderDetailPrice;
	// 주문 상세 개수
	private int orderDetailCnt;
	// 주문 상세 금액
	private int orderDetailAmt;
	// 주문번호
	private int orderNo;
	// 상품 상세 번호
	private int prodDetailNo;
}
