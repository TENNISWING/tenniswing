package com.tenniswing.project.shop.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenniswing.project.shop.mapper.CartMapper;
import com.tenniswing.project.shop.mapper.OrderMapper;
import com.tenniswing.project.shop.mapper.ProdDetailMapper;
import com.tenniswing.project.shop.mapper.ProdMapper;
import com.tenniswing.project.shop.service.CartVO;
import com.tenniswing.project.shop.service.OrderDetailVO;
import com.tenniswing.project.shop.service.OrderService;
import com.tenniswing.project.shop.service.OrderTableVO;
import com.tenniswing.project.shop.service.PayCancelVO;
import com.tenniswing.project.shop.service.ProdVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderMapper orderMapper; 
	
	@Autowired
	CartMapper cartMapper;
	
	@Autowired
	ProdMapper prodMapper;
	
	@Autowired
	ProdDetailMapper prodDetailMapper;
	
	// 등록
	@Override
	public Map<String, Object> insertOrder(OrderTableVO orderTableVO) {
		Map<String, Object> map = new HashMap<>();
		ProdVO prodVO = new ProdVO();
		boolean isSuccessed = false;
		
		// prod_detail에서 재고 마이너스, prod에서 재고 마이너스
		int prodNo = orderTableVO.getProdNo();
		int prodDetailNo = orderTableVO.getProdDetailNo();
		int prodDetailSto = orderTableVO.getProdDetailSto();
		log.warn("===주문 들어오고 3개 변수==="+prodNo+"==="+prodDetailNo+"==="+prodDetailSto);
		
		// 주문이 들어오면 order_table, order_detail, pay에 insert 발생
		int result = orderMapper.insertOrder(orderTableVO);
		log.warn("===주문 들어오고 3개 테이블 insert==="+result);
		log.warn("===주문 들어오고 3개 테이블 insert==="+orderTableVO);
		
		result += prodDetailMapper.updateOrderProdDetail(prodDetailNo, prodDetailSto);
		log.warn("===주문 들어오고 1개 테이블 updateOrderProdDetail==="+result);
		
		int prodTSto = prodDetailMapper.selectSumOrderProdNo(prodNo);
		log.warn("===주문 들어오고 selectSumOrderProdNo==="+prodTSto);
		prodVO.setProdNo(prodNo);
		prodVO.setProdTSto(prodTSto);
		result += prodMapper.updateProd(prodVO);
		log.warn("===주문 들어오고 1개 테이블 updateProd==="+result);
		
		if(result > 0) {
			isSuccessed = true;
		}
		map.put("result", isSuccessed);
		map.put("orderTableVO", orderTableVO);
		
		return map;
	}

	
	// 등록 카트에서
	@Override
	public Map<String, Object> insertCartOrder(OrderTableVO orderTableVO, List<CartVO> cartList) {
		Map<String, Object> map = new HashMap<>();
		ProdVO prodVO = new ProdVO();
		//List<CartVO> tempCartVO = new ArrayList<CartVO>();
		boolean isSuccessed = false;
		
		//int prodNo = orderTableVO.getProdNo();
		//int prodDetailNo = orderTableVO.getProdDetailNo();
		//int prodDetailSto = orderTableVO.getProdDetailSto();
		//log.warn("===주문 들어오고 3개 변수==="+prodNo+"==="+prodDetailNo+"==="+prodDetailSto);
		log.warn("===주문 들어오고 3개 변수==="+orderTableVO);
		// 주문이 들어오면 order_table, order_detail, pay에 insert 발생
		int result = orderMapper.insertCartOrder(orderTableVO);
		log.warn("===주문 들어오고 2개 테이블 insert==="+result);
		log.warn("===주문 들어오고 2개 테이블 insert==="+orderTableVO);
		
		for (CartVO vo : cartList) {
			vo.setOrderNo(orderTableVO.getOrderNo());
		}
		/*tempCartVO = cartList;*/
		result += orderMapper.insertCartDetailOrder(cartList);
		log.warn("===주문 들어오고 1개 테이블 insert==="+result);
		for (CartVO vo : cartList) {
			log.warn("====cartList=====", vo);
		}		
		
		// 장바구니 비워주기
		for (CartVO delVO : cartList) {
			cartMapper.deleteCart(delVO);
			int prodNo = delVO.getProdNo();
			int prodDetailNo = delVO.getProdDetailNo();
			int prodDetailSto = delVO.getCartProdQt();
			
			prodDetailMapper.updateOrderProdDetail(prodDetailNo, prodDetailSto);
			int prodTSto = prodDetailMapper.selectSumOrderProdNo(prodNo);
			
			prodVO.setProdNo(prodNo);
			prodVO.setProdTSto(prodTSto);
			prodMapper.updateProd(prodVO);
		}
		
		// prod_detail에서 재고 마이너스, prod에서 재고 마이너스
		
		/*
		 * log.warn("===주문 들어오고 1개 테이블 updateOrderProdDetail==="+result);
		 * 
		 * log.warn("===주문 들어오고 selectSumOrderProdNo==="+prodTSto);
		 * 
		 * log.warn("===주문 들어오고 1개 테이블 updateProd==="+result);
		 */
		
		if(result > 0) {
			isSuccessed = true;
		}
		map.put("result", isSuccessed);
		map.put("orderTableVO", orderTableVO);
		
		return map;
	}

	// 전체 조회 어드민
	@Override
	public List<OrderTableVO> selectAdminAllOrder(OrderTableVO orderTableVO) {
		return orderMapper.selectAdminAllOrder(orderTableVO);
	}


	// 한건 조회 어드민
	@Override
	public OrderTableVO selectAdminOrder(int orderNo) {
		//log.warn("=====한건 조회====="+orderMapper.selectAdminOrder(orderNo));
		return orderMapper.selectAdminOrder(orderNo);
	}

	@Override
	public OrderTableVO selectAdminOrderPay(int orderNo) {
		return orderMapper.selectAdminOrderPay(orderNo);
	}


	@Override
	public List<OrderTableVO> selectAdminOrderProd(int orderNo) {
		return orderMapper.selectAdminOrderProd(orderNo);
	}

	
	// 한건 조회 취소 어드민
	@Override
	public OrderTableVO selectOrder(int orderNo) {
		return orderMapper.selectOrder(orderNo);
	}

	// 주문 취소 결제 취소 INSERT
	@Override
	public int insertPayCancel(PayCancelVO payCancelVO) {
		return orderMapper.insertPayCancel(payCancelVO);
	}

	// 주문 취소 주문 상태 update
	@Override
	public boolean updateOrderState(OrderTableVO orderTable) {
		boolean isSucess = false;
		int result = orderMapper.updateOrderState(orderTable);
		if(result > 0) {
			isSucess = true;
		}
		return isSucess;  
	}
	
	// 주문 취소 order_detail 한건 조회
	@Override
	public List<OrderDetailVO> selectOrderDetail(int orderNo) {
		return orderMapper.selectOrderDetail(orderNo);
	}

	@Override
	public int selectAdminAllOrderCount(OrderTableVO orderTableVO) {
		return orderMapper.selectAdminAllOrderCount(orderTableVO);
	}
	
	
}
