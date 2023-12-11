package com.tenniswing.project.shop.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class OrderTableVO {
	// 주문번호
	private int orderNo;
	// 주문 총 결제 금액
	private int orderTPayAmt;
	// 주문 총 갯수
	private int orderProdCnt;
	// 주문 날짜
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date orderDate;
	// 주문 상태
	private String orderState;
	// 주문 우편번호
	private String orderAdr1;
	// 주문 배송지
	private String orderAdr2;
	// 주문 상세주소
	private String orderAdr3;
	// 주문 받는분 이름
	private String orderRptName;
	// 주문 받는분 전화
	private String orderRptTel;
	// 주문 요청사항
	private String orderReq;
	// 주문 회원 아이디
	private String memId;
	
	////////////////////////
	// 주문 상세 조회
	private String name;
	private String phoneNo;
	private String payMethod;
	private int payAmt;
	private Date payDate;
	private int orderDetailCnt;
	private int orderDetailAmt;
	private String prodName;
	private String cateColor;
	private String cateSize;
	private String attachPath;
	////////////////////////
	// 상품 주문시
	private int prodNo;
	private int prodPrice;
	private int prodDetailNo;
	private int prodDetailSto;
	private int orderDetailNo;
	private int payNo;
	private String type;
	
	private String impUid;
	private String merchantUid;
}


