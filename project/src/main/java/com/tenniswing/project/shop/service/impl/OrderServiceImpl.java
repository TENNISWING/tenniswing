package com.tenniswing.project.shop.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenniswing.project.shop.mapper.OrderMapper;
import com.tenniswing.project.shop.mapper.ProdDetailMapper;
import com.tenniswing.project.shop.mapper.ProdMapper;
import com.tenniswing.project.shop.service.OrderService;
import com.tenniswing.project.shop.service.OrderTableVO;
import com.tenniswing.project.shop.service.ProdVO;

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
		
		// 주문이 들어오면 order_table, order_detail, pay에 insert 발생
		int result = orderMapper.insertOrder(orderTableVO);

		// prod_detail에서 재고 마이너스, prod에서 재고 마이너스
		int prodNo = orderTableVO.getProdNo();
		int prodDetailNo = orderTableVO.getProdDetailNo();
		int prodDetailSto = orderTableVO.getProdDetailSto();
		
		result += prodDetailMapper.updateOrderProdDetail(prodDetailNo, prodDetailSto);
		
		int prodTSto = prodDetailMapper.selectSumOrderProdNo(prodNo);
		prodVO.setProdTSto(prodTSto);
		result += prodMapper.updateProd(prodVO);
		
		if(result > 0) {
			isSuccessed = true;
		}
		map.put("result", isSuccessed);
		map.put("orderTableVO", orderTableVO);
		
		return map;
	}

}
