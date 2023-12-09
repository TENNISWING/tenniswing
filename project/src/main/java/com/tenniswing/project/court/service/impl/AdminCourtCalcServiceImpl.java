package com.tenniswing.project.court.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenniswing.project.court.mapper.AdminCourtCalcMapper;
import com.tenniswing.project.court.service.AdminCourtCalcService;
import com.tenniswing.project.court.service.AdminCourtCalcVO;
import com.tenniswing.project.court.service.CalcTableVO;
import com.tenniswing.project.member.mapper.MemberMapper;
import com.tenniswing.project.member.service.MemberVO;

@Service
public class AdminCourtCalcServiceImpl implements AdminCourtCalcService {
	
	@Autowired
	AdminCourtCalcMapper adminCourtCalcMapper;
	@Autowired
	MemberMapper memberMapper;
	
	// 호스트 정산 조회
	@Override
	public List<CalcTableVO> selectAllAdminCourtCalc(AdminCourtCalcVO adminCalcVO) {
		int total = 0;
		int cancelTotal = 0;
		//Map<String, Object> map = new HashMap<String, Object>();
		List<MemberVO> hostList = memberMapper.searchHost();
		List<CalcTableVO> calcList = new ArrayList<>();
		
		// 날짜 화면에서 받아와서 vo에 set해주기
		int i = 0;
		for(MemberVO host: hostList) {
			AdminCourtCalcVO vo = new AdminCourtCalcVO();
			vo.setHostId(host.getMemId());
			List<AdminCourtCalcVO> list = adminCourtCalcMapper.selectAllAdminCourtCalc(vo);
			CalcTableVO calcT = new CalcTableVO();
			
			for(AdminCourtCalcVO calc : list){
				 if(calc.getReserveState().equals("i2")){
				        total += calc.getReservePrice();
			     }else{
			        total += calc.getReservePrice() - calc.getRefundPrice();
			        cancelTotal += calc.getRefundPrice();
			     }
			}
			
			double comm = total * 0.1;
			Integer calcTotal = total - (int) comm;
			
			System.out.println(host.getMemId());
			calcT.setCalcNo(i+1);
			calcT.setHostId(host.getMemId());
			calcT.setHostName(host.getName());
			calcT.setTotalPayPrice(total);
			calcT.setCancelPrice(cancelTotal);
			calcT.setCalcPrice(calcTotal);
			calcT.setDepositor(host.getHostDepositor());
			calcT.setBank(host.getHostBank());
			calcT.setAccountNo(host.getHostActNo());
			calcT.setCalcState("bi1");
			calcList.add(i, calcT);
			
			
			total =0;
			cancelTotal=0;
			//System.out.println(map);
			i++;
		}
		System.out.println("정산리스트 >>>> "+calcList);
		return calcList;
	}
}
