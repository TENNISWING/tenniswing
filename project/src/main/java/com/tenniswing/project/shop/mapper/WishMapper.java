package com.tenniswing.project.shop.mapper;

import java.util.List;

import com.tenniswing.project.shop.service.WishVO;

public interface WishMapper {
	// 위시 리스트
	public List<WishVO> selectAllWish(String memId);
	
	// 위시 한건
	public WishVO selectOneWish(WishVO wishVO);
	
	// 위치 등록
	public int insertWish(WishVO wishVO);
	
	// 위시 삭제
}
