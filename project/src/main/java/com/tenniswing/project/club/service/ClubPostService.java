package com.tenniswing.project.club.service;

import java.util.List;

public interface ClubPostService {

	//게시글 전체조회
	public List<ClubPostVO> selectAllPost(ClubPostVO clubPostVO);
	
	//게시글 단건조회
	public ClubPostVO selectPost(ClubPostVO clubPostVO);
	
	//게시글 등록
	public int insertPost(ClubPostVO clubPostVO);
	
	
}
