package com.tenniswing.project.shop.mapper;

import java.util.List;

import com.tenniswing.project.shop.service.ProdVO;

public interface ProdMapper {
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
	public int updateProd(ProdVO prodVO);
	
	// 조회수
	public void updateProdHit(int prodNo);
	
	// 삭제
	public int deleteProd(int prodNo);
	
	// 주문시 재고 처리
	public int updateOrderProd(int prodNo, int prodDetailSto);
	
	// 후기 등록 가능 확인
	public int confirmInsertReview(ProdVO prodVO);
	
	// 후기 등록
	public int insertProdReview(ProdVO prodVO);
	
	// 코트 후기 조회
	public List<ProdVO> selectProdReview(int prodNo);
	
	// 후기 삭제
	public int deleteProdReview(int prodReviewNo);
	
	// 후기 사진 삭제
	public int deleteReviewImg(int prodReviewNo);
	
	// 별점 및 후기수 계산
	public ProdVO prodStar(int prodNo);
}
