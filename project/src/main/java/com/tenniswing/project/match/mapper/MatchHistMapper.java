package com.tenniswing.project.match.mapper;

import java.util.List;

import com.tenniswing.project.match.service.MatchHistVO;

public interface MatchHistMapper {
	//전체
	public List<MatchHistVO> selectAllMatchHist();
	public List<MatchHistVO> selectAllClubMatchHist();
	public List<MatchHistVO> selectAllContMatchHist();
	public List<MatchHistVO> selectAllStarterMatchHist();
	
	//단건
	public MatchHistVO selectMatchHist(MatchHistVO matchHistVO);
	public MatchHistVO selectClubMatchHist(MatchHistVO matchHistVO);
	public MatchHistVO selectContMatchHist(MatchHistVO matchHistVO);
	public MatchHistVO selectStarterMatchHist(MatchHistVO matchHistVO);
	
	//등록
	public int insertMatchHist(MatchHistVO matchHistVO);
	public int insertClubMatchHist(MatchHistVO matchHistVO);
	public int insertContMatchHist(MatchHistVO matchHistVO);
	public int insertStarterMatchHist(MatchHistVO matchHistVO);
	
	//수정
	public int updateMatchHist(MatchHistVO matchHistVO);
	public int updateClubMatchHist(MatchHistVO matchHistVO);
	public int updateContMatchHist(MatchHistVO matchHistVO);
	public int updateStarterMatchHist(MatchHistVO matchHistVO);
	
	//삭제
	public int deleteMatchHist(MatchHistVO matchHistVO);
	public int deleteClubMatchHist(MatchHistVO matchHistVO);
	public int deleteContMatchHist(MatchHistVO matchHistVO);
	public int deleteStarterMatchHist(MatchHistVO matchHistVO);
}
