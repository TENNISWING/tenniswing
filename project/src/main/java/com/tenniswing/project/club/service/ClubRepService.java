package com.tenniswing.project.club.service;

import java.util.List;

public interface ClubRepService {
	//전체조회
	public List<ClubRepVO> selectAllRep(ClubRepVO clubRepVO);	
		
	//댓글 등록
	public int insertRep(ClubRepVO clubRepVO);
		
	//댓글 삭제
	public boolean deleteRep(int clubRepNo);
}
