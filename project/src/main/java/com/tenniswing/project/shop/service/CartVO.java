package com.tenniswing.project.shop.service;

import lombok.Data;

@Data
public class CartVO {
	private int cartNo;
	private int cartProdQt;
	private String memId;
	private int prodDetailNo;
}