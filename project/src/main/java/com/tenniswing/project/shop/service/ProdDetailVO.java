package com.tenniswing.project.shop.service;

import lombok.Data;

@Data
public class ProdDetailVO {
	// 상품 상세 번호
	private int prodDetailNo;
	// 상품 상세 재고
	private int prodDetailSto;
	// 상품 상세 판매 유무
	private String prodDetailSaleSts;
	// 상품 상세 컬러
	private String cateColor;
	// 상품 상세 사이즈
	private String cateSize;
	// 상품 번호
	private int prodNo;
}
