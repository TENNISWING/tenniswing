package com.tenniswing.project.match.service;

import java.util.List;
import java.util.Map;

public interface MatchHistService {
		//전체
		public List<MatchHistVO> selectAllMatchHist();
		
		//단건
		public MatchHistVO selectMatchHist(MatchHistVO matchHistVO);
		
		//클럽단건
		public MatchHistVO selectClubMatchHist(MatchHistVO matchHistVO);
		
		//신청
		public int insertMatchHist(MatchHistVO matchHistVO);
		public int insertClubMatchHist(MatchHistVO matchHistVO);
		public int insertContMatchHist(MatchHistVO matchHistVO);		
		public int insertStarterMatchHist(MatchHistVO matchHistVO);
		
		//수정
		public int updateMatchHist(MatchHistVO matchHistVO);
		public int updateStarterMatchHist(MatchHistVO matchHistVO);
		
		//삭제
		public int deleteMatchHist(MatchHistVO matchHistVO);


		//마이페이지
		public List<Map<String, Object>> selectAllMyMatchHist(String memId);
		
}
