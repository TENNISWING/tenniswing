package com.tenniswing.project.court.service;

import lombok.Data;

@Data
public class CrtDetailVO {
	// 코트 상세 테이블
	private int crtDetailNo;
	private int crtUsePrice;
	private String useUnitTime;
	private int crtIndoorOutdoor;
}
