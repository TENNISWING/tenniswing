package com.tenniswing.project.attach.service;

import lombok.Data;

@Data
public class AttachVO {
	// 첨부파일번호
	private int attachNo;
	
	// 첨부파일 확장자
	private String attachExt;
	
	// 첨부파일 이름
	private String attachName;
	
	// 첨부파일 경로
	private String attachPath;
	
	// 첨부파일 순서
	private int attachTurn;
	
	// 첨부파일 테이블 구분 코드
	private String attachTableDiv;
	
	// 첨부파일 테이블 PK
	private int attacTablePk;
}
