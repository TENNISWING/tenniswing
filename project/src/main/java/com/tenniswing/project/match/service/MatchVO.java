package com.tenniswing.project.match.service;

import java.util.Date;

import lombok.Data;

@Data
public class MatchVO {
	private int matchNo;
	private String matchMemNo;
	private Date matchDate;
	private Date matchTime;
	private String matchGateWay;
	private String matchProgState;
	private int crtNo;
	private String matchNtrp;
	private String matchLv;
	private String matchGen;
	private int matchCrtUseAmt;
	private int matchRcrPsnNum;
	private String matchDetailCtt;
}
