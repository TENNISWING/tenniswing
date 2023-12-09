package com.tenniswing.project.admin.web;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenniswing.project.court.service.AdminCourtCalcService;
import com.tenniswing.project.court.service.AdminCourtCalcVO;
import com.tenniswing.project.court.service.CalcTableVO;

@Controller
public class AdminCourtController {
	@Autowired
	AdminCourtCalcService adminCalcService;
	
	@GetMapping("admin_Court_Calc")
	public String adminCourtCalcPage(Model model, AdminCourtCalcVO adminCourtCalcVO){
		return "admin/admin_Court_Calc";
	}
	
	@GetMapping("admin_Court_Calc_Ajax")
	@ResponseBody
	public List<CalcTableVO> adminCourtCalcPr(AdminCourtCalcVO adminCourtCalcVO){
			List<CalcTableVO> calcList = adminCalcService.selectAllAdminCourtCalc(adminCourtCalcVO); 
		return calcList;
	}
	
}
