package com.tenniswing.project.match.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tenniswing.project.match.mapper.MatchMapper;
import com.tenniswing.project.match.service.MatchService;
import com.tenniswing.project.match.service.MatchVO;

public class MatchServiceImpl implements MatchService {

	@Autowired MatchMapper matchMapper;
	
	@Override
	public List<MatchVO> selectAllMatch() {
		return matchMapper.selectAllMatch();
	}

	@Override
	public MatchVO selectMatch(MatchVO matchVO) {
		return null;
	}

	@Override
	public int insertMatch(MatchVO matchVO) {
		return 0;
	}

	@Override
	public int updateMatch(MatchVO matchVO) {
		return 0;
	}

	@Override
	public int deleteMatch(MatchVO matchVO) {
		return 0;
	}

}
