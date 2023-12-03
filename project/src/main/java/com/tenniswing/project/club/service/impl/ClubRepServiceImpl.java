package com.tenniswing.project.club.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenniswing.project.club.mapper.ClubRepMapper;
import com.tenniswing.project.club.service.ClubRepService;
import com.tenniswing.project.club.service.ClubRepVO;

@Service
public class ClubRepServiceImpl implements ClubRepService {

	@Autowired
	ClubRepMapper clubRepMapper;
	
	//댓글 전체 조회
	@Override
	public List<ClubRepVO> selectAllRep(ClubRepVO clubRepVO) {
		return clubRepMapper.selectAllRep(clubRepVO);
	}

	//댓글 등록
	@Override
	public int insertRep(ClubRepVO clubRepVO) {
		return clubRepMapper.insertRep(clubRepVO);
	}

	//댓글 삭재
	@Override
	public boolean deleteRep(int clubRepNo) {
		int result = clubRepMapper.deleteRep(clubRepNo);
		
		if(result == 1) {
			return true;
		}else {
			return false;
		}
	}

}
