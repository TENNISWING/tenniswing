package com.tenniswing.project.club.mapper;

import java.util.HashMap;
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
	public int updatePost(ClubPostVO clubPostVO);
	
	//삭제
	public int deletePost(HashMap<String, Long> map);
	
	//게시글 조회수
	public void updatePostHit(int clubPostNo);
	
	//페이징 전체 갯수
	public int selectCount(ClubPostVO clubPostVO);
}
