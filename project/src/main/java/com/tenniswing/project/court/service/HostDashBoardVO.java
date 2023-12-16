package com.tenniswing.project.court.service;

import lombok.Data;

@Data
public class HostDashBoardVO {
	private int monthReservePrice;
	private int monthReserveCount;
	private int monthRefundPrice;
	private int monthRefundCount;
	
	private int jan;
	private int feb;
	private int mar;
	private int apr;
	private int may;
	private int jun;
	private int jul;
	private int aug;
	private int sep;
	private int oct;
	private int nov;
	private int dec;
	
	private String memId;
	private String name;
	private String attachPath;
	
	private int five;
	private int four;
	private int three;
	private int two;
	private int one;
}
