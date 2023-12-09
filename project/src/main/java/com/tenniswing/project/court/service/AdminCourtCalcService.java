package com.tenniswing.project.court.service;

import java.util.Map;

public interface AdminCourtCalcService {
	//관리자 정산 조회
	public Map<String, Object>selectAllAdminCourtCalc(AdminCourtCalcVO adminCalcVO);
}
