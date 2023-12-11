package com.tenniswing.project.court.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CalcTableVO {
	private int calcNo;
	private String hostId;
	private String hostName;
	private int payPrice;
	private int cancelPrice;
	private int calcPrice;
	private String depositor;
	private String bank;
	private String accountNo;
	private String calcState;
	private String calcStateName;
	private Double calcComm;
	private int payCase;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date calcStartDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date calcEndDate;
}
