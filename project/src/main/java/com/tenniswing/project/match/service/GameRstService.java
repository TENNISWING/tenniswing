package com.tenniswing.project.match.service;

import java.util.List;

import com.tenniswing.project.match.mapper.GameRstMapper;

public interface GameRstService {
	//전체
			public List<GameRstMapper> selectAllGameRst();
			
			//단건
			public GameRstMapper selectGameRst(GameRstMapper gameRstVO);
			
			//등록
			public int insertGameRst(GameRstMapper gameRstVO);
			
			//수정
			public int updateGameRst(GameRstMapper gameRstVO);
			
			//삭제
			public int deleteGameRst(GameRstMapper gameRstVO);
}
