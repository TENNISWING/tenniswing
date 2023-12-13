package com.tenniswing.project.community.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.tenniswing.project.attach.service.AttachVO;

import lombok.Data;

@Data
public class SnsVO {
	private int snsWrtNo;
	private String snsTitle;
	private String snsCtt; // sns 내용
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date snsWriteDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date snsEditDate;
	private String snsTag;
	private int snsHit;
	private String memNo;
	private String memId;
	private String name;
	private Integer snsGrpNo;
	private int test;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date likeInDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date scrapInDate;
	
	//sns 좋아요
	private Integer likeNo;
	private int likeCnt; //좋아요 수 count
	
	//sns 그룹
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date grpCreateDate;
	private int postNum;
	private String grpName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date grpEditDate;

	//sns 이미지 리스트
	private List<AttachVO> attachList;
	private String attachPath; 
	
	// sns 댓글 리스트
	//private List<SnsRepVO> snsRepList;
	
	//attach
	private List<MultipartFile> files = new ArrayList<>();
	
	//sns 스크랩
	private Integer scrapNo;
	
	//페이지
	private int page;
	private int rn;
	private int pageUnit=5;


}
