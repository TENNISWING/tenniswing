package com.tenniswing.project.court.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
			
			LocalDate date = LocalDate.now();
			int year = date.getYear();
			int monthValue = date.getMonthValue()-1;
			int lastDay = 0;
			Calendar cal = Calendar.getInstance();
			cal.set(year,monthValue-1,1);
			lastDay = cal.getActualMaximum(Calendar.DATE);
			
			//System.out.println("날짜계산 >>>> "+year+'-'+monthValue+'-'+lastDay);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String start = year+"-"+monthValue+"-"+"01";
			String end = year+"-"+monthValue+"-"+lastDay;
			Date startPar = null;
			Date endPar = null;
			try {
				startPar = format.parse(start);
				endPar = format.parse(end);
				System.out.println("날짜 찍힘?"+startPar+endPar);
			} catch (ParseException e) {
				e.printStackTrace();
			} 
			vo.setCalcStartDate(start);
			vo.setCalcEndDate(end);
			System.out.println("날짜 >>>>>>>>>>> "+start+end);
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
			//calcT.setCalcNo(i+1);
			calcT.setHostId(host.getMemId());
			calcT.setHostName(host.getName());
			calcT.setPayPrice(total);
			calcT.setCancelPrice(cancelTotal);
			calcT.setCalcPrice(calcTotal);
			calcT.setCalcComm(comm);
			calcT.setDepositor(host.getHostDepositor());
			calcT.setBank(host.getHostBank());
			calcT.setAccountNo(host.getHostActNo());
			calcT.setCalcState("bi1");
			calcT.setPayCase(list.size());
			calcT.setCalcStartDate(startPar);
			calcT.setCalcEndDate(endPar);
			calcList.add(i, calcT);
			
			
			total =0;
			cancelTotal=0;
			//System.out.println(map);
			i++;
		}
		System.out.println("정산리스트 >>>> "+calcList);
		return calcList;
	}

	@Override
	public int insertAdminCourtCalc(List<CalcTableVO> list) {
		return adminCourtCalcMapper.insertAdminCourtCalc(list);
	}
}
