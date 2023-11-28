package com.tenniswing.project.shop.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenniswing.project.shop.mapper.ProdMapper;
import com.tenniswing.project.shop.service.ProdService;
import com.tenniswing.project.shop.service.ProdVO;

@Service
public class ProdServiceImpl implements ProdService{

	@Autowired
	ProdMapper prodMapper;
	
//	전체 조회
	@Override
	public List<ProdVO> selectAllProd() {
		return prodMapper.selectAllProd();
	}

//	단건 조회
	@Override
	public ProdVO selectProd(ProdVO prodVO) {
		return prodMapper.selectProd(prodVO);
	}

//	등록
	@Override
	public int insertProd(ProdVO prodVO) {
		System.out.println(prodVO.getProdSaleSts());
		if(prodVO.getProdSaleSts() == null) {
			prodVO.setProdSaleSts("p2");
		}
		
		int result = prodMapper.insertProd(prodVO);
		if(result == 1) {
			return prodVO.getProdNo(); 
		}
		else 
			return -1;
	}

	@Override
	public Map<String, Object> updateProd(ProdVO prodVO) {
		return null;
	}

	@Override
	public boolean deleteProd(int prodNo) {
		return false;
	}

}
