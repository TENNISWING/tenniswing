package com.tenniswing.project.match.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenniswing.project.club.mapper.ClubMapper;
import com.tenniswing.project.match.mapper.MatchHistMapper;
import com.tenniswing.project.match.service.MatchHistService;
import com.tenniswing.project.match.service.MatchHistVO;

@Service
public class MatchHistServiceImpl implements MatchHistService {

	@Autowired MatchHistMapper matchHistMapper;
	@Autowired ClubMapper clubMapper;
	
	@Override
	public List<MatchHistVO> selectAllMatchHist() {
		return matchHistMapper.selectAllMatchHist();
	}

	@Override
	public MatchHistVO selectMatchHist(MatchHistVO matchHistVO) {
		return matchHistMapper.selectMatchHist(matchHistVO);
	}

	@Override
	public int insertMatchHist(MatchHistVO matchHistVO) {
		return matchHistMapper.insertMatchHist(matchHistVO);
	}

	@Override
	public int insertClubMatchHist(MatchHistVO matchHistVO) {
		return matchHistMapper.insertClubMatchHist(matchHistVO);
	}
	
	@Override
	public int insertStarterMatchHist(MatchHistVO matchHistVO) {
		return matchHistMapper.insertStarterMatchHist(matchHistVO);
	}
	
	@Override
	public int updateMatchHist(MatchHistVO matchHistVO) {
		return matchHistMapper.updateMatchHist(matchHistVO);
	}

	@Override
	public int deleteMatchHist(MatchHistVO matchHistVO) {
		return matchHistMapper.deleteMatchHist(matchHistVO);
	}

	@Override
	public MatchHistVO selectClubMatchHist(MatchHistVO matchHistVO) {
		return matchHistMapper.selectClubMatchHist(matchHistVO);
	}

	@Override
	public int updateStarterMatchHist(MatchHistVO matchHistVO) {
		return matchHistMapper.updateStarterMatchHist(matchHistVO);
	}

	@Override
	public int insertContMatchHist(MatchHistVO matchHistVO) {
		return matchHistMapper.insertContMatchHist(matchHistVO);
	}

	

}
