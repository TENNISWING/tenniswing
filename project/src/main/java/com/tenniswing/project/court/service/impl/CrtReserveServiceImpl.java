package com.tenniswing.project.court.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.tenniswing.project.court.mapper.CrtReserveMapper;
import com.tenniswing.project.court.service.CrtReserveService;
import com.tenniswing.project.court.service.CrtReserveVO;
import com.tenniswing.project.court.service.CrtroomVO;
import com.tenniswing.project.court.service.HostDashBoardVO;

@Service
public class CrtReserveServiceImpl implements CrtReserveService {

	@Autowired CrtReserveMapper crtReserveMapper;
	
	
	@Scheduled(fixedRate = 86400000)
	public void reportCurrentTime() {
		Date date = new Date();
		CrtReserveVO crtReserveVO = new CrtReserveVO();
		crtReserveVO.setReserveDate(date);
		crtReserveMapper.updateState(crtReserveVO);
	}
	
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
	public List<Map<String, Object>> selectMyCourtReverse(String id) {
		return crtReserveMapper.selectMyCourtReverse(id);
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

	@Override
	public int insertCrtRefund(CrtReserveVO crtReserveVO) {
		return crtReserveMapper.insertCrtRefund(crtReserveVO);
	}

	@Override
	public int updateCrtReserveState(CrtReserveVO crtReserveVO) {
		return crtReserveMapper.updateCrtReserveState(crtReserveVO);
	}

	@Override
	public List<CrtReserveVO> selectAllCrtDeReserve(CrtReserveVO crtReserveVO) {
		return crtReserveMapper.selectAllCrtDeReserve(crtReserveVO);
	}

	@Override
	public HostDashBoardVO hostMonthReserve() {
		return crtReserveMapper.hostMonthReserve();
	}

	@Override
	public HostDashBoardVO hostMonthRefund() {
		return crtReserveMapper.hostMonthRefund();
	}

	@Override
	public List<CrtReserveVO> todayReserve() {
		return crtReserveMapper.todayReserve();
	}

	@Override
	public HostDashBoardVO thisYearMonthPrice() {
		return crtReserveMapper.thisYearMonthPrice();
	}

	@Override
	public List<CrtroomVO> recentReview(String hostId) {
		return crtReserveMapper.recentReview(hostId);
	}

	@Override
	public HostDashBoardVO hostInfo(String hostId) {
		return crtReserveMapper.hostInfo(hostId);
	}

	@Override
	public HostDashBoardVO hostStarInfo(String hostId) {
		return crtReserveMapper.hostStarInfo(hostId);
	}

}
