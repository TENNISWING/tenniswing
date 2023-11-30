package com.tenniswing.project.community.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.repository.query.Param;

public interface SnsService {
	// 전체조회
	public List<SnsVO> selectAllSnsInfo();
	
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
		
	// 삭제
	public int deleteSns(@Param("snsWrtNo")int snsWrtNo );
	
	// 좋아요 삭제
	public boolean deleteLike(int likeNo);

}
