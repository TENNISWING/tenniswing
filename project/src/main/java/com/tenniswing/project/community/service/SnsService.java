package com.tenniswing.project.community.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.repository.query.Param;

public interface SnsService {
	// 전체조회
	public List<SnsVO> selectAllSnsInfo();
	
	// 단건조회
	public SnsVO selectSnsInfo(SnsVO snsVO);
	
	// 등록
		public int insertSns(SnsVO snsVO);
		// 그룹등록
		public int insertSnsGrp(SnsVO snsVO);
		// 좋아요 등록
		public int insertSnsLike(SnsVO snsVO);
		// 스크랩 등록
		public int insertSnsScrap(SnsVO snsVO);
		
		
	// 수정
	public Map<String, Object> updateSns(SnsVO snsVO);
		
	// 삭제
	public int deleteSns(@Param("snsWrtNo")int snsWrtNo );

}
