package com.tenniswing.project.match.service;

import java.util.List;

import com.tenniswing.project.court.service.CrtroomVO;

public interface MatchService {
	//전체조회
		public List<MatchVO> selectAllMatch(MatchVO matchVO);
		public List<MatchVO> selectAllClubMatch(MatchVO matchVO);
		public List<MatchVO> selectAllContMatch(MatchVO matchVO);
		public List<MatchVO> selectAllStarterMatch(MatchVO matchVO);	
		
		//단건조회
		public MatchVO selectMatch(MatchVO matchVO);
		public MatchVO selectClubMatch(MatchVO matchVO);
		public MatchVO selectContMatch(MatchVO matchVO);
		public MatchVO selectStarterMatch(MatchVO matchVO);
		
		//등록
		public int insertMatch(MatchVO matchVO);
		public int insertHistMatch(MatchVO matchVO);		
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
		
		//페이징
		public int selectCount(MatchVO matchVO);
		public int selectClubCount(MatchVO matchVO);
		public int selectContCount(MatchVO matchVO);
		public int selectStarterCount(MatchVO matchVO);
		
		//코트찾기
		public int searchCourtList(CrtroomVO crtroomVO);
		
		//이력 테이블
		public int insertMatchHist(MatchVO matchVO);
		
		//최근 조회
		public List<MatchVO> matchRecentView();
		public List<MatchVO> clubRecentView();
		public List<MatchVO> contRecentView();
		public List<MatchVO> starterRecentView();
}
