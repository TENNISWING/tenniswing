package com.tenniswing.project.match.web;


import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenniswing.project.court.service.CourtroomService;
import com.tenniswing.project.court.service.CrtroomVO;
import com.tenniswing.project.match.service.MatchService;
import com.tenniswing.project.match.service.MatchVO;

@Controller
public class MatchController {
	
	@Autowired
	MatchService matchService;	
	
	@Autowired
	CourtroomService courtroomService;

	@GetMapping(value = {"/", "/home"})
	public String matchPage(Model model) {
		return "match/match";
	}
	
	@GetMapping("matchList")
	@ResponseBody
	public HashMap<String,Object> matchAjax(MatchVO matchVO) {
		List<MatchVO> list = matchService.selectAllMatch(matchVO);
		HashMap<String,Object> map = new HashMap<>();
		map.put("selectCount", matchService.selectCount(matchVO));
		map.put("matchList", matchService.selectAllMatch(matchVO));
		return map;
	}
	
	@GetMapping("clubmatch")  
	public String clubmatchPage(Model model) {
		return "match/clubmatch";
	}

	@GetMapping("clubMatchList")
	@ResponseBody
	public HashMap<String,Object> clubMatchAjax(MatchVO matchVO) {
		List<MatchVO> list = matchService.selectAllClubMatch(matchVO);
		HashMap<String,Object> map = new HashMap<>();
		map.put("selectClubCount", matchService.selectClubCount(matchVO));
		map.put("clubMatchList", matchService.selectAllClubMatch(matchVO));
		return map;
	}	
		
	@GetMapping("contestmatch")  
	public String contestmatchPage(Model model) {
		return "match/contestmatch";
	}
	
	@GetMapping("contMatchList")
	@ResponseBody
	public HashMap<String,Object> contMatchAjax(MatchVO matchVO) {
		List<MatchVO> list = matchService.selectAllContMatch(matchVO);
		HashMap<String,Object> map = new HashMap<>();
		map.put("selectContCount", matchService.selectContCount(matchVO));
		map.put("contMatchList", matchService.selectAllContMatch(matchVO));
		return map;
	}
	
	@GetMapping("startermatch")  
	public String startermatchPage(Model model, MatchVO matchVO) {
		return "match/startermatch";
	}
	
	@GetMapping("starterMatchList")
	@ResponseBody
	public HashMap<String,Object> starterMatchAjax(MatchVO matchVO) {
		List<MatchVO> list = matchService.selectAllStarterMatch(matchVO);
		HashMap<String,Object> map = new HashMap<>();
		map.put("selectStarterCount", matchService.selectStarterCount(matchVO));
		map.put("starterMatchList", matchService.selectAllStarterMatch(matchVO));
		return map;
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
	
	/*
	 * @GetMapping("searchCourt")
	 * 
	 * @ResponseBody public HashMap<String,Object> searchCourtAjax() {
	 * List<CrtroomVO> list = courtroomService.selectAllCourtroomMain();
	 * HashMap<String,Object> map = new HashMap<>(); map.put("searchCourtList",
	 * courtroomService.searchCourtList()); map.put("starterMatchList",
	 * courtroomService.selectAllCourtroomMain()); return map; }
	 */
	@PostMapping("matchregiform")
	public String insertClubProcess(MatchVO matchVO) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		
		//matchVO.set
		return id;	
	}
}
