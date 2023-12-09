package com.tenniswing.project.club.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenniswing.project.club.mapper.ClubMatchMapper;
import com.tenniswing.project.club.service.ClubMatchService;
import com.tenniswing.project.club.service.ClubMatchVO;

@Service
public class ClubMatchServiceImpl implements ClubMatchService {
	
	@Autowired
	ClubMatchMapper clubMatchMapper;

	//매치 모집 등록
	@Override
	public int insertMatchRecruit(ClubMatchVO clubMatchVO) {
		return clubMatchMapper.insertMatchRecruit(clubMatchVO);
	}

	//매치 모집 전체조회
	@Override
	public List<ClubMatchVO> selectAllMatchRecruit(ClubMatchVO clubMatchVO) {
		return clubMatchMapper.selectAllMatchRecruit(clubMatchVO);
	}

}
