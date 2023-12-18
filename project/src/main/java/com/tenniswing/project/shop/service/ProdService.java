package com.tenniswing.project.shop.service;

import java.util.List;
import java.util.Map;

import com.tenniswing.project.attach.service.AttachVO;

public interface ProdService {
	// 전체조회
	public List<ProdVO> selectAllProd(ProdVO prodVO);
	
	// 어드민 전체조회
	public List<ProdVO> selectAdminAllProd(ProdVO prodVO);
	
	public int selectAdminAllCount(ProdVO prodVO);
	
	// 페이징 전체 갯수
	public int selectCount(ProdVO prodVO);
	
	// 최근 상품 스와이퍼
	public List<ProdVO> selectSwiperProd();
	
	// 최근 상품 스와이퍼
	public List<ProdVO> relatedSwiperProd(ProdVO prodVO);
	
	// 단건조회
	public ProdVO selectProd(ProdVO prodVO);
	
	// 등록
	public int insertProd(ProdVO prodVO);
	
	// 수정
	public Map<String, Object> updateProd(ProdVO prodVO);
	
	// 조회수
	public void updateProdHit(int hit);
	
	// 삭제
	public Map<String, Object> deleteProd(int prodNo);
	
	// 후기 등록 권한여부
	public Integer confirmInsertReview(ProdVO prodVO);
	
	// 코트 후기 등록
	public int insertProdReview(ProdVO prodVO, List<AttachVO> files);
	
	// 코트 후기 조회
	public List<ProdVO> selectProdReview(int prodNo);
	
	// 후기 삭제
	public boolean deleteProdReview(int prodReviewNo);
	
	// 별점 및 후기수 계산
	public ProdVO prodStar(int prodNo);
}
