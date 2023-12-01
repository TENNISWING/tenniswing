package com.tenniswing.project.club.mapper;

import java.util.List;

import com.tenniswing.project.club.service.ClubPostVO;


public interface ClubPostMapper {

	//전체조회
	public List<ClubPostVO> selectAllPost(ClubPostVO clubPostVO);
	
	//단건조회
	public ClubPostVO selectPost(ClubPostVO clubPostVO);
	
	//필터조회
	
	//등록
	public int insertPost(ClubPostVO clubPostVO);
	
	//수정
	
	//삭제
}
