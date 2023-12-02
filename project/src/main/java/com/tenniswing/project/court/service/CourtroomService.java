package com.tenniswing.project.court.service;

import java.util.List;
import java.util.Map;

import com.tenniswing.project.attach.service.AttachVO;

public interface CourtroomService {
	// 사용자
	public List<CrtroomVO> selectAllCourtroomMain();
	
	// 호스트
	// 전체조회
	public List<CrtroomVO> selectAllCourtroom(String hostId);
	
	// 상세조회
	public CrtroomVO selectCourtroom(CrtroomVO crtroomVO);
	
	// 등록
	public int insertCourtroom(CrtroomVO crtroomVO);
	
	// 수정
	public Map<String, Object> updateCourtroom(CrtroomVO crtroomVO);
	
	// 삭제
	public boolean deleteCourtroom(int crtroomNo);
}
