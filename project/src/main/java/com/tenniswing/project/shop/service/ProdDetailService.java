package com.tenniswing.project.shop.service;

import java.util.List;
import java.util.Map;

public interface ProdDetailService {
//	전체조회
	public List<ProdDetailVO> selectAllProdDetail(ProdVO prodVO);
//	등록
	public Map<String, Object> insertProdDetail(ProdDetailVO prodDetailVO);
//	수정
	public Map<String, Object> updateProdDetail(ProdDetailVO prodDetailVO);
//	삭제
	public Map<String, Object> deleteProdDetail(int prodDetailNo);
}
