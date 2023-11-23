package com.tenniswing.project;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")  
	public String matchPage(Model model) { 			
		return "match/match";
	}
	
	@GetMapping("/admin")
	public String adminPage(Model model) { 			
		return "admin/dashboard";
	}
	
	@GetMapping("/host")
	public String hostPage(Model model) { 			
		return "host/dashboard";
	}
}

