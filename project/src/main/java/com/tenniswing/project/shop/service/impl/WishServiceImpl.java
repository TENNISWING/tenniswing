package com.tenniswing.project.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenniswing.project.shop.mapper.WishMapper;
import com.tenniswing.project.shop.service.WishService;
import com.tenniswing.project.shop.service.WishVO;

@Service
public class WishServiceImpl implements WishService{
	@Autowired
	WishMapper wishMapper;

	// 위시 목록
	@Override
	public List<WishVO> selectAllWish(String memId) {
		return wishMapper.selectAllWish(memId);
	}

	// 위시 한건 조회
	@Override
	public boolean selectOneWish(WishVO wishVO) {
		boolean isSucess = false;
		if(wishMapper.selectOneWish(wishVO) != null)
			isSucess = true;
		return isSucess;
	}

	// 위시 등록
	@Override
	public boolean insertWish(WishVO wishVO) {
		boolean isSucess = false;
		if(wishMapper.insertWish(wishVO) > 0)
			isSucess = true;
		return isSucess;
	}

}
