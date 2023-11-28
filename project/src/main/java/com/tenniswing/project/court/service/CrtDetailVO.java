package com.tenniswing.project.court.service;

import lombok.Data;

@Data
public class CrtDetailVO {
	// 코트 상세 테이블
	private int crtDetailNo;
	private int crtUsePrice;
	private String useUnitTime;
	private String crtIndoorOutdoor;
	private String crtIndoorOutdoorName;
	private int crtroomNo;
	
	// 추가 등록 or 완료
	private String action;
}
