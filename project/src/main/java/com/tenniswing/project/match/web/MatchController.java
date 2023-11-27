package com.tenniswing.project.match.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tenniswing.project.match.service.MatchService;
import com.tenniswing.project.match.service.MatchVO;

@Controller
public class MatchController {
	
	@Autowired
	MatchService matchService;
	
	@GetMapping(value = {"/", "/home"})
	public String matchPage(Model model) { 	
		model.addAttribute("matchList", matchService.selectAllMatch());
		return "match/match";
	}
	
	@GetMapping("clubmatch")  
	public String clubmatchPage(Model model) {
		model.addAttribute("clubMatchList", matchService.selectAllClubMatch());
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
	
	@GetMapping("matchdetail")  
	public String matchdetailPage(Model model, MatchVO matchVO) { 	
		//System.out.println(matchVO.getMatchNo());
		model.addAttribute("matchInfo", matchService.selectMatch(matchVO));		
		return "match/matchdetail";
	}
	
	@GetMapping("clubmatchdetail")  
	public String clubmatchdetailPage(Model model, MatchVO matchVO) { 	
		//System.out.println(matchVO.getMatchNo());
		model.addAttribute("clubMatchInfo", matchService.selectClubMatch(matchVO));		
		return "match/clubmatchdetail";
	}
	
	@GetMapping("matchregi")  
	public String matchregiPage(Model model) { 			
		return "match/matchregi";
	}
}
