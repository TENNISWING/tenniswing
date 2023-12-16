package com.tenniswing.project.court.mapper;

import java.util.List;

import com.tenniswing.project.court.service.AdminCourtCalcVO;
import com.tenniswing.project.court.service.CalcTableVO;

public interface AdminCourtCalcMapper {
	//관리자 정산 조회
	public List<AdminCourtCalcVO> selectAllAdminCourtCalc(AdminCourtCalcVO adminCalcVO);
	
	//관리자 정산하기
	public int insertAdminCourtCalc(List<CalcTableVO> list);
	
	//정산 내역 확인
	public int selectHostCourtCalc(AdminCourtCalcVO adminCalcVO);
	
	//관리자 정산 내역 조회
	public List<CalcTableVO> selectPreviousCourtCalc(CalcTableVO calcTableVO);
}
