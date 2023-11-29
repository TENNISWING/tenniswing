package com.tenniswing.project.court.mapper;

import java.util.List;

import com.tenniswing.project.court.service.CrtroomVO;

public interface CourtroomMapper {
	// 사용자
	public List<CrtroomVO> selectAllCourtroomMain();
	
	// 호스트
	// 전체조회
	public List<CrtroomVO> selectAllCourtroom(String hostId);
	
	// 단건조회
	public CrtroomVO selectCourtroom(CrtroomVO crtroomVO);
	
	// 필터조회(지역, 날짜, 시간대)
	
	// 등록
	public int insertCourtroom(CrtroomVO crtroomVO);
	
	// 수정
	public int updateCourtroom(CrtroomVO crtroomVO);
	
	// 삭제
	public int deleteCourtroom(int courtroomNo);
}
