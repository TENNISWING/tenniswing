package com.tenniswing.project.member.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class MemberVO {
	private int memNo;
	private String memId;
	private String name;
	private String pwd;
	private String pwdUpdate;
	private String phoneNo;
	private String memDiv;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date joinDate;
	private String state;
	private String div;

	// profile
	private String nick;
	private String age;
	private String lv;
	private String region;
	private String gen;
	private String ntrp;
	private String preferGameWay;
	private String preferAge;
	private int crtroomNo;
	private String crtroomName;
	private String prImage;
	private int mannerPoint;
		
	// host
	private String hostBank;
	private String hostActNo;
	private String hostDepositor;
	private String hostBusinessRegiNo;
	
	//attach
	private List<MultipartFile> files = new ArrayList<>();
	private String attachPath;
}
