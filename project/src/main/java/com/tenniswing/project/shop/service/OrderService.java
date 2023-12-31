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
	
	// 한건 조회 어드민 취소
	public OrderTableVO selectOrder(int orderNo);
	
	// 주문 취소시 결제 취소 테이블 등록
	public int insertPayCancel(PayCancelVO payCancelVO);
	
	// 주문 취소시 수정 (상태 변경)
	public boolean updateOrderState(OrderTableVO orderTable);
	
	// 주문 취소시 order_detail 한건 조회
	public List<OrderDetailVO> selectOrderDetail(int orderNo);
	
	// 주문 취소시 prod_detail 재고 업데이트
	
	// 주문 취소시 prod 재고 업데이트
	
	// 등록
	public Map<String, Object> insertOrder(OrderTableVO orderTableVO);
	
	// 등록 카트에서
	public Map<String, Object> insertCartOrder(OrderTableVO orderTableVO, List<CartVO> cartList);
	
	public int selectAdminAllOrderCount(OrderTableVO orderTableVO);
}
