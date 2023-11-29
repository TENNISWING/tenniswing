package com.tenniswing.project.club.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenniswing.project.club.mapper.ClubMapper;
import com.tenniswing.project.club.service.ClubService;
import com.tenniswing.project.club.service.ClubVO;

@Service
public class ClubServiceImpl implements ClubService {

	@Autowired ClubMapper clubMapper;

	//전체조회
	@Override
	public List<ClubVO> selectAllClub(ClubVO clubVO) {
		return clubMapper.selectAllClub();
	}

	//단건조회
	@Override
	public ClubVO selectClub(ClubVO clubVO) {
		return clubMapper.selectClub(clubVO);
	}
	
	//등록
	@Override
	public int insertClub(ClubVO clubVO) {
		int result = clubMapper.insertClub(clubVO);
		
		if(result == 1) {
			return clubVO.getClubNo();
		}else {
			return -1;
		}
	}

	
}
