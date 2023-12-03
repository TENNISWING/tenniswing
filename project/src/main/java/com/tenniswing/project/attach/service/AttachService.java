package com.tenniswing.project.attach.service;

import java.util.List;

public interface AttachService {
	
	//전체조회
	public List<AttachVO> attachList(String tableDiv, int attachTablePk);
	
	//sns 첨부파일 조회
	public List<AttachVO> attachListAllSns();
	
	//전체등록
	public int saveAttach( String tableDiv, int tablePk, List<AttachVO> files );
	
	//업데이트
	public int updateAttach(String tableDiv, int tablePk, List<AttachVO> files);
	
	//코트 첨부파일 순서
	public int saveAttachTurn(String tableDiv, int tablePK, List<AttachVO> files);
}
