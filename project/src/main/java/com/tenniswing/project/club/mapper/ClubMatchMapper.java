package com.tenniswing.project.club.mapper;

import java.util.List;

import com.tenniswing.project.club.service.ClubVO;

public interface ClubMatchMapper {
	
	    //매치 모집 등록
		public int insertMatchRecruit(ClubVO clubVO);
		
		//매치 모집 전체조회
		public List<ClubVO> selectAllMatchRecruit(ClubVO clubVO);
		
		//매치 모집 멤버추가
		public int insertRecruitMem(ClubVO clubVO);
}
