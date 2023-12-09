package com.tenniswing.project.community.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenniswing.project.community.mapper.SnsRrepMapper;
import com.tenniswing.project.community.service.SnsRrepService;
import com.tenniswing.project.community.service.SnsRrepVO;
@Service
public class SnsRrepServiceImpl implements SnsRrepService {
	@Autowired
	SnsRrepMapper snsRrepMapper;
	
	// 대댓글 조회
	@Override
	public List<SnsRrepVO> selectAllSnsRrep(SnsRrepVO snsRrepVO) {
		return snsRrepMapper.selectAllSnsRrep(snsRrepVO);
	}
	
	// 대댓글 단건조회
	@Override
	public SnsRrepVO selectSnsRrep(SnsRrepVO snsRrepVO) {
		return snsRrepMapper.selectSnsRrep(snsRrepVO);
	}
	
	// 대댓글 등록
	@Override
	public int insertSnsRrep(SnsRrepVO snsRrepVO) {
		return snsRrepMapper.insertSnsRrep(snsRrepVO);
	}
	
	// 대댓글 수정
	@Override
	public Map<String, Object> updateSnsRrep(SnsRrepVO snsRrepVO) {
		Map<String, Object> map = new HashMap<>();
		boolean inSuccessed = false;
		
		int result = snsRrepMapper.updateSnsRrep(snsRrepVO);
		if(result == 1) {
			inSuccessed = true;
		}
		map.put("result", inSuccessed);
		map.put("info", snsRrepVO);
		return map;
	}
	
	// 대댓글 삭제
	@Override
	public boolean deleteSnsRrep(int snsRrepNo) {
		int result = snsRrepMapper.deleteSnsRrep(snsRrepNo);
		
		if(result == 1) {
			return true;
		}else {
			return false;
		}
	}

}
