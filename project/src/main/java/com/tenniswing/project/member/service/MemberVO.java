package com.tenniswing.project.member.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MemberVO {
	private int memNo;
	private String memId;
	private String name;
	private String pwd;
	private String phoneNo;
	private String memDiv;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date joinDate;

	// profile
	private String nick;
	private String age;
	private String lv;
	private String region;
	private String gen;
	private String ntrp;
	private String preferGameWay;
	private String preferAge;
	private Integer crtroomNo;
	private String prImage;
	private int mannerPoint;
		
	// host
	private String hostBank;
	private String hostActNo;
	private String hostDepositor;
	private String hostBusinessRegiNo;
}
