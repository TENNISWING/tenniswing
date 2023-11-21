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
	
	@GetMapping("court")  
	public String courtPage(Model model) { 			
		return "court/court";
	}
	
	@GetMapping("club")  
	public String clubPage(Model model) { 		
		return "club/club";
	}
	
	@GetMapping("sns")  
	public String communityPage(Model model) { 			
		return "community/community";
	}
	
}
