package com.tenniswing.project.club.service;

import java.util.List;
import java.util.Map;

public interface ClubService {
	//전체조회
	public List<ClubVO> selectAllClub(ClubVO clubVO);
	
	//단건조회
	public ClubVO selectClub(ClubVO clubVO);
	
	//등록
	public int insertClub(ClubVO clubVO);
	
	//삭제
	public boolean deleteClub(int ClubNo);
	
	//수정
	public Map<String, Object> updateClub(ClubVO clubVO);
}
