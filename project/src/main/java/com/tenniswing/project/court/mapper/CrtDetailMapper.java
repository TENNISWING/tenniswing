package com.tenniswing.project.court.mapper;

import java.util.List;

import com.tenniswing.project.attach.service.AttachVO;
import java.util.List;

import com.tenniswing.project.court.service.CrtDetailVO;

public interface CrtDetailMapper {
	// 조회
	public CrtDetailVO selectCrtDetail(CrtDetailVO crtDetailVO);
	
	// 코트 상세 번호로 조회
	public CrtDetailVO selectCrtDetailNo(int crtDetailNo);
	
	// 환불안내
	public CrtDetailVO refundInf();
	
	// 등록
	public int insertCrtDetail(CrtDetailVO crtDetailVO);
	
	// 수정
	public int updateCrtDetail(CrtDetailVO crtDetailVO);
	
	// 삭제
	public int deleteCrtDetail(int crtroomNo);
	
	// 코트 디테일 예약 건수 조회
	public int courtDetailReserveCount(int crtDetailNo);
	
	// 코트 사진 삭제
	public int deleteCrtDetailImg(int crtDetailNo);
	
	// 코트장 내의 코트 사진 카운트
	public int selectCrtDetailImg(int crtroomNo);
}
