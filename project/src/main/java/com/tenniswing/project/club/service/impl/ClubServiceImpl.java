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

	@Override
	public List<ClubVO> selectAllClub() {
		return clubMapper.selectAllClub();
	}

	@Override
	public int isnertClub(ClubVO clubVO) {
		int result = clubMapper.isnertClub(clubVO);
		
		if(result == 1) {
			return clubVO.getClubNo();
		}else {
			return -1;
		}
	}
}
