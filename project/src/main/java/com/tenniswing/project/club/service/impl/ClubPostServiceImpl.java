package com.tenniswing.project.club.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenniswing.project.club.mapper.ClubPostMapper;
import com.tenniswing.project.club.service.ClubPostService;
import com.tenniswing.project.club.service.ClubPostVO;

@Service
public class ClubPostServiceImpl implements ClubPostService  {
	@Autowired
	ClubPostMapper clubPostMapper;
	
	//게시글 전체조회
	@Override
	public List<ClubPostVO> selectAllPost(ClubPostVO clubPostVO) {
		return clubPostMapper.selectAllPost(clubPostVO);
	}

	//게시글 상세조회
	@Override
	public ClubPostVO selectPost(ClubPostVO clubPostVO) {
		return clubPostMapper.selectPost(clubPostVO);
	}
	
	
	//게시글 등록
		@Override
		public int insertPost(ClubPostVO clubPostVO) {
			return clubPostMapper.insertPost(clubPostVO);
		}
		
	//게시글 삭제	
		@Override
		public boolean deletePost(int clubPostNo) {
			int result = clubPostMapper.deletePost(clubPostNo);
			
			if(result == 1) {
				return true;
			}else {
				return false;
			}
		}

	//게시글 수정	
		@Override
		public Map<String, Object> updatePost(ClubPostVO clubPostVO) {
			Map<String, Object> map = new HashMap<>();
			boolean isSuccessed = false;
			
			int result = clubPostMapper.updatePost(clubPostVO);
			
			if(result == 1) {
				isSuccessed = true;
			}
			map.put("result", isSuccessed);
			map.put("post", clubPostVO);
			return map;
			
		}

		//조회수
		@Override
		public void updatePostHit(int clubPostNo) {
			clubPostMapper.updatePostHit(clubPostNo);
			
		}
		
		

}
