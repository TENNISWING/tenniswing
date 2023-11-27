package com.tenniswing.project;

import lombok.Data;

@Data
public class UploadFileVO {
	private int attachNo;
	private String attachExt;
	private String attachName;
	private String attachPath;
	private int attachTurn;
	private String attachTableDiv;
	private int attachTablePk;
}
