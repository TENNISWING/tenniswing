package com.tenniswing.project.court.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourtController {
	@GetMapping("court")  
	public String courtPage(Model model) { 			
		return "court/court";
	}
	
	@GetMapping("courtDetail")  
	public String courtDetailPage(Model model) { 			
		return "court/courtDetail";
	}
}
