package com.tenniswing.project.court.service;

import java.util.Map;

public interface CrtReserveService {
	// 예약등록
	public Map<String, Object> insertCrtReserve(CrtReserveVO crtReserveVO);
}
