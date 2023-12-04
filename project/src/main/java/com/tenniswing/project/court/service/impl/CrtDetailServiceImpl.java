package com.tenniswing.project.court.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.tenniswing.project.attach.mapper.AttachMapper;
import com.tenniswing.project.attach.service.AttachVO;
import com.tenniswing.project.court.mapper.CrtDetailMapper;
import com.tenniswing.project.court.service.CrtDetailService;
import com.tenniswing.project.court.service.CrtDetailVO;

@Service
public class CrtDetailServiceImpl implements CrtDetailService {
	
	@Autowired
	CrtDetailMapper crtDetailMapper;
	
	@Autowired
	AttachMapper attachMapper;
	
	@Override
	@Transactional
	public int insertCrtDetail(CrtDetailVO crtDetailVO, List<AttachVO> files) {
		int result = crtDetailMapper.insertCrtDetail(crtDetailVO);
		
		//사진 등록
		//테이블 구분, 파일목록

		int crtroomNo = crtDetailVO.getCrtroomNo();
		
		int index = 1;
		for (AttachVO file : files) {
			file.setAttachTableDiv("cd");
			file.setAttachTablePk(crtroomNo);
			file.setAttachTurn(index);
			index++;
		}
		
		attachMapper.saveAttachTurn(files);
		
		
		if(result == 1) {
			return crtDetailVO.getCrtDetailNo();
		}else {
			return -1;
		}
	}

	@Override
	public Map<String, Object> updateCrtDetail(CrtDetailVO crtDetailVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result = crtDetailMapper.updateCrtDetail(crtDetailVO);
		if(result == 1) {
			isSuccessed = true;
		}
		map.put("result", isSuccessed);
		map.put("crtDetail", crtDetailVO);
		return map;
	}

	@Override
	public CrtDetailVO selectCrtDetail(CrtDetailVO crtDetailVO) {
		return crtDetailMapper.selectCrtDetail(crtDetailVO);
	}

	@Override
	public boolean deleteCrtDetail(int crtDetailNo) {
		int result = crtDetailMapper.deleteCrtDetail(crtDetailNo);
		
		if(result == 1) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public CrtDetailVO selectCrtDetailNo(int crtDetailNo) {
		return crtDetailMapper.selectCrtDetailNo(crtDetailNo);
	}

	@Override
	public CrtDetailVO refundInf() {
		return crtDetailMapper.refundInf();
	}

}
