package com.tenniswing.project.court.service;

import java.util.Date;

import lombok.Data;

@Data
public class CourtroomVO {
	private int courtroomNo;
	private String hostNo;
	private String courtroomName;
	private String courtroomExplanation;
	private String courtroomRegion;
	private String courtroomLocation;
	private String courtroomOperateTime;
	private String courtroomCaution;
	private String courtroomUseGuide;
	private int courtroomRefundCharge;
	private int courtroomIndoorOutdoor;
	private Date courtroomRegisterDate;
}
