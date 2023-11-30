package com.tenniswing.project.club.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenniswing.project.club.mapper.ClubMapper;
import com.tenniswing.project.club.service.ClubService;
import com.tenniswing.project.club.service.ClubVO;

@Service
public class ClubServiceImpl implements ClubService {

	@Autowired ClubMapper clubMapper;

	//클럽 전체조회
	@Override
	public List<ClubVO> selectAllClub(ClubVO clubVO) {
		return clubMapper.selectAllClub();
	}

	//클럽 단건조회
	@Override
	public ClubVO selectClub(ClubVO clubVO) {
		return clubMapper.selectClub(clubVO);
	}
	
	//클럽 등록
	@Override
	public int insertClub(ClubVO clubVO) {
		int result = clubMapper.insertClub(clubVO);
		
		if(result == 1) {
			return clubVO.getClubNo();
		}else {
			return -1;
		}
	}
	
	//클럽 삭제
	@Override
	public boolean deleteClub(int ClubNo) {
		int result = clubMapper.deleteClub(ClubNo);
		
		if(result ==1) {
			return true;
		}else {
			return false;
		}
	}
	
	//클럽 수정
	@Override
	public Map<String, Object> updateClub(ClubVO clubVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result = clubMapper.updateClub(clubVO);
		
		if(result == 1) {
			isSuccessed = true;
		}
		map.put("result", isSuccessed);
		map.put("info", clubVO);
		return map;
	}

	
}
