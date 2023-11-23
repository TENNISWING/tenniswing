package com.tenniswing.project.match.mapper;

import java.util.List;

import com.tenniswing.project.match.service.GameRstVO;

public interface GameRstMapper {
	//전체
		public List<GameRstVO> selectAllGameRst();
		
		//단건
		public GameRstVO selectGameRst(GameRstVO gameRstVO);
		
		//등록
		public int insertGameRst(GameRstVO gameRstVO);
		
		//수정
		public int updateGameRst(GameRstVO gameRstVO);
		
		//삭제
		public int deleteGameRst(GameRstVO gameRstVO);
}
