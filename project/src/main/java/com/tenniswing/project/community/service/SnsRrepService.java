package com.tenniswing.project.community.service;

import java.util.List;
import java.util.Map;

public interface SnsRrepService {
	// 전체 조회
	public List<SnsRrepVO> selectAllSnsRrep(SnsRrepVO snsRrepVO);
	
	// 단건 조회
	public SnsRrepVO selectSnsRrep(SnsRrepVO snsRrepVO);
	
	// 대댓글 등록
	public int insertSnsRrep(SnsRrepVO snsRrepVO);
		
	// 댓글 수정
	public Map<String, Object> updateSnsRrep(SnsRrepVO snsRrepVO);
	
	// 댓글 삭제
	public boolean deleteSnsRrep(int snsRrepNo);
}
