package com.tenniswing.project.match.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenniswing.project.court.service.CrtroomVO;
import com.tenniswing.project.match.mapper.MatchMapper;
import com.tenniswing.project.match.service.MatchService;
import com.tenniswing.project.match.service.MatchVO;


@Service
public class MatchServiceImpl implements MatchService {

	@Autowired MatchMapper matchMapper;

	@Override
	public List<MatchVO> selectAllMatch(MatchVO matchVO) {
		return matchMapper.selectAllMatch(matchVO);
	}

	@Override
	public List<MatchVO> selectAllClubMatch(MatchVO matchVO) {
		return matchMapper.selectAllClubMatch(matchVO);
	}

	@Override
	public List<MatchVO> selectAllContMatch(MatchVO matchVO) {
		return matchMapper.selectAllContMatch(matchVO);
	}

	@Override
	public List<MatchVO> selectAllStarterMatch(MatchVO matchVO) {
		return matchMapper.selectAllStarterMatch(matchVO);
	}

	@Override
	public MatchVO selectMatch(MatchVO matchVO) {
		return matchMapper.selectMatch(matchVO);
	}

	@Override
	public MatchVO selectClubMatch(MatchVO matchVO) {
		return matchMapper.selectClubMatch(matchVO);
	}

	@Override
	public MatchVO selectContMatch(MatchVO matchVO) {
		return matchMapper.selectContMatch(matchVO);
	}

	@Override
	public MatchVO selectStarterMatch(MatchVO matchVO) {
		return matchMapper.selectStarterMatch(matchVO);
	}

	@Override
	public int insertMatch(MatchVO matchVO) {
		return matchMapper.insertMatch(matchVO);
	}
	
	@Override
	public int insertHistMatch(MatchVO matchVO) {
		return matchMapper.insertHistMatch(matchVO);
	}	

	@Override
	public int insertClubMatch(MatchVO matchVO) {
		return matchMapper.insertClubMatch(matchVO);
	}

	@Override
	public int insertContMatch(MatchVO matchVO) {
		return matchMapper.insertContMatch(matchVO);
	}

	@Override
	public int insertStarterMatch(MatchVO matchVO) {
		return matchMapper.insertStarterMatch(matchVO);
	}

	@Override
	public int updateMatch(MatchVO matchVO) {
		return matchMapper.updateMatch(matchVO);
	}

	@Override
	public int updateClubMatch(MatchVO matchVO) {
		return matchMapper.updateClubMatch(matchVO);
	}

	@Override
	public int updateContMatch(MatchVO matchVO) {
		return matchMapper.updateContMatch(matchVO);
	}

	@Override
	public int updateStarterMatch(MatchVO matchVO) {
		return matchMapper.updateStarterMatch(matchVO);
	}

	@Override
	public int deleteMatch(MatchVO matchVO) {
		return matchMapper.deleteMatch(matchVO);
	}

	@Override
	public int deleteClubMatch(MatchVO matchVO) {
		return matchMapper.deleteClubMatch(matchVO);
	}

	@Override
	public int deleteContMatch(MatchVO matchVO) {
		return matchMapper.deleteContMatch(matchVO);
	}

	@Override
	public int deleteStarterMatch(MatchVO matchVO) {
		return matchMapper.deleteStarterMatch(matchVO);
	}

	@Override
	public int selectCount(MatchVO matchVO) {
		return matchMapper.selectCount(matchVO);
	}

	@Override
	public int selectClubCount(MatchVO matchVO) {
		return matchMapper.selectClubCount(matchVO);
	}

	@Override
	public int selectContCount(MatchVO matchVO) {
		return matchMapper.selectContCount(matchVO);
	}

	@Override
	public int selectStarterCount(MatchVO matchVO) {
		return matchMapper.selectStarterCount(matchVO);
	}

	@Override
	public int searchCourtList(CrtroomVO crtroomVO) {
		return 0;
	}

	@Override
	public int insertMatchHist(MatchVO matchVO) {
		return matchMapper.insertMatchHist(matchVO);
	}
	
}
