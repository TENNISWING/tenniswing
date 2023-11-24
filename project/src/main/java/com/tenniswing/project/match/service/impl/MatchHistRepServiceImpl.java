package com.tenniswing.project.match.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tenniswing.project.match.mapper.MatchHistRepMapper;
import com.tenniswing.project.match.service.MatchHistRepService;
import com.tenniswing.project.match.service.MatchHistVO;

public class MatchHistRepServiceImpl implements MatchHistRepService {

	@Autowired MatchHistRepMapper matchHistRepMapper;
	
	@Override
	public List<MatchHistRepMapper> selectAllMatchHistRep() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MatchHistVO selectMatchHistRep(MatchHistRepMapper matchHistRepVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertMatchHistRep(MatchHistRepMapper matchHistRepVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateMatchHistRep(MatchHistRepMapper matchHistRepVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMatchHistRep(MatchHistRepMapper matchHistRepVO) {
		// TODO Auto-generated method stub
		return 0;
	}

}
