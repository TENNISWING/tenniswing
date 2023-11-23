package com.tenniswing.project.club.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClubController {

	@GetMapping("club")  
	public String clubPage(Model model) { 		
		return "club/club";
	}
	
	@GetMapping("clubform")  
	public String clubFormPage(Model model) { 		
		return "club/clubform";
	}
	
	@GetMapping("clubdetail")  
	public String clubDetailPage(Model model) { 		
		return "club/clubdetail";
	}
}
