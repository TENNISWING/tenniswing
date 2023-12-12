package com.tenniswing.project.shop.mapper;

import java.util.List;

import com.tenniswing.project.shop.service.CartVO;
import com.tenniswing.project.shop.service.OrderDetailVO;
import com.tenniswing.project.shop.service.OrderTableVO;
import com.tenniswing.project.shop.service.PayCancelVO;

public interface OrderMapper {
	// 전체 조회 어드민
	public List<OrderTableVO> selectAdminAllOrder(OrderTableVO orderTableVO);
	
	// 한건 조회 어드민
	public OrderTableVO selectAdminOrder(int orderNo);
	public OrderTableVO selectAdminOrderPay(int orderNo);
	public List<OrderTableVO> selectAdminOrderProd(int orderNo);
	
	// 한건 조회 어드민 취소
	public OrderTableVO selectOrder(int orderNo);
	
	// 주문 취소시 결제 취소 테이블 등록
	public int insertPayCancel(PayCancelVO payCancelVO);
	
	// 주문 취소시 수정 (상태 변경)
	public int updateOrderState(OrderTableVO orderTable);
	
	// 주문 취소시 order_detail 한건 조회
	public List<OrderDetailVO> selectOrderDetail(int orderNo);
	
	// 등록
	public int insertOrder(OrderTableVO orderTableVO);
	
	// 등록 카트에서
	public int insertCartOrder(OrderTableVO orderTableVO);

	// 등록 상세 카트에서
	public int insertCartDetailOrder(List<CartVO> cartList);
}
