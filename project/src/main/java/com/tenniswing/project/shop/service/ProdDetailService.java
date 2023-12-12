package com.tenniswing.project.shop.service;

import java.util.List;
import java.util.Map;

public interface ProdDetailService {
//	전체조회
	public List<ProdDetailVO> selectAllProdDetail(ProdVO prodVO);
	
//	장바구니 등록시 한건 조회
	public ProdDetailVO selectCartProd(ProdDetailVO prodDetailVO);
	
//	한건
	public ProdDetailVO selectProdDetail(ProdDetailVO prodDetailVO); 
	
//	등록
	public Map<String, Object> insertProdDetail(ProdDetailVO prodDetailVO);
//	수정
	public Map<String, Object> updateProdDetail(ProdDetailVO prodDetailVO);
//	삭제
	public Map<String, Object> deleteProdDetail(ProdDetailVO prodDetailVO);
	
	// 주문 취소시 재고 추가 update
	public int updateProdDetailCancel(ProdDetailVO prodDetailVO);
}
