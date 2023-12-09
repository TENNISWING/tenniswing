package com.tenniswing.project.shop.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tenniswing.project.shop.mapper.CartMapper;
import com.tenniswing.project.shop.mapper.ProdDetailMapper;
import com.tenniswing.project.shop.service.CartService;
import com.tenniswing.project.shop.service.CartVO;
import com.tenniswing.project.shop.service.ProdDetailVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CartServiceImpl implements CartService{
	@Autowired
	CartMapper cartMapper;
	
	@Autowired
	ProdDetailMapper prodDetailMapper;

	
	// 목록
	@Override
	public List<CartVO> selectAllCart(String memId) {
		CartVO cartVO = new CartVO();
		cartVO.setMemId(memId);
		return cartMapper.selectAllCart(cartVO);
	}
	
	// 체크아웃 목록
	@Override
	public List<CartVO> selectCheckCart(String memId, String type) {
		CartVO cartVO = new CartVO();
		cartVO.setMemId(memId);
		cartVO.setType(type);
		return cartMapper.selectAllCart(cartVO);
	}
	
	// 한건조회
	@Override
	public boolean selectOneCart(CartVO cartvo) {
		boolean isSucess = false;
		if(cartMapper.selectCart(cartvo) != null) {
			isSucess = true;
		}
		return isSucess;
	}
	
	// 등록
	@Override
	public Map<String, Object> insertCart(ProdDetailVO prodDetailVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		CartVO cartVO = new CartVO();
		// 카트 상품 수량
		cartVO.setCartProdQt(prodDetailVO.getProdDetailSto());
		
		// 카트 멤버
		String memId = SecurityContextHolder.getContext().getAuthentication().getName();
		cartVO.setMemId(memId);
		
		prodDetailVO = prodDetailMapper.selectCartProd(prodDetailVO);
		log.warn("========cart===="+prodDetailVO);
		
		// 카트 상품 디테일 번호
		cartVO.setProdDetailNo(prodDetailVO.getProdDetailNo());
		
		boolean isSucess = false;
		// 카트 등록하러 메퍼로 이동
		int result = cartMapper.insertCart(cartVO);
		if(result == 1)
			isSucess = true;
		
		map.put("result", isSucess);
		map.put("cartVO", cartVO);
		return map;
	}

	// 수정
	@Override
	public boolean updateCart(CartVO cartvo) {
		boolean isSucess = false;
		
		if(cartMapper.updateCart(cartvo) == 1) {
			isSucess = true;
		}
		return isSucess;
	}

	// 삭제
	@Override
	public boolean deleteCart(CartVO cartvo) {
		boolean isSucess = false;
		
		if(cartMapper.deleteCart(cartvo) == 1) {
			isSucess = true;
		}
		return isSucess;
	}
	
	

	
}




