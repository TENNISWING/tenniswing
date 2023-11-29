package com.tenniswing.project.attach.service;

import lombok.Builder;
import lombok.Data;

@Data
public class AttachVO {
	// 첨부파일번호
	private int attachNo;	
	// 첨부파일 확장자
	private String attachExt;	
	// 첨부파일 이름
	private String attachOriginName;	
	// 첨부파일 이름
	private String attachSaveName;	
	// 첨부파일 경로
	private String attachPath;	
	// 첨부파일 순서
	private int attachTurn;	
	// 첨부파일 테이블 구분 코드
	private String attachTableDiv;	
	// 첨부파일 테이블 PK
	private int attacTablePk;	
	//파일크기
	private long size;
	
	@Builder
	public AttachVO(String originalName, String saveName, long size, String path, String ext){
		this.attachOriginName = originalName;
        this.attachSaveName = saveName;
        this.size = size;
        this.attachPath = path;
        this.attachExt = ext;
	}
	
	 public void setPostId(int tablePk, String tableDiv ) {
	        this.attacTablePk = tablePk;
	        this.attachTableDiv = tableDiv;
	    }
	
}
