package com.tenniswing.project.community.service;

import java.util.List;
import java.util.Map;

public interface SnsRepService {
	// 전체 조회
	public List<SnsRepVO> selectAllSnsRep(SnsRepVO snsRepVO);
	// 단건 조회
	public SnsRepVO selectSnsRep(SnsRepVO snsRepVO);
	// 댓글 등록
	public int insertSnsRep(SnsRepVO snsRepVO);
	// 대댓글 등록
	public int insertSnsRrep(SnsRepVO snsRepVO);
	
	// 댓글 수정
	public Map<String, Object> updateSnsRep(SnsRepVO snsRepVO);
	
	// 댓글 삭제
	public boolean deleteSnsRep(int repNo);
}
