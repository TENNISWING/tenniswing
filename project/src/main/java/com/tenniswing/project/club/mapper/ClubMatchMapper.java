package com.tenniswing.project.club.mapper;

import java.util.HashMap;
import java.util.List;

import com.tenniswing.project.club.service.ClubVO;
import com.tenniswing.project.match.service.MatchVO;

public interface ClubMatchMapper {
	
	    //매치 모집 등록
		public int insertMatchRecruit(ClubVO clubVO);
		
		//매치 모집 전체조회
		public List<ClubVO> selectAllMatchRecruit(ClubVO clubVO);
		
		//매치 모집 멤버추가
		public int insertRecruitMem(ClubVO clubVO);
		
		//매치 결과 입력
		public int insertResult(ClubVO clubVO);
		
		//매치 모집 삭제
		public int clubRecDelete(HashMap<String, Long> map);
		
		//매치 결과 수정
		public int editResult(ClubVO clubVO);
}
