package com.tenniswing.project.club.service;

import java.util.List;

public interface ClubService {
	//전체조회
	public List<ClubVO> selectAllClub(ClubVO clubVO);
	
	//단건조회
	public ClubVO selectClub(ClubVO clubVO);
	
	//등록
	public int insertClub(ClubVO clubVO);
}
