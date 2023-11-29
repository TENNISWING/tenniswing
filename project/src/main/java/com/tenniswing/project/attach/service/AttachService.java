package com.tenniswing.project.attach.service;

import java.util.List;

public interface AttachService {
	
	//전체조회
	public List<AttachVO> attachList(AttachVO attachVO);
	
	//전체등록
	public int saveAttach( String tableDiv, int tablePk, List<AttachVO> files );
}
