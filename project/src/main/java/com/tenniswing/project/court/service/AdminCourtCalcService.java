package com.tenniswing.project.court.service;

import java.util.List;

public interface AdminCourtCalcService {
	//관리자 정산 조회
	public List<CalcTableVO> selectAllAdminCourtCalc(AdminCourtCalcVO adminCalcVO);
	
	//관리자 정산하기
	public int insertAdminCourtCalc(List<CalcTableVO> list);
	
	
}
