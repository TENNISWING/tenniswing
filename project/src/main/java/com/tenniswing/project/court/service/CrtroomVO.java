package com.tenniswing.project.court.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CrtroomVO {
	// 코트장 테이블
	private int crtroomNo;
	private String hostId;
	private String crtroomName;
	private String crtroomExp;
	private String crtroomRegion;
	private String crtroomRegionName;
	private String crtroomLocation;
	private String crtroomOperateStartTime;
	private String crtroomOperateEndTime;
	private String crtroomCaution;
	private String crtroomUseGuide;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date crtroomRegiDate;
	
	private int crtCount;
	private int minPrice;
	// 코트 상세 테이블
	private List<CrtDetailVO> crtDetailList;
	
	// 첨부파일
	
}
