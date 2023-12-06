package com.tenniswing.project.community.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenniswing.project.community.mapper.BrdMapper;
import com.tenniswing.project.community.service.BrdService;
import com.tenniswing.project.community.service.BrdVO;
@Service
public class BrdServiceImpl implements BrdService {
	@Autowired
	BrdMapper brdMapper;
	
	// 전체조회
	@Override
	public List<BrdVO> selectBrdAllInfo(BrdVO brdVO) {
		return brdMapper.selectBrdAllInfo(brdVO);
	}
	
	// 단건조회
	@Override
	public BrdVO selectBrdInfo(BrdVO brdVO) {
		return brdMapper.selectBrdInfo(brdVO);
	}

	// 등록
	@Override
	public int insertBrd(BrdVO brdVO) {
		return brdMapper.insertBrd(brdVO);
	}
	
	// 수정
	@Override
	public Map<String, Object> updateBrd(BrdVO brdVO) {
		Map<String, Object> map = new HashMap<>();
		boolean inSuccessed = false;
		
		int result = brdMapper.updateBrd(brdVO);
		
		if(result == 1) {
			inSuccessed = true;
		}
		map.put("result", inSuccessed);
		map.put("info", brdVO);
		
		return map;
	}

	// 삭제
	@Override
	public boolean deleteBrd(int brdWrtNo) {
		int result = brdMapper.deleteBrd(brdWrtNo); 
		
		if(result == 1) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public int selectCount(BrdVO brdVO) {
		return brdMapper.selectCount(brdVO);
	}

}
