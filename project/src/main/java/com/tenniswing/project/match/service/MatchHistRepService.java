package com.tenniswing.project.match.service;

import java.util.List;

import com.tenniswing.project.match.mapper.MatchHistRepMapper;

public interface MatchHistRepService {
		//전체
			public List<MatchHistRepMapper> selectAllMatchHistRep();
			
			//단건
			public MatchHistVO selectMatchHistRep(MatchHistRepMapper matchHistRepVO);
			
			//등록
			public int insertMatchHistRep(MatchHistRepMapper matchHistRepVO);
			
			//수정
			public int updateMatchHistRep(MatchHistRepMapper matchHistRepVO);
			
			//삭제
			public int deleteMatchHistRep(MatchHistRepMapper matchHistRepVO);
}
