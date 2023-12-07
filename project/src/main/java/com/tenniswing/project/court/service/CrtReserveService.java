package com.tenniswing.project.court.service;

import java.util.List;
import java.util.Map;

public interface CrtReserveService {
	// 예약등록
	public Map<String, Object> insertCrtReserve(CrtReserveVO crtReserveVO);
	
	// 예약불가능 리스트 가져오기
	public List<CrtReserveVO> impossibleReserveList(CrtReserveVO crtReserveVO);
	
	// 예약시간 구분코드 가져오기
	public List<CrtReserveVO> reserveTimeCodeList();
	
	// 호스트
		// 호스트별 예약 리스트 조회
		public List<CrtReserveVO> selectAllCrtReserve(String hostId);
}
