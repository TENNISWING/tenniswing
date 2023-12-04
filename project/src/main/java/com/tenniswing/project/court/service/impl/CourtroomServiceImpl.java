package com.tenniswing.project.court.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenniswing.project.attach.service.AttachVO;
import com.tenniswing.project.court.mapper.CourtroomMapper;
import com.tenniswing.project.court.service.CourtroomService;
import com.tenniswing.project.court.service.CrtroomVO;

@Service
public class CourtroomServiceImpl implements CourtroomService {

	@Autowired CourtroomMapper courtroomMapper;
	
	// 호스트
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

	@Override
	public Map<String, Object> updateCourtroom(CrtroomVO crtroomVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result = courtroomMapper.updateCourtroom(crtroomVO);
		if(result == 1) {
			isSuccessed = true;
		}
		map.put("result", isSuccessed);
		map.put("info", crtroomVO);
		
		return map;
	}

	@Override
	public List<CrtroomVO> selectAllCourtroom(String hostId) {
		return courtroomMapper.selectAllCourtroom(hostId);
	}

	@Override
	public CrtroomVO selectCourtroom(CrtroomVO crtroomVO) {
		return courtroomMapper.selectCourtroom(crtroomVO);
	}

	@Override
	public boolean deleteCourtroom(int crtroomNo) {
		int result = courtroomMapper.deleteCourtroom(crtroomNo);
		
		if(result == 1) {
			return true;
		}else {
			return false;
		}
	}

	//사용자
	@Override
	public List<CrtroomVO> selectAllCourtroomMain() {
		return courtroomMapper.selectAllCourtroomMain();
	}

	@Override
	public List<CrtroomVO> crtroomBanner() {
		return courtroomMapper.crtroomBanner();
	}

	@Override
	public List<CrtroomVO> recentRegiCourt() {
		return courtroomMapper.recentRegiCourt();
	}

	@Override
	public List<CrtroomVO> courtSearch(String str) {		
		return courtroomMapper.courtSearch(str);
	}
}
