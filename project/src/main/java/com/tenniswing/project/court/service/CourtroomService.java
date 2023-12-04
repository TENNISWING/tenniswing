package com.tenniswing.project.court.service;

import java.util.List;
import java.util.Map;

import com.tenniswing.project.attach.service.AttachVO;
import com.tenniswing.project.member.service.MemberVO;

public interface CourtroomService {
	// 사용자
	public List<CrtroomVO> selectAllCourtroomMain();
	
	// 호스트
	// 전체조회
	public List<CrtroomVO> selectAllCourtroom(String hostId);
	
	// 상세조회
	public CrtroomVO selectCourtroom(CrtroomVO crtroomVO);
	
	// 최근 등록한 코트
	public List<CrtroomVO> recentRegiCourt();
	
	// 인근 코트장
	public List<CrtroomVO> nearCrtroom(CrtroomVO crtroomVO);
	
	// 호스트 정보
	public MemberVO selectCrtDetailHost(String hostId);
	
	// 등록
	public int insertCourtroom(CrtroomVO crtroomVO, List<AttachVO> files);
	
	// 수정
	public Map<String, Object> updateCourtroom(CrtroomVO crtroomVO);
	
	// 삭제
	public boolean deleteCourtroom(int crtroomNo);
	
	// 코트검색	
	public List<CrtroomVO> courtSearch(String str);
	
	// 코트 후기 조회
	public List<CrtroomVO> selectCourtReview(int crtroomNo);
	
	// 코트 후기 등록
	public int insertCourtReview(CrtroomVO crtroomVO);
}
