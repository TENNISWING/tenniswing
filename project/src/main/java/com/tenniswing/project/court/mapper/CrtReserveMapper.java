package com.tenniswing.project.court.mapper;

import java.util.List;
import java.util.Map;

import com.tenniswing.project.court.service.CrtReserveVO;

public interface CrtReserveMapper {
	// 예약등록
	public int insertCrtReserve(CrtReserveVO crtReserveVO);
	
	// 예약불가능 리스트 가져오기
	public List<CrtReserveVO> impossibleReserveList(CrtReserveVO crtReserveVO);
	
	// 예약시간 구분코드 가져오기
	public List<CrtReserveVO> reserveTimeCodeList();
}
