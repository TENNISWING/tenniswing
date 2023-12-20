package com.tenniswing.project.court.service.impl;

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
	
	
	@Scheduled(cron = "0 59 23 * * *", zone = "Asia/Seoul")
	public void reportCurrentTime() {
		crtReserveMapper.updateState();
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
	public HostDashBoardVO hostMonthReserve(String hostId) {
		return crtReserveMapper.hostMonthReserve(hostId);
	}

	@Override
	public HostDashBoardVO hostMonthRefund(String hostId) {
		return crtReserveMapper.hostMonthRefund(hostId);
	}

	@Override
	public List<CrtReserveVO> todayReserve(String hostId) {
		return crtReserveMapper.todayReserve(hostId);
	}

	@Override
	public HostDashBoardVO thisYearMonthPrice(String hostId) {
		return crtReserveMapper.thisYearMonthPrice(hostId);
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
