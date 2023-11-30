package com.tenniswing.project.shop.mapper;

import java.util.List;

import com.tenniswing.project.shop.service.ProdDetailVO;

public interface ProdDetailMapper {

//	전체조회
	public List<ProdDetailVO> selectAllProdDetail();
//	등록
	public int insertProdDetail(ProdDetailVO prodDetailVO);
//	수정
	public int updateProdDetail(ProdDetailVO prodDetailVO);
//	삭제
	public int deleteProdDetail(int prodDetailNo);
}
