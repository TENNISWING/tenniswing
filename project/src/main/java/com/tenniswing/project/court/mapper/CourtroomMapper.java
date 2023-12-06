package com.tenniswing.project.court.mapper;

import java.util.List;
import java.util.Map;

import com.tenniswing.project.attach.service.AttachVO;
import com.tenniswing.project.community.service.BrdVO;
import com.tenniswing.project.court.service.CrtroomVO;
import com.tenniswing.project.member.service.MemberVO;

public interface CourtroomMapper {
	// 사용자
	public List<CrtroomVO> selectAllCourtroomMain(CrtroomVO crtroomVO);
	
	// 호스트
	// 전체조회
	public List<CrtroomVO> selectAllCourtroom(String hostId);
	
	// 단건조회
	public CrtroomVO selectCourtroom(CrtroomVO crtroomVO);
	
	// 최근 등록한 코트
	public List<CrtroomVO> recentRegiCourt();
	
	// 인근 코트장
	public List<CrtroomVO> nearCrtroom(CrtroomVO crtroomVO);
	
	// 호스트 정보
	public MemberVO selectCrtDetailHost(String hostId);
	
	// 필터조회(지역, 날짜, 시간대)
	
	// 등록
	public int insertCourtroom(CrtroomVO crtroomVO);
	
	// 수정
	public int updateCourtroom(CrtroomVO crtroomVO);
	
	// 삭제
	public int deleteCourtroom(int courtroomNo);
	
	// 첨부파일
	public List<AttachVO> attachListAllCourt();
	
	// 코트검색
	public List<CrtroomVO> courtSearch(String str);
	
	// 코트 후기 조회
	public List<CrtroomVO> selectCourtReview(int crtroomNo);
	
	// 코트 후기 단건조회
	public CrtroomVO selectReview(int reviewNo);
	
	// 코트 후기 등록
	public int insertCourtReview(CrtroomVO crtroomVO);
	
	// 후기 등록 권한여부
	public Integer confirmInsertReview(CrtroomVO crtroomVO);
	
	// 후기 삭제
	public int deleteReview(int reviewNo);
	
	// 후기 사진 삭제
	public int deleteReviewImg(int reviewNo);
	
	// 별점 및 후기수 계산
	public CrtroomVO crtroomStar(int crtroomNo);
	
	// 전체페이지수 count
	public int selectCount(CrtroomVO crtroomVO);
}
