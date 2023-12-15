package com.tenniswing.project.shop.service;

import java.util.List;

public interface WishService {
	// 위시 리스트
	public List<WishVO> selectAllWish(String memId);
	
	// 위시 한건
	public boolean selectOneWish(WishVO wishVO);
	
	// 위치 등록
	public boolean insertWish(WishVO wishVO);
	
	// 위시 삭제
}
