package com.tenniswing.project.club.mapper;

import java.util.List;

import com.tenniswing.project.club.service.ClubMatchVO;

public interface ClubMatchMapper {
	
	    //매치 모집 등록
		public int insertMatchRecruit(ClubMatchVO clubMatchVO);
		
		//매치 모집 전체조회
		public List<ClubMatchVO> selectAllMatchRecruit(ClubMatchVO clubMatcjVO);
		
}
