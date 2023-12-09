package com.tenniswing.project.admin.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tenniswing.project.court.service.AdminCourtCalcService;
import com.tenniswing.project.court.service.AdminCourtCalcVO;

@Controller
public class AdminCourtController {
	@Autowired
	AdminCourtCalcService adminCalcService;
	
	@GetMapping("admin_Court_Calc")
	public String adminCourtCalcPage(Model model, AdminCourtCalcVO adminCourtCalcVO){
		
		model.addAttribute("calcList", adminCalcService.selectAllAdminCourtCalc(adminCourtCalcVO) );
		
		return "admin/admin_Court_Calc";
	}
}
