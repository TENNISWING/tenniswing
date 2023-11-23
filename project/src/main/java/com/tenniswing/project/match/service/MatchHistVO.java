package com.tenniswing.project.match.service;

import java.util.Date;

import lombok.Data;

@Data
public class MatchHistVO {
	private int matchHistNo;
	private int matchNo;
	private int matchApplyMemNo;
	private Date matchApplyDate;
	private Date wrtRegiDate;
	private int matchState;
	
	private int matchApplyClubNo;
	private int acceptState;
	
	private int contMatchNo;	
}
