package com.tenniswing.project.match.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenniswing.project.match.mapper.GameRstMapper;
import com.tenniswing.project.match.service.GameRstService;
import com.tenniswing.project.match.service.GameRstVO;

@Service
public class GameRstServiceImpl implements GameRstService {
	
	@Autowired GameRstMapper gameRstMapper;

	@Override
	public List<GameRstVO> selectAllGameRst() {
		return gameRstMapper.selectAllGameRst();
	}

	@Override
	public GameRstVO selectGameRst(GameRstVO gameRstVO) {
		return gameRstMapper.selectGameRst(gameRstVO);
	}

	@Override
	public int insertGameRst(GameRstVO gameRstVO) {
		return gameRstMapper.insertGameRst(gameRstVO);
	}

	@Override
	public int updateGameRst(GameRstVO gameRstVO) {
		return gameRstMapper.updateGameRst(gameRstVO);
	}

	@Override
	public int deleteGameRst(GameRstVO gameRstVO) {
		return gameRstMapper.deleteGameRst(gameRstVO);
	}
	

}
