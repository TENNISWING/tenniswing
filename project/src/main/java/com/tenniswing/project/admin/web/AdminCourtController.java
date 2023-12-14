package com.tenniswing.project.admin.web;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenniswing.project.court.service.AdminCourtCalcService;
import com.tenniswing.project.court.service.AdminCourtCalcVO;
import com.tenniswing.project.court.service.CalcTableVO;
import com.tenniswing.project.court.service.CourtroomService;
import com.tenniswing.project.court.service.CrtroomVO;

@Controller
public class AdminCourtController {
	@Autowired
	AdminCourtCalcService adminCalcService;
	@Autowired
	CourtroomService courtroomService;
	

	@PostMapping("admin_CourtApply_proccess")
	@ResponseBody
	public int courtStateProccess(CrtroomVO crtroomVO, int number) {
		int result = 0;
		if(number == 1) {
			result = courtroomService.courtStatePermit(crtroomVO);
		}else {
			result = courtroomService.courtStateNotPermit(crtroomVO);
		}
		
		if(result > 0) {
			return number;
		}else {
			return -1;
		}
	}	

}
