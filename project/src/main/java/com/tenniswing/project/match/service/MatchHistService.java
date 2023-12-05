package com.tenniswing.project.match.service;

import java.util.List;

public interface MatchHistService {
		//전체
		public List<MatchHistVO> selectAllMatchHist();
		
		//단건
		public MatchHistVO selectMatchHist(MatchHistVO matchHistVO);
		
		//클럽단건
		public MatchHistVO selectClubMatchHist(MatchHistVO matchHistVO);
		
		//등록
		public int insertMatchHist(MatchHistVO matchHistVO);
		public int insertClubMatchHist(MatchHistVO matchHistVO);

		public int insertStarterMatchHist(MatchHistVO matchHistVO);
		
		//수정
		public int updateMatchHist(MatchHistVO matchHistVO);
		
		//삭제
		public int deleteMatchHist(MatchHistVO matchHistVO);


		
}
