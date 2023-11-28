package com.tenniswing.project.court.mapper;

import com.tenniswing.project.court.service.CrtDetailVO;
import com.tenniswing.project.court.service.CrtroomVO;

public interface CrtDetailMapper {
	// 조회
	public CrtDetailVO selectCrtDetail(CrtDetailVO crtDetailVO);
	
	// 코트 상세 번호로 조회
	public CrtDetailVO selectCrtDetailNo(int crtDetailNo);
	
	// 등록
	public int insertCrtDetail(CrtDetailVO crtDetailVO);
	
	// 수정
	public int updateCrtDetail(CrtDetailVO crtDetailVO);
	
	// 삭제
	public int deleteCrtDetail(int crtDetailNo);
}
