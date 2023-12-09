package com.tenniswing.project.shop.mapper;

import java.util.List;

import com.tenniswing.project.shop.service.CartVO;
import com.tenniswing.project.shop.service.OrderTableVO;

public interface OrderMapper {
	// 등록
	public int insertOrder(OrderTableVO orderTableVO);
	
	// 등록 카트에서
	public int insertCartOrder(OrderTableVO orderTableVO);

	// 등록 상세 카트에서
	public int insertCartDetailOrder(List<CartVO> cartList);
}
