package com.tenniswing.project.court.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenniswing.project.court.mapper.CrtReserveMapper;
import com.tenniswing.project.court.service.CrtReserveService;
import com.tenniswing.project.court.service.CrtReserveVO;

@Service
public class CrtReserveServiceImpl implements CrtReserveService {

	@Autowired CrtReserveMapper crtReserveMapper;
	
	@Override
	public int insertCrtReserve(CrtReserveVO crtReserveVO) {
		int result = crtReserveMapper.insertCrtReserve(crtReserveVO);
		if(result == 1) {
			return crtReserveVO.getCrtroomNo();
		}
		else {
			return -1;
		}
	}

}
