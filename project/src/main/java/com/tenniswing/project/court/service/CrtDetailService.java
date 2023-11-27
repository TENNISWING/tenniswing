package com.tenniswing.project.court.service;

import java.util.Map;

public interface CrtDetailService {
	// 조회
	public CrtDetailVO selectCrtDetail(CrtDetailVO crtDetailVO);
	
	// 등록
	public int insertCrtDetail(CrtDetailVO crtDetailVO);
	
	// 수정
	public Map<String, Object> updateCrtDetail(CrtDetailVO crtDetailVO);
	
	// 삭제
	public boolean deleteCrtDetail(int crtDetailNo);
}
