package com.tenniswing.project.match.service;

import java.util.List;

public interface MatchHistService {
	//전체
		public List<MatchHistVO> selectAllMatchHist();
		
		//단건
		public MatchHistVO selectMatchHist(MatchHistVO matchHistVO);
		
		//등록
		public int insertMatchHist(MatchHistVO matchHistVO);
		
		//수정
		public int updateMatchHist(MatchHistVO matchHistVO);
		
		//삭제
		public int deleteMatchHist(MatchHistVO matchHistVO);
}
