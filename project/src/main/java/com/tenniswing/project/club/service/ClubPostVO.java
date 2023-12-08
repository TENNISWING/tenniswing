package com.tenniswing.project.club.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.tenniswing.project.attach.service.AttachVO;

import lombok.Data;

@Data
public class ClubPostVO {
	private int clubPostNo;
	private String clubPostTitle;
	private String clubPostCtt;
	private int clubPostHit;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date clubPostWriteDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date clubPostEditDate;
	private String memId;
	private int clubNo;
	
	//회원 이름
	private String name;
	
	//attach
	private List<MultipartFile> files = new ArrayList<>();
	private List<AttachVO> attachList;
	private String attachPath;
	
	//페이징
	private int page;
	private int rn;
	private int pageUnit=7;
		
	private String sort;
	
	
}
