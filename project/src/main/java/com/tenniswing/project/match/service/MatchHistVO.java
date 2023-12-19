package com.tenniswing.project.match.service;

import java.util.Date;

import lombok.Data;

@Data
public class MatchHistVO {
	private int matchHistNo;
	private int matchNo;
	private int clubNo;
	private String crtroomName;
	private String openName;
	private String depenName;
	private String memId;
	private String mhMemId;
	private Date matchApplyDate;
	private Date wrtRegiDate;
	private String matchState;
	private Date matchDate;
	
	private int matchApplyClubNo;
	private int acceptState;
	
	private int contMatchNo;	
}
