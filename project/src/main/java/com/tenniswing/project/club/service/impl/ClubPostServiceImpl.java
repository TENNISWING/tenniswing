package com.tenniswing.project.club.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenniswing.project.attach.service.AttachService;
import com.tenniswing.project.attach.service.AttachVO;
import com.tenniswing.project.club.mapper.ClubPostMapper;
import com.tenniswing.project.club.service.ClubPostService;
import com.tenniswing.project.club.service.ClubPostVO;
import com.tenniswing.project.common.FileUtils;

@Service
public class ClubPostServiceImpl implements ClubPostService  {
	@Autowired
	ClubPostMapper clubPostMapper;
	
	@Autowired
	AttachService attachService;
	
	@Autowired
	FileUtils fileUtils;
	
	
	//게시글 전체조회
	@Override
	public List<ClubPostVO> selectAllPost(ClubPostVO clubPostVO) {
		clubPostMapper.updatePostHit(clubPostVO.getClubPostNo());
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
		
	//게시글 삭제(댓글,첨부파일 삭제 프로시저)
		@Override
		public boolean deletePost(int clubPostNo) {
			HashMap<String, Long> map = new HashMap<String, Long>();
			map.put("clubPostNo", (long) clubPostNo);
			map.put("delPostResult", (long) 0);
			clubPostMapper.deletePost(map);
			long result = (long)map.get("delPostResult");
			
			if(result >= 1) {
				return true;
			}else {
				return false;
			}
		}

	//게시글 수정	
		@Override
		public Map<String, Object> updatePost(ClubPostVO clubPostVO) {
			Map<String, Object> map = new HashMap<>();
			
			List<AttachVO> files = fileUtils.uploadFiles(clubPostVO.getFiles());
			attachService.updateAttach("cp", clubPostVO.getClubNo(), files);
			
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

	@Override
	public int selectCount(ClubPostVO clubPostVO) {
		return clubPostMapper.selectCount(clubPostVO);
	}
		
		

}
