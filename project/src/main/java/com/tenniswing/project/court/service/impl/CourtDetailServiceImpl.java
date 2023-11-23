package com.tenniswing.project.court.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenniswing.project.court.mapper.CourtDetailMapper;
import com.tenniswing.project.court.service.CourtDetailService;
import com.tenniswing.project.court.service.CrtDetailInfoVO;

@Service
public class CourtDetailServiceImpl implements CourtDetailService {

	@Autowired
	CourtDetailMapper courtDetailMapper;

	@Override
	public int insertCourtDetail(CrtDetailInfoVO crtDetailInfoVO) {
		// TODO Auto-generated method stub
		return 0;
	}
}
	
//	@Override
//	public int insertCourtDetail(CrtDetailInfoVO crtDetailInfoVO) {
//		int result = courtDetailMapper.insertCourtDetail(crtDetailInfoVO);
//		
//		if(result == 1) {
//			return crtDetailInfoVO.getCrtDetailNo();
//		}
//		else {
//			return -1;
//		}
//	}

//}
