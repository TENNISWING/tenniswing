package com.tenniswing.project.court.service;

import java.util.Map;

public interface CrtDetailService {
	// 조회
	public CrtDetailVO selectCrtDetail(CrtDetailVO crtDetailVO);
	
	// 코트 상세 번호로 조회
	public CrtDetailVO selectCrtDetailNo(int crtDetailNo);
	
	// 등록
	public int insertCrtDetail(CrtDetailVO crtDetailVO);
	
	// 수정
	public Map<String, Object> updateCrtDetail(CrtDetailVO crtDetailVO);
	
	// 삭제
	public boolean deleteCrtDetail(int crtDetailNo);
}
