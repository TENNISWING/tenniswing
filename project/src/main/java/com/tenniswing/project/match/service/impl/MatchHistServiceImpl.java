package com.tenniswing.project.match.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tenniswing.project.match.mapper.MatchHistMapper;
import com.tenniswing.project.match.service.MatchHistService;
import com.tenniswing.project.match.service.MatchHistVO;

public class MatchHistServiceImpl implements MatchHistService {

	@Autowired MatchHistMapper matchHistMapper;
	
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
	public int updateMatchHist(MatchHistVO matchHistVO) {
		return matchHistMapper.updateMatchHist(matchHistVO);
	}

	@Override
	public int deleteMatchHist(MatchHistVO matchHistVO) {
		return matchHistMapper.deleteMatchHist(matchHistVO);
	}

}
