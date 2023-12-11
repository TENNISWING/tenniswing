package com.tenniswing.project.match.mapper;

import java.util.List;
import java.util.Map;

import com.tenniswing.project.club.service.ClubVO;
import com.tenniswing.project.match.service.MatchVO;

public interface MatchMapper {
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
	
	//이력 테이블
	public int insertMatchHist(MatchVO matchVO);
	public int insertClubMatchHist(MatchVO matchVO);
	public int insertContMatchHist(MatchVO matchVO);
	public int insertStarterMatchHist(MatchVO matchVO);
	
	//최근 조회
	public List<MatchVO> matchRecentView();
	public List<MatchVO> clubRecentView();
	public List<MatchVO> contRecentView();
	public List<MatchVO> starterRecentView();
	
	public List<ClubVO> selectMyOwnerClub(String memId);
	public List<ClubVO> selectMyClub(String memId);
	
	//어드민페이지
	public List<MatchVO> matchAll();
	public List<MatchVO> matchClubAll();
	public List<MatchVO> matchConstAll();
	public List<MatchVO> matchStarterAll();	
	
	//클럽 매치 리스트(클럽페이지 내)
	public List<MatchVO> selectClubList(MatchVO matchVO);
	
	//public MatchVO matchSelectCourtroom(MatchVO matchVO);
}
