package com.tenniswing.project.shop.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenniswing.project.shop.mapper.OrderMapper;
import com.tenniswing.project.shop.mapper.ProdDetailMapper;
import com.tenniswing.project.shop.mapper.ProdMapper;
import com.tenniswing.project.shop.service.CartVO;
import com.tenniswing.project.shop.service.OrderService;
import com.tenniswing.project.shop.service.OrderTableVO;
import com.tenniswing.project.shop.service.ProdVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderMapper orderMapper; 
	
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
		boolean isSuccessed = false;
		
		int prodNo = orderTableVO.getProdNo();
		int prodDetailNo = orderTableVO.getProdDetailNo();
		int prodDetailSto = orderTableVO.getProdDetailSto();
		log.warn("===주문 들어오고 3개 변수==="+prodNo+"==="+prodDetailNo+"==="+prodDetailSto);
		
		// 주문이 들어오면 order_table, order_detail, pay에 insert 발생
		int result = orderMapper.insertCartOrder(orderTableVO);
		
		for (CartVO file : cartList) {
			file.setOrderNo(orderTableVO.getOrderNo());
		}
		log.warn("====cartList=====", cartList);
		result += orderMapper.insertCartDetailOrder(cartList);
		log.warn("===주문 들어오고 3개 테이블 insert==="+result);
		log.warn("===주문 들어오고 3개 테이블 insert==="+orderTableVO);
		
		// 장바구니 비워주기
		
		
		// prod_detail에서 재고 마이너스, prod에서 재고 마이너스
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
	

}
