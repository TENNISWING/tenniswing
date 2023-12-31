package com.tenniswing.project.club.service;

import java.util.List;
import java.util.Map;
import java.util.Map;
import java.util.Map;
import java.util.Map;

public interface ClubPostService {

	//게시글 전체조회
	public List<ClubPostVO> selectAllPost(ClubPostVO clubPostVO);
	
	//게시글 단건조회
	public ClubPostVO selectPost(ClubPostVO clubPostVO);
	
	//게시글 등록
	public int insertPost(ClubPostVO clubPostVO);
	
	//게시글 삭제(댓글, 첨부파일 삭제 프로시저)
	public boolean deletePost(int clubPostNo);
	
	//게시글 수정
	public Map<String, Object> updatePost(ClubPostVO clubPostVO);
	
	//게시글 조회수
	public void updatePostHit(int hit);
	
	//페이징 전체 갯수
	public int selectCount(ClubPostVO clubPostVO);
}
