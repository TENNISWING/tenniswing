package com.tenniswing.project.match.service;

import lombok.Data;

@Data
public class GameRstVO {
	private int gameRstNo;
	private int matchNo;
	private String memNo;
	private int winpoints;
	private String gameRst;
	private String repCtt;
	private String match_part;
}
