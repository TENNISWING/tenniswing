package com.tenniswing.project.community.service;

import java.util.List;
import java.util.Map;

import com.tenniswing.project.attach.service.AttachVO;

public interface SnsService {
	// 전체조회
	public List<SnsVO> selectAllSnsInfo(SnsVO snsVO);
	// 회원당 그룹 조회
	public List<SnsVO> selectGroup(SnsVO snsVO);
	// 내 그룹 리스트(sns, snsgroup, like 다 가지고 있음)
	public List<SnsVO> selectMyGroup(SnsVO snsVO);

	// 단건조회
	public SnsVO selectSnsInfo(SnsVO snsVO);
	// 좋아요번호 단건조회 (회원별 게시글 당)
	public SnsVO selectLikeNo(SnsVO snsVO);
	
	// 등록
		public int insertSns(SnsVO snsVO);
		// 그룹등록
		public int insertSnsGrp(SnsVO snsVO);
		// 좋아요 등록
		public int insertLike(SnsVO snsVO);
		// 스크랩 등록
		public int insertSnsScrap(SnsVO snsVO);
		
		
	// 수정
	public Map<String, Object> updateSns(SnsVO snsVO);
//	public Map<String, Object> updateGrp(SnsVO snsVO);
	
	// 삭제
	public boolean deleteSns(int snsWrtNo );
	
	// 좋아요 삭제
	public boolean deleteLike(int likeNo);
	
	//sns 첨부파일 조회
	public List<AttachVO> attachListAllSns();

	//public List<SnsVO> selectAllSnsInfo(SnsVO snsVO);

}
