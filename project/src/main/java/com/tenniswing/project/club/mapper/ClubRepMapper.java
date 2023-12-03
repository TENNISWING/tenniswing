package com.tenniswing.project.club.mapper;

import java.util.List;

import com.tenniswing.project.club.service.ClubRepVO;

public interface ClubRepMapper {
	//전체조회
	public List<ClubRepVO> selectAllRep(ClubRepVO clubRepVO);	
	
	//댓글 등록
	public int insertRep(ClubRepVO clubRepVO);
	
	//댓글 삭제
	public int deleteRep(int clubRepNo);
}
