package com.tenniswing.project.club.service;

import java.util.List;

public interface ClubService {
	//전체조회
	public List<ClubVO> selectAllClub();
	
	//등록
	public int isnertClub(ClubVO clubVO);
}
