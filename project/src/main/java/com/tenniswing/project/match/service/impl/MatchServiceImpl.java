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
	public MatchVO selectMatch(MatchVO matchVO) {
		return matchMapper.selectMatch(matchVO);
	}

	@Override
	public int insertMatch(MatchVO matchVO) {
		return matchMapper.insertMatch(matchVO);
	}

	@Override
	public int updateMatch(MatchVO matchVO) {
		return matchMapper.updateMatch(matchVO);
	}

	@Override
	public int deleteMatch(MatchVO matchVO) {
		return matchMapper.deleteMatch(matchVO);
	}

}
