package com.tenniswing.project.match.mapper;

import java.util.List;

import com.tenniswing.project.match.service.MatchHistRepVO;
import com.tenniswing.project.match.service.MatchHistVO;

public interface MatchHistRepMapper {
		//전체
		public List<MatchHistRepVO> selectAllMatchHistRep();
		
		//단건
		public MatchHistRepVO selectMatchHistRep(MatchHistRepVO matchHistRepVO);
		
		//등록
		public int insertMatchHistRep(MatchHistRepVO matchHistRepVO);
		
		//수정
		public int updateMatchHistRep(MatchHistRepVO matchHistRepVO);
		
		//삭제
		public int deleteMatchHistRep(MatchHistRepVO matchHistRepVO);
}
