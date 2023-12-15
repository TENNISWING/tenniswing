package com.tenniswing.project.shop.service;

import lombok.Data;

@Data
public class WishVO {
	private int wishNo;
	private String memId;
	private int prodNo;
	
	// 목록 출력
	private String attachPath;
	private String prodName;
	private String prodBrand;
	private int prodPrice;
	private String cateProd;
}
