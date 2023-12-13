package com.tenniswing.project.club.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenniswing.project.club.mapper.ClubMapper;
import com.tenniswing.project.club.mapper.ClubMatchMapper;
import com.tenniswing.project.club.service.ClubMatchService;
import com.tenniswing.project.club.service.ClubVO;
import com.tenniswing.project.match.service.MatchVO;

@Service
public class ClubMatchServiceImpl implements ClubMatchService {
	
	@Autowired
	ClubMatchMapper clubMatchMapper;

	//매치 모집 등록
	@Override
	public int insertMatchRecruit(ClubVO clubVO) {
		return clubMatchMapper.insertMatchRecruit(clubVO);
	}

	//매치 모집 전체조회
	@Override
	public List<ClubVO> selectAllMatchRecruit(ClubVO clubVO) {
		return clubMatchMapper.selectAllMatchRecruit(clubVO);
	}

	//매치 모집 멤버추가
	@Override
	public int insertRecruitMem(ClubVO clubVO) {
		return clubMatchMapper.insertRecruitMem(clubVO);
	}
	
	//매치 결과 입력
	@Override
	public int insertResult(ClubVO clubVO) {
		return clubMatchMapper.insertResult(clubVO);
	}

	@Override
	public boolean clubRecDelete(int clubMatchRecruitNo) {
		
		HashMap<String, Long> map = new HashMap<String, Long>();
		map.put("clubMatchRecruitNo", (long) clubMatchRecruitNo);
		map.put("delRecResult", (long) 0);
		
		clubMatchMapper.clubRecDelete(map);
		
		long result = (long)map.get("delRecResult");
		
		if(result >= 1) {
			return true;
		}else {
			return false;
		}
	}

}
