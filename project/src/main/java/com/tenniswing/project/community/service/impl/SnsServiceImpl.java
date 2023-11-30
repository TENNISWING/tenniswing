package com.tenniswing.project.community.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenniswing.project.community.mapper.SnsMapper;
import com.tenniswing.project.community.service.SnsService;
import com.tenniswing.project.community.service.SnsVO;

@Service
public class SnsServiceImpl implements SnsService {

	@Autowired
	SnsMapper snsMapper;

	// 전체조회(다건 조회)
	@Override
	public List<SnsVO> selectAllSnsInfo() {
		return snsMapper.selectAllSnsInfo();
	}

	// 단건 조회
	@Override
	public SnsVO selectSnsInfo(SnsVO snsVO) {
		return snsMapper.selectSnsInfo(snsVO);
	}

	// 등록
	@Override
	public int insertSns(SnsVO snsVO) {
		int result = snsMapper.insertSns(snsVO);

		if (result == 1) {
			return snsVO.getSnsWrtNo();
		} else {
			return -1;
		}
	}

	// 그룹등록
	@Override
	public int insertSnsGrp(SnsVO snsVO) {
		return 0;
	}

	// 좋아요 등록
	@Override
	public int insertLike(SnsVO snsVO) {
		return snsMapper.insertLike(snsVO);
	}

	// 스크랩 등록
	@Override
	public int insertSnsScrap(SnsVO snsVO) {
		return 0;
	}


	// 수정
	@Override
	public Map<String, Object> updateSns(SnsVO snsVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result = snsMapper.updateSns(snsVO);
		if(result == 1) {
			isSuccessed = true;
		}
		map.put("result", isSuccessed);
		map.put("info", snsVO);
		
		return map;
	}

	// 삭제
	@Override
	public int deleteSns(int snsWrtNo) {
		return 0;
	}

}
