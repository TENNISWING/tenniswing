package com.tenniswing.project.community.mapper;

import java.util.List;

import com.tenniswing.project.community.service.SnsRepVO;

public interface SnsRepMapper {
	// 전체 조회
		public List<SnsRepVO> selectAllSnsRep(SnsRepVO snsRepVO);
		// 단건 조회
		public SnsRepVO selectSnsRep(SnsRepVO snsRepVO);
		// 댓글 등록
		public int insertSnsRep(SnsRepVO snsRepVO);
		// 대댓글 등록
		public int insertSnsRrep(SnsRepVO snsRepVO);
		
		// 댓글 수정
		public int updateSnsRep(SnsRepVO snsRepVO);
		// 댓글 삭제
		public int deleteSnsRep(int repNo);
}
