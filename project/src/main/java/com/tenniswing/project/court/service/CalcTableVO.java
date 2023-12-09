package com.tenniswing.project.court.service;

import lombok.Data;

@Data
public class CalcTableVO {
	private int calcNo;
	private String hostId;
	private String hostName;
	private int totalPayPrice;
	private int cancelPrice;
	private int calcPrice;
	private String depositor;
	private String bank;
	private String accountNo;
	private String calcState;
	private String calcStateName;
}
