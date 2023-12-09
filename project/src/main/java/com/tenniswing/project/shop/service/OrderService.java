package com.tenniswing.project.shop.service;

import java.util.List;
import java.util.Map;

public interface OrderService {
	// 전체 조회
	
	// 한건 조회
	
	// 등록
	public Map<String, Object> insertOrder(OrderTableVO orderTableVO);
	
	// 등록 카트에서
	public Map<String, Object> insertCartOrder(OrderTableVO orderTableVO, List<CartVO> cartList);
	
	// 수정 (상태 변경)
}
