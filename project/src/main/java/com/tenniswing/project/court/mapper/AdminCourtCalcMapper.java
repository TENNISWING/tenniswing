package com.tenniswing.project.court.mapper;

import java.util.List;

import com.tenniswing.project.court.service.AdminCourtCalcVO;

public interface AdminCourtCalcMapper {
	//관리자 정산 조회
	public List<AdminCourtCalcVO> selectAllAdminCourtCalc(AdminCourtCalcVO adminCalcVO);
}
