package com.tenniswing.project.shop.mapper;

import java.util.List;

import com.tenniswing.project.shop.service.ProdVO;

public interface ProdMapper {
	// 전체조회
	public List<ProdVO> selectAllProd();
	
	// 단건조회
	public ProdVO selectProd(ProdVO prodVO);
	
	// 등록
	public int insertProd(ProdVO prodVO);
	
	// 수정
	public int updateProd(ProdVO prodVO);
	
	// 삭제
	public int deleteProd(int prodNo);
}
