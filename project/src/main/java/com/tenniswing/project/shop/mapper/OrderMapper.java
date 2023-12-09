package com.tenniswing.project.shop.mapper;

import com.tenniswing.project.shop.service.OrderTableVO;

public interface OrderMapper {
	// 등록
	public int insertOrder(OrderTableVO orderTableVO);
}
