package com.tenniswing.project.match.web;


import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenniswing.project.match.service.MatchService;
import com.tenniswing.project.match.service.MatchVO;

@Controller
public class MatchController {
	
	@Autowired
	MatchService matchService;	

	@GetMapping(value = {"/", "/home"})

	public String matchPage(Model model) { 
		//System.out.println(principal.getName());		
		//model.addAttribute("matchList", matchService.selectAllMatch(null));

		return "match/match";
	}
	
	@GetMapping("match")
	@ResponseBody
	public List<MatchVO> matchPageAjax(MatchVO matchVO) {		
		return matchService.selectAllMatch(matchVO);
	}
	
	@GetMapping("clubmatch")  
	public String clubmatchPage(Model model) {
		model.addAttribute("clubMatchList", matchService.selectAllClubMatch());
		return "match/clubmatch";
	}
	
	@GetMapping("contestmatch")  
	public String contestmatchPage(Model model) {
		model.addAttribute("contestMatchList", matchService.selectAllContMatch());
		return "match/contestmatch";
	}
	
	@GetMapping("startermatch")  
	public String startermatchPage(Model model) {
		model.addAttribute("starterMatchList", matchService.selectAllStarterMatch());
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
	
	@GetMapping("contestmatchdetail")  
	public String contestmatchdetailPage(Model model, MatchVO matchVO) { 	
		//System.out.println(matchVO.getMatchNo());
		model.addAttribute("contestMatchInfo", matchService.selectContMatch(matchVO));		
		return "match/contestmatchdetail";
	}
	
	@GetMapping("startermatchdetail")  
	public String startermatchdetailPage(Model model, MatchVO matchVO) { 	
		//System.out.println(matchVO.getMatchNo());
		model.addAttribute("starterMatchInfo", matchService.selectStarterMatch(matchVO));		
		return "match/startermatchdetail";
	}
		
	@GetMapping("matchregi")  
	public String matchregiPage(Model model) { 			
		return "match/matchregi";
	}
}
