package com.tenniswing.project.shop.service;

import java.util.List;
import java.util.Map;

public interface OrderService {
	// 전체 조회 어드민
	public List<OrderTableVO> selectAdminAllOrder(OrderTableVO orderTableVO);
	
	// 한건 조회
	public OrderTableVO selectAdminOrder(int orderNo);
	public OrderTableVO selectAdminOrderPay(int orderNo);
	public List<OrderTableVO> selectAdminOrderProd(int orderNo);
	
	// 등록
	public Map<String, Object> insertOrder(OrderTableVO orderTableVO);
	
	// 등록 카트에서
	public Map<String, Object> insertCartOrder(OrderTableVO orderTableVO, List<CartVO> cartList);
	
	// 수정 (상태 변경)
}
