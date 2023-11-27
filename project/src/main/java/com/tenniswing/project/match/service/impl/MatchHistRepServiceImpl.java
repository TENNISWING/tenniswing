package com.tenniswing.project.match.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenniswing.project.match.mapper.MatchHistRepMapper;
import com.tenniswing.project.match.service.MatchHistRepService;
import com.tenniswing.project.match.service.MatchHistRepVO;


@Service
public class MatchHistRepServiceImpl implements MatchHistRepService {
	
	@Autowired MatchHistRepMapper matchHistRepMapper;

	@Override
	public List<MatchHistRepVO> selectAllMatchHistRep() {
		return matchHistRepMapper.selectAllMatchHistRep();
	}

	@Override
	public MatchHistRepVO selectMatchHistRep(MatchHistRepVO matchHistRepVO) {
		return matchHistRepMapper.selectMatchHistRep(matchHistRepVO);
	}

	@Override
	public int insertMatchHistRep(MatchHistRepVO matchHistRepVO) {
		return matchHistRepMapper.insertMatchHistRep(matchHistRepVO);
	}

	@Override
	public int updateMatchHistRep(MatchHistRepVO matchHistRepVO) {
		return matchHistRepMapper.updateMatchHistRep(matchHistRepVO);
	}

	@Override
	public int deleteMatchHistRep(MatchHistRepVO matchHistRepVO) {
		return matchHistRepMapper.deleteMatchHistRep(matchHistRepVO);
	}
		
	}

