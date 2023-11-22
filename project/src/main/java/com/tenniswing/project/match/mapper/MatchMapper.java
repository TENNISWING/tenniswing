package com.tenniswing.project.match.mapper;

import java.util.List;

import com.tenniswing.project.match.service.MatchVO;

public interface MatchMapper {
	//전체조회
	public List<MatchVO> selectAllMatch();
	
	//단건조회
	public MatchVO selectMatch(MatchVO matchVO);
	
	//등록
	public int insertMatch(MatchVO matchVO);
	
	//수정
	public int updateMatch(MatchVO matchVO);
	
	//삭제
	public int deleteMatch(MatchVO matchVO);
}
