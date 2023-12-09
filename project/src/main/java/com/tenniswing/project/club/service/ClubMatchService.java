package com.tenniswing.project.club.service;

import java.util.List;

public interface ClubMatchService {

	//매치 모집 등록
	public int insertMatchRecruit(ClubMatchVO clubMatchVO);
	
	//매치 모집 전체조회
	public List<ClubMatchVO> selectAllMatchRecruit(ClubMatchVO clubMatcjVO);
	
	
	
	
}
