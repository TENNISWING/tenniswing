package com.tenniswing.project.shop.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenniswing.project.shop.mapper.ProdDetailMapper;
import com.tenniswing.project.shop.service.ProdDetailService;
import com.tenniswing.project.shop.service.ProdDetailVO;
import com.tenniswing.project.shop.service.ProdVO;

@Service
public class ProdDetailServiceImpl implements ProdDetailService{
	@Autowired
	ProdDetailMapper prodDetailMapper;
	
	//전체
	@Override
	public List<ProdDetailVO> selectAllProdDetail(ProdVO prodVO) {
		return prodDetailMapper.selectAllProdDetail(prodVO);
	}
	//등록
	@Override
	public Map<String, Object> insertProdDetail(ProdDetailVO prodDetailVO) {
		Map<String, Object> map = new HashMap<>();
		ProdVO tempVO = new ProdVO();
		tempVO.setProdNo(prodDetailVO.getProdNo());
		
		if(prodDetailVO.getProdDetailSaleSts() == null) {
			prodDetailVO.setProdDetailSaleSts("p2");
		}
		boolean isSucess = false;
		
		int result = prodDetailMapper.insertProdDetail(prodDetailVO);
		if(result == 1) {
			isSucess = true;
		}
		map.put("result", isSucess);	
		map.put("targetVO", prodDetailVO);
		map.put("listDetailVO", prodDetailMapper.selectAllProdDetail(tempVO));
		return map;
	}
	//수정
	@Override
	public Map<String, Object> updateProdDetail(ProdDetailVO prodDetailVO) {
		return null;
	}
	//삭제
	@Override
	public Map<String, Object> deleteProdDetail(int prodDetailNo) {
		return null;
	}
	
}
