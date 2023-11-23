package com.tenniswing.project.court.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.tenniswing.project.court.service.CourtroomService;
import com.tenniswing.project.court.service.CrtroomVO;


@Controller
public class CourtController {
	
	@Autowired
	CourtroomService courtroomService;
	
	// 메인
		@GetMapping("court")  
		public String courtPage(Model model) { 			
			return "court/court";
		}
		
		@GetMapping("courtDetail")  
		public String courtDetailPage(Model model) { 			
			return "court/courtDetail";
		}
		
		@GetMapping("reserveCourt")  
		public String reserveCourtPage(Model model) { 			
			return "court/reserveCourt";
		}
	
	// 호스트
		// 목록
		@GetMapping("hostCourtList")
		public String hostCourtListPage(Model model) {
			return "courtHost/hostCourtList";
		}
			
		// 등록 - 페이지
		@GetMapping("courtRegister")
		public String insertCourtroomForm(Model model) {
			model.addAttribute("crtroomVO", new CrtroomVO());
			return "courtHost/courtRegister";
		}
		
		// 등록
		@PostMapping("courtRegister")
		public String insertCourtroomProcess(CrtroomVO crtroomVO) {
			courtroomService.insertCourtroom(crtroomVO);
			
			return "redirect:hostCourtList";
		}
}
