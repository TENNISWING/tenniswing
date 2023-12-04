package com.tenniswing.project.court.service;

import java.util.List;
import java.util.Map;

import com.tenniswing.project.attach.service.AttachVO;

public interface CrtDetailService {
	// 조회
	public CrtDetailVO selectCrtDetail(CrtDetailVO crtDetailVO);
	
	// 코트 상세 번호로 조회
	public CrtDetailVO selectCrtDetailNo(int crtDetailNo);
	
	// 환불안내
	public CrtDetailVO refundInf();
	
	// 등록
	public int insertCrtDetail(CrtDetailVO crtDetailVO, List<AttachVO> attach);
	
	// 수정
	public Map<String, Object> updateCrtDetail(CrtDetailVO crtDetailVO);
	
	// 삭제
	public boolean deleteCrtDetail(int crtDetailNo);
	
}
