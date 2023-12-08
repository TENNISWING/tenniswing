package com.tenniswing.project.club.service;

import java.util.List;
import java.util.Map;

public interface ClubService {
	//전체조회
	public List<ClubVO> selectAllClub(ClubVO clubVO);
	
	//단건조회
	public ClubVO selectClub(ClubVO clubVO);
	//public Map<String, Object> selectClub1(ClubVO clubVO);
	public List<ClubVO> selectclubMem(ClubVO clubVO);
	
	//등록
	public int insertClub(ClubVO clubVO);
	
	//삭제
	public boolean deleteClub(int ClubNo);
	
	//수정
	public Map<String, Object> updateClub(ClubVO clubVO);
	
	
	
	//회원 가입 신청(등록)
	public int insertClubMem(ClubVO clubVO);
	
	//회원 리스트(전체조회)
    //public List<ClubVO> selectAllClubMem(ClubVO clubVO);
	

	//재은
	public ClubVO selectMatchClub(ClubVO clubVO);
	
	//클럽 마이페이지
	public List<Map<String, Object>> selectAllMyClub(String memId);

}
