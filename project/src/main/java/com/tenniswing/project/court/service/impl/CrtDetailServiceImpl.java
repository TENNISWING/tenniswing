package com.tenniswing.project.court.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenniswing.project.court.mapper.CrtDetailMapper;
import com.tenniswing.project.court.service.CrtDetailService;
import com.tenniswing.project.court.service.CrtDetailVO;

@Service
public class CrtDetailServiceImpl implements CrtDetailService {
	
	@Autowired
	CrtDetailMapper crtDetailMapper;
	
	@Override
	public int insertCrtDetail(CrtDetailVO crtDetailVO) {
		int result = crtDetailMapper.insertCrtDetail(crtDetailVO);
		
		if(result == 1) {
			return crtDetailVO.getCrtDetailNo();
		}else {
			return -1;
		}
	}

}
