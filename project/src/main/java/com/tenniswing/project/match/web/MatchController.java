package com.tenniswing.project.match.web;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenniswing.project.match.service.MatchService;
import com.tenniswing.project.match.service.MatchVO;

@Controller
public class MatchController {
	
	@Autowired
	MatchService matchService;	

	@GetMapping(value = {"/", "/home"})
	public String matchPage(Model model) {
		return "match/match";
	}
	
	@GetMapping("matchList")
	@ResponseBody
	public List<MatchVO> matchAjax(MatchVO matchVO) {		
		return matchService.selectAllMatch(matchVO);
	}
	
	@GetMapping("clubmatch")  
	public String clubmatchPage(Model model) {
		return "match/clubmatch";
	}

	@GetMapping("clubMatchList")
	@ResponseBody
	public List<MatchVO> clubMatchAjax(MatchVO matchVO) {		
		return matchService.selectAllClubMatch(matchVO);
	}	
		
	@GetMapping("contestmatch")  
	public String contestmatchPage(Model model) {
		return "match/contestmatch";
	}
	
	@GetMapping("contMatchList")
	@ResponseBody
	public List<MatchVO> contMatchAjax(MatchVO matchVO) {		
		return matchService.selectAllContMatch(matchVO);
	}
	
	@GetMapping("startermatch")  
	public String startermatchPage(Model model, MatchVO matchVO) {
		return "match/startermatch";
	}
	
	@GetMapping("starterMatchList")
	@ResponseBody
	public List<MatchVO> starterMatchAjax(MatchVO matchVO) {		
		return matchService.selectAllStarterMatch(matchVO);
	}
	
	@GetMapping("matchdetail")  
	public String matchdetailPage(Model model, @RequestParam Integer matchNo) {
		MatchVO matchVO = new MatchVO();
		matchVO.setMatchNo(matchNo);
		model.addAttribute("matchInfo", matchService.selectMatch(matchVO));		
		return "match/matchdetail";
	}
	
	@GetMapping("clubmatchdetail")  
	public String clubmatchdetailPage(Model model, @RequestParam Integer matchNo) {
		MatchVO matchVO = new MatchVO();
		matchVO.setMatchNo(matchNo);
		model.addAttribute("clubMatchInfo", matchService.selectClubMatch(matchVO));		
		return "match/clubmatchdetail";
	}
	
	@GetMapping("contestmatchdetail")  
	public String contestmatchdetailPage(Model model, @RequestParam Integer matchNo) { 	
		MatchVO matchVO = new MatchVO();
		matchVO.setMatchNo(matchNo);
		model.addAttribute("contMatchInfo", matchService.selectContMatch(matchVO));		
		return "match/contestmatchdetail";
	}
	
	@GetMapping("startermatchdetail")  
	public String startermatchdetailPage(Model model, @RequestParam Integer matchNo) {
		MatchVO matchVO = new MatchVO();
		matchVO.setMatchNo(matchNo);
		model.addAttribute("starterMatchInfo", matchService.selectStarterMatch(matchVO));		
		return "match/startermatchdetail";
	}
		
	@GetMapping("matchregi")  
	public String matchregiPage(Model model) { 			
		return "match/matchregi";
	}
	
	//등록 페이지
	@GetMapping("matchregiform")
	public String matchregiformPage(Model model) {
		model.addAttribute("matchVO",new MatchVO());
		return "match/matchregi";
	}
	
	@PostMapping("matchregiform")
	public String insertClubProcess(MatchVO matchVO) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		
		//matchVO.set
		return id;	
	}
}
