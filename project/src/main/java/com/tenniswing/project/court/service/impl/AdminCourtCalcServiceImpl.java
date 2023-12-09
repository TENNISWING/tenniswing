package com.tenniswing.project.court.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenniswing.project.court.mapper.AdminCourtCalcMapper;
import com.tenniswing.project.court.service.AdminCourtCalcService;
import com.tenniswing.project.court.service.AdminCourtCalcVO;

@Service
public class AdminCourtCalcServiceImpl implements AdminCourtCalcService {
	
	@Autowired
	AdminCourtCalcMapper adminCourtCalcMapper;
	
	// 호스트 정산 조회
	@Override
	public Map<String, Object> selectAllAdminCourtCalc(AdminCourtCalcVO adminCalcVO) {
		int total = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		List<AdminCourtCalcVO> list = adminCourtCalcMapper.selectAllAdminCourtCalc(adminCalcVO);
		for(AdminCourtCalcVO calc : list){
			 if(calc.getReserveState().equals("i2")){
			        total += calc.getReservePrice();
			    }else{
			        total += calc.getReservePrice() - calc.getRefundPrice();
			    }
		}
		map.put("calcList", list);
		map.put("total", total);
		return map;
	}
}
