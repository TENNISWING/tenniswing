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
	
	//한건
	@Override
	public ProdDetailVO selectProdDetail(ProdDetailVO prodDetailVO) {
		return prodDetailMapper.selectProdDetail(prodDetailVO);
	}
	
	// 장바구니 등록시 한건 조회
	@Override
	public ProdDetailVO selectCartProd(ProdDetailVO prodDetailVO) {
		return prodDetailMapper.selectCartProd(prodDetailVO);
	}
	
	// 주문 취소시 재고 추가 update
	@Override
	public int updateProdDetailCancel(ProdDetailVO prodDetailVO) {
		return prodDetailMapper.updateProdDetailCancel(prodDetailVO);
	}
	
	//등록
	@Override
	public Map<String, Object> insertProdDetail(ProdDetailVO prodDetailVO) {
		Map<String, Object> map = new HashMap<>();
		ProdVO tempVO = new ProdVO();
		tempVO.setProdNo(prodDetailVO.getProdNo());
		//tempVO.setProdTSto(prodDetailVO.getProdDetailSto());
		
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
		Map<String, Object> map = new HashMap<>();
		ProdVO tempVO = new ProdVO();
		tempVO.setProdNo(prodDetailVO.getProdNo());
		
		if(prodDetailVO.getProdDetailSaleSts() == null) {
			prodDetailVO.setProdDetailSaleSts("p2");
		}
		boolean isSucess = false;
		
		int result = prodDetailMapper.updateProdDetail(prodDetailVO);
		if(result == 1) {
			isSucess = true;
		}
		map.put("result", isSucess);	
		map.put("targetVO", prodDetailVO);
		map.put("listDetailVO", prodDetailMapper.selectAllProdDetail(tempVO));
		return map;
	}
	//삭제
	@Override
	public Map<String, Object> deleteProdDetail(ProdDetailVO prodDetailVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		ProdVO tempVO = new ProdVO();
		tempVO.setProdNo(prodDetailVO.getProdNo());
		
		boolean isSuccess = false;
		
		int result = prodDetailMapper.deleteProdDetail(prodDetailVO);
		if(result == 1) {
			isSuccess = true;
		}
		map.put("result", isSuccess);
		map.put("target", prodDetailVO);
		map.put("listDetailVO", prodDetailMapper.selectAllProdDetail(tempVO));
		return map;	
	}
	
}
