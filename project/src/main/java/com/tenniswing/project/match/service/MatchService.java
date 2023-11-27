package com.tenniswing.project.match.service;

import java.util.List;

public interface MatchService {
	//전체조회
		public List<MatchVO> selectAllMatch();
		public List<MatchVO> selectAllClubMatch();
		public List<MatchVO> selectAllContMatch();
		public List<MatchVO> selectAllStarterMatch();	
		
		//단건조회
		public MatchVO selectMatch(MatchVO matchVO);
		public MatchVO selectClubMatch(MatchVO matchVO);
		public MatchVO selectContMatch(MatchVO matchVO);
		public MatchVO selectStarterMatch(MatchVO matchVO);
		
		//등록
		public int insertMatch(MatchVO matchVO);
		public int insertClubMatch(MatchVO matchVO);
		public int insertContMatch(MatchVO matchVO);
		public int insertStarterMatch(MatchVO matchVO);
		
		//수정
		public int updateMatch(MatchVO matchVO);
		public int updateClubMatch(MatchVO matchVO);
		public int updateContMatch(MatchVO matchVO);
		public int updateStarterMatch(MatchVO matchVO);
		
		//삭제
		public int deleteMatch(MatchVO matchVO);
		public int deleteClubMatch(MatchVO matchVO);
		public int deleteContMatch(MatchVO matchVO);
		public int deleteStarterMatch(MatchVO matchVO);
}
