package com.tenniswing.project.match.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenniswing.project.match.mapper.MatchMapper;
import com.tenniswing.project.match.service.MatchService;
import com.tenniswing.project.match.service.MatchVO;

@Service
public class MatchServiceImpl implements MatchService {

	@Autowired MatchMapper matchMapper;

	@Override
	public List<MatchVO> selectAllMatch() {
		return matchMapper.selectAllMatch();
	}

	@Override
	public List<MatchVO> selectAllClubMatch() {
		return matchMapper.selectAllClubMatch();
	}

	@Override
	public List<MatchVO> selectAllContMatch() {
		return null;
	}

	@Override
	public List<MatchVO> selectAllStarterMatch() {
		return null;
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
		return null;
	}

	@Override
	public MatchVO selectStarterMatch(MatchVO matchVO) {
		return null;
	}

	@Override
	public int insertMatch(MatchVO matchVO) {
		return matchMapper.insertMatch(matchVO);
	}

	@Override
	public int insertClubMatch(MatchVO matchVO) {
		return 0;
	}

	@Override
	public int insertContMatch(MatchVO matchVO) {
		return 0;
	}

	@Override
	public int insertStarterMatch(MatchVO matchVO) {
		return 0;
	}

	@Override
	public int updateMatch(MatchVO matchVO) {
		return matchMapper.updateMatch(matchVO);
	}

	@Override
	public int updateClubMatch(MatchVO matchVO) {
		return 0;
	}

	@Override
	public int updateContMatch(MatchVO matchVO) {
		return 0;
	}

	@Override
	public int updateStarterMatch(MatchVO matchVO) {
		return 0;
	}

	@Override
	public int deleteMatch(MatchVO matchVO) {
		return matchMapper.deleteMatch(matchVO);
	}

	@Override
	public int deleteClubMatch(MatchVO matchVO) {
		return 0;
	}

	@Override
	public int deleteContMatch(MatchVO matchVO) {
		return 0;
	}

	@Override
	public int deleteStarterMatch(MatchVO matchVO) {
		return 0;
	}
	
	
}
