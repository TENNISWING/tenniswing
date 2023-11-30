package com.tenniswing.project.shop.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenniswing.project.shop.mapper.ProdDetailMapper;
import com.tenniswing.project.shop.service.ProdDetailService;
import com.tenniswing.project.shop.service.ProdDetailVO;

@Service
public class ProdDetailServiceImpl implements ProdDetailService{
	@Autowired
	ProdDetailMapper prodDetailMapper;
	
	//전체
	@Override
	public List<ProdDetailVO> selectAllProdDetail() {
		return prodDetailMapper.selectAllProdDetail();
	}
	//등록
	@Override
	public int insertProdDetail(ProdDetailVO prodDetailVO) {
		if(prodDetailVO.getProdDetailSaleSts() == null) {
			prodDetailVO.setProdDetailSaleSts("p2");
		}
		int result = prodDetailMapper.insertProdDetail(prodDetailVO);
		if(result == 1) {
			return prodDetailVO.getProdDetailNo();
		} else
			return -1;
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
