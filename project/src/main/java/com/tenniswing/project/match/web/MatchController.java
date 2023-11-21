package com.tenniswing.project.match.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MatchController {
	
	@GetMapping("clubmatch")  
	public String clubmatchPage(Model model) { 			
		return "match/clubmatch";
	}
	
	@GetMapping("contestmatch")  
	public String contestmatchPage(Model model) { 		
		return "match/contestmatch";
	}
	
	@GetMapping("startermatch")  
	public String startermatchPage(Model model) { 			
		return "match/startermatch";
	}
}
