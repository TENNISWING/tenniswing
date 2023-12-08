package com.tenniswing.project.court.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenniswing.project.court.mapper.CrtReserveMapper;
import com.tenniswing.project.court.service.CrtReserveService;
import com.tenniswing.project.court.service.CrtReserveVO;

@Service
public class CrtReserveServiceImpl implements CrtReserveService {

	@Autowired CrtReserveMapper crtReserveMapper;
	
	@Override
	public Map<String, Object> insertCrtReserve(CrtReserveVO crtReserveVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		int result = crtReserveMapper.insertCrtReserve(crtReserveVO);
		if(result > 0) {
			isSuccessed = true;
		}
		map.put("result", isSuccessed);
		map.put("crtReserveVO", crtReserveVO);
		
		return map;
	}

	@Override
	public List<CrtReserveVO> impossibleReserveList(CrtReserveVO crtReserveVO) {
		return crtReserveMapper.impossibleReserveList(crtReserveVO);
	}

	@Override
	public List<CrtReserveVO> reserveTimeCodeList() {
		return crtReserveMapper.reserveTimeCodeList();
	}

	@Override
	public List<CrtReserveVO> selectAllCrtReserve(String hostId) {
		return crtReserveMapper.selectAllCrtReserve(hostId);
	}

	@Override
	public List<CrtReserveVO> selectAllCrtRefund(String hostId) {
		return crtReserveMapper.selectAllCrtRefund(hostId);
	}

	@Override
	public List<CrtReserveVO> selectAllCrtCalc(String hostId) {
		return crtReserveMapper.selectAllCrtCalc(hostId);
	}

}
