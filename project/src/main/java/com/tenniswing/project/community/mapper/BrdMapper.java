package com.tenniswing.project.community.mapper;

import java.util.List;

import com.tenniswing.project.community.service.BrdVO;

public interface BrdMapper {
	// 전체조회
	public List<BrdVO> selectBrdAllInfo(BrdVO brdVO);
	
	// 단건조회
	public BrdVO selectBrdInfo(BrdVO brdVO);
	
	// 등록
	public int insertBrd(BrdVO brdVO);
	
	// 수정
	public int updateBrd(BrdVO brdVO);
	
	// 삭제
	public int deleteBrd(int brdWrtNo);
}