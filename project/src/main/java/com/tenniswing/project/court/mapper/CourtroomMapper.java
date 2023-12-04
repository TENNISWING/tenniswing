package com.tenniswing.project.court.mapper;

import java.util.List;

import com.tenniswing.project.attach.service.AttachVO;
import com.tenniswing.project.court.service.CrtroomVO;

public interface CourtroomMapper {
	// 사용자
	public List<CrtroomVO> selectAllCourtroomMain();
	
	// 호스트
	// 전체조회
	public List<CrtroomVO> selectAllCourtroom(String hostId);
	
	// 단건조회
	public CrtroomVO selectCourtroom(CrtroomVO crtroomVO);
	
	// Main 코트 배너
	public List<CrtroomVO> crtroomBanner();
	
	// 최근 등록한 코트
	public List<CrtroomVO> recentRegiCourt();
	
	// 필터조회(지역, 날짜, 시간대)
	
	// 등록
	public int insertCourtroom(CrtroomVO crtroomVO);
	
	// 수정
	public int updateCourtroom(CrtroomVO crtroomVO);
	
	// 삭제
	public int deleteCourtroom(int courtroomNo);
	
	// 첨부파일
	public List<AttachVO> attachListAllCourt();
	
	//코트검색
	public List<CrtroomVO> courtSearch(String str);
}
