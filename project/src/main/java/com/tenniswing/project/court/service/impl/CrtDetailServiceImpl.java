package com.tenniswing.project.court.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenniswing.project.court.mapper.CrtDetailMapper;
import com.tenniswing.project.court.service.CrtDetailService;
import com.tenniswing.project.court.service.CrtDetailVO;
import com.tenniswing.project.court.service.CrtroomVO;

@Service
public class CrtDetailServiceImpl implements CrtDetailService {
	
	@Autowired
	CrtDetailMapper crtDetailMapper;
	
	@Override
	public int insertCrtDetail(CrtroomVO crtroomVO) {
		int result = crtDetailMapper.insertCrtDetail(crtroomVO);
		
		if(result == 1) {
			return crtroomVO.getCrtDetailNo();
		}else {
			return -1;
		}
	}

}
