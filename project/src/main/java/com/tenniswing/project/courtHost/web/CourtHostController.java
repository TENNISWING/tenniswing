package com.tenniswing.project.courtHost.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class CourtHostController {
	@GetMapping("courtRegister")
	public String courtRegisterPage(Model model) {
		return "courtHost/courtRegister";
	}
	
	@GetMapping("hostCourtList")
	public String hostCourtListPage(Model model) {
		return "courtHost/hostCourtList";
	}
}
