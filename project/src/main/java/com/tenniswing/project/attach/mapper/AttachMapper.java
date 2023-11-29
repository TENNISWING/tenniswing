package com.tenniswing.project.attach.mapper;

import java.util.List;

import com.tenniswing.project.attach.service.AttachVO;

public interface AttachMapper {
	
	//전체조회
	public List<AttachVO> attachListAll(AttachVO attachVO);
	
	//전체등록
	public int saveAttachAll(List<AttachVO> files);
}
