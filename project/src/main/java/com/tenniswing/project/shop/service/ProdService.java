package com.tenniswing.project.shop.service;

import java.util.List;
import java.util.Map;

public interface ProdService {
	// 전체조회
	public List<ProdVO> selectAllProd(ProdVO prodVO);
	
	// 페이징 전체 갯수
	public int selectCount(ProdVO prodVO);
	
	// 최근 상품 스와이퍼
	public List<ProdVO> selectSwiperProd();
	
	// 단건조회
	public ProdVO selectProd(ProdVO prodVO);
	
	// 등록
	public int insertProd(ProdVO prodVO);
	
	// 수정
	public Map<String, Object> updateProd(ProdVO prodVO);
	
	// 조회수
	public void updateProdHit(int hit);
	
	// 수정 - 상품 상세 추가시
	
	// 수정 - 상품 상세 수정시s
	
	// 수정 - 상품 상세 삭제시
	
	// 삭제
	public Map<String, Object> deleteProd(int prodNo);
}
