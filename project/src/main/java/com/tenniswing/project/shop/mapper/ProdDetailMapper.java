package com.tenniswing.project.shop.mapper;

import java.util.List;

import com.tenniswing.project.shop.service.ProdDetailVO;
import com.tenniswing.project.shop.service.ProdVO;

public interface ProdDetailMapper {

//	전체조회
	public List<ProdDetailVO> selectAllProdDetail(ProdVO prodVO);
	
//	한건
	public ProdDetailVO selectProdDetail(ProdDetailVO prodDetailVO);
	
//	장바구니 등록시 한건 조회
	public ProdDetailVO selectCartProd(ProdDetailVO prodDetailVO);
	
//	등록
	public int insertProdDetail(ProdDetailVO prodDetailVO);
//	수정
	public int updateProdDetail(ProdDetailVO prodDetailVO);
//	삭제
	public int deleteProdDetail(ProdDetailVO prodDetailVO);
}
