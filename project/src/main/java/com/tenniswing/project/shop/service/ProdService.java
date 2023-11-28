package com.tenniswing.project.shop.service;

import java.util.List;
import java.util.Map;

public interface ProdService {
	// 전체조회
	public List<ProdVO> selectAllProd();
	
	// 단건조회
	public ProdVO selectProd(ProdVO prodVO);
	
	// 등록
	public int insertProd(ProdVO prodVO);
	
	// 수정
	public Map<String, Object> updateProd(ProdVO prodVO);
	
	// 삭제
	public boolean deleteProd(int prodNo);
}