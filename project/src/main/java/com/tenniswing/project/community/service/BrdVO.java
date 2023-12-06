package com.tenniswing.project.community.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.tenniswing.project.attach.service.AttachVO;

import lombok.Data;
@Data
public class BrdVO {
	private int brdWrtNo;
	private String brdTitle;
	private String brdCtt;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date brdWriteDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date brdEditDate;
	private int brdHit;
	private String memId;
	private String name;
	private String brdDiv;
	
	//페이징
	private int page = 1;
	private int pageUnit = 10;
	
	//구분코드
	private String brdDivName;
	
	//sns 이미지 리스트
	private List<AttachVO> attachList;
	private String attachPath; 
	
	//attach
	private List<MultipartFile> files = new ArrayList<>();
		
}
