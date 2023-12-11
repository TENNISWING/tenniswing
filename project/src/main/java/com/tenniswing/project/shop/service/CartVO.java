package com.tenniswing.project.shop.service;

import lombok.Data;

@Data
public class CartVO {
	// DB 칼럼
	
	// 카트 번호
	private int cartNo;
	// 카트 상품 수량
	private int cartProdQt;
	// 카트 회원
	private String memId;
	// 카트 상품 상세 번호
	private int prodDetailNo;
	
	// 장바구니에 보여줘야할 데이터
	// 상품 가격
	private int prodPrice;
	// 상품 이름
	private String prodName;
	// 상품 첨부파일
	private String attachPath;
	
	// 장바구니 추가시 필요한 데이터
	// 추가로 필요한 다른 테이블의 칼럼들
	private int prodNo;
	private int prodDetailSto;
	private String cateColor;
	private String cateSize;
	
	private int orderNo;
	private String type;

}