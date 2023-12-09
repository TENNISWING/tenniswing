package com.tenniswing.project.community.mapper;

import java.util.List;

import com.tenniswing.project.community.service.SnsRrepVO;

public interface SnsRrepMapper {
		// 전체 조회
		public List<SnsRrepVO> selectAllSnsRrep(SnsRrepVO snsRrepVO);
		
		// 단건 조회
		public SnsRrepVO selectSnsRrep(SnsRrepVO snsRrepVO);
		
		// 대댓글 등록
		public int insertSnsRrep(SnsRrepVO snsRrepVO);
		
		// 댓글 수정
		public int updateSnsRrep(SnsRrepVO snsRrepVO);
		
		// 댓글 삭제
		public int deleteSnsRrep(int snsRrepNo);
}
