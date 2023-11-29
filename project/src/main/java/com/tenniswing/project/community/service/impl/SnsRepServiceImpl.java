package com.tenniswing.project.community.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenniswing.project.community.mapper.SnsRepMapper;
import com.tenniswing.project.community.service.SnsRepService;
import com.tenniswing.project.community.service.SnsRepVO;
@Service
public class SnsRepServiceImpl implements SnsRepService {
	@Autowired
	SnsRepMapper snsRepMapper;
	
	@Override
	public List<SnsRepVO> selectAllSnsRep(SnsRepVO snsRepVO) {
		return snsRepMapper.selectAllSnsRep(snsRepVO);
	}

	@Override
	public SnsRepVO selectSnsRep(SnsRepVO snsRepVO) {
		return snsRepMapper.selectSnsRep(snsRepVO);
	}

	@Override
	public int insertSnsRep(SnsRepVO snsRepVO) {
		return snsRepMapper.insertSnsRep(snsRepVO);
	}

	@Override
	public int insertSnsRrep(SnsRepVO snsRepVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, Object> updateSnsRep(SnsRepVO snsRepVO) {
		
		Map<String, Object> map = new HashMap<>();
		boolean inSuccessed = false;
		
		int result = snsRepMapper.updateSnsRep(snsRepVO);
		if(result == 1) {
			inSuccessed = true;
		}
		map.put("result", inSuccessed);
		map.put("info", snsRepVO);
		return map;
	}

	//result.snsRepVO.변수명
	
	@Override
	public boolean deleteSnsRep(int snsRepNo) {
		int result = snsRepMapper.deleteSnsRep(snsRepNo);
		
		if(result == 1) {
			return true;
		}else {
			return false;
		}
	}

}
