package com.tenniswing.project.club.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenniswing.project.club.mapper.ClubPostMapper;
import com.tenniswing.project.club.service.ClubPostService;
import com.tenniswing.project.club.service.ClubPostVO;

@Service
public class ClubPostServiceImpl implements ClubPostService  {
	@Autowired
	ClubPostMapper clubPostMapper;
	
	//게시글 등록
	@Override
	public int insertPost(ClubPostVO clubPostVO) {
		return clubPostMapper.insertPost(clubPostVO);
	}

}
