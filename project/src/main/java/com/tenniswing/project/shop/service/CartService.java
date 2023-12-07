package com.tenniswing.project.shop.service;

import java.util.List;
import java.util.Map;

public interface CartService {
	// 장바구니 리스트
	public List<CartVO> selectAllCart(String memId);

	// 장바구니 한건
	public Map<String, Object> selectAllCart(CartVO cartvo);

	// 장바구니 등록
	public Map<String, Object> insertCart(ProdDetailVO prodDetailVO);

}