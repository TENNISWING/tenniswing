package com.tenniswing.project.community.service;

import java.util.List;
import java.util.Map;

public interface BrdService {
		// 전체조회
		public List<BrdVO> selectBrdAllInfo(BrdVO brdVO);
		
		// 단건조회
		public BrdVO selectBrdInfo(BrdVO brdVO);
		
		// 등록
		public int insertBrd(BrdVO brdVO);
		
		// 수정
		public Map<String, Object> updateBrd(BrdVO brdVO);
		
		// 삭제
		public boolean deleteBrd(int brdWrtNo);
		
		// 전체페이지수 count
		public int selectCount(BrdVO brdVO);
}
