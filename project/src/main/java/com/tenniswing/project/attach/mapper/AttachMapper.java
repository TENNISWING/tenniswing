package com.tenniswing.project.attach.mapper;

import java.util.List;

import com.tenniswing.project.attach.service.AttachVO;

public interface AttachMapper {
	
	//전체조회
	public List<AttachVO> attachListAll(String attachTableDiv, int attachTablePk);
	
//	sns 첨부파일 
	public List<AttachVO> attachListAllSns();
	
	//전체등록
	public int saveAttachAll(List<AttachVO> files);
	
	public int updateAttach(List<AttachVO> files);
	
	// 코트 순서 등록
	public int saveAttachTurn(List<AttachVO> files);
	
	// sns 첨부파일 삭제
	//public int deleteAttachSns(int attachTablePk);
}
