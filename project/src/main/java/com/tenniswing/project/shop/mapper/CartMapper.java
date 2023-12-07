package com.tenniswing.project.shop.mapper;

import java.util.List;

import com.tenniswing.project.shop.service.CartVO;

public interface CartMapper {
	// 장바구니 리스트
	public List<CartVO> selectAllCart(CartVO cartVO);
	
	// 장바구니 한건
	public CartVO selectCart(CartVO cartVO);
	
	// 장바구니 등록
	public int insertCart(CartVO cartVO);
	
	// 장바구니 수정
	public int updateCart(CartVO cartvo);
}
