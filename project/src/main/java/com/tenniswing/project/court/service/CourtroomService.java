package com.tenniswing.project.court.service;

import java.util.List;
import java.util.Map;

public interface CourtroomService {
	// 전체조회
	public List<CrtroomVO> selectAllCourtroom();
	
	// 상세조회
	public CrtroomVO selectCourtroom(CrtroomVO crtroomVO);
	
	// 등록
	public int insertCourtroom(CrtroomVO crtroomVO);
	
	// 수정
	public Map<String, Object> updateCourtroom(CrtroomVO crtroomVO);
}
