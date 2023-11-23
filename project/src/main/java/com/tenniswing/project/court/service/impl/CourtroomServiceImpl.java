package com.tenniswing.project.court.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenniswing.project.court.mapper.CourtroomMapper;
import com.tenniswing.project.court.service.CourtroomService;
import com.tenniswing.project.court.service.CrtroomVO;

@Service
public class CourtroomServiceImpl implements CourtroomService {

	@Autowired CourtroomMapper courtroomMapper;
	
	@Override
	public int insertCourtroom(CrtroomVO crtroomVO) {
		int result = courtroomMapper.insertCourtroom(crtroomVO);
		
		if(result == 1) {
			return crtroomVO.getCrtroomNo();
		}
		else {
			return -1;
		}
	}
}
