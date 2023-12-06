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

import com.tenniswing.project.club.service.ClubService;
import com.tenniswing.project.court.service.CourtroomService;
import com.tenniswing.project.court.service.CrtroomVO;
import com.tenniswing.project.match.service.MatchHistService;
import com.tenniswing.project.match.service.MatchHistVO;
import com.tenniswing.project.match.service.MatchService;
import com.tenniswing.project.match.service.MatchVO;

@Controller
public class MatchController {
	
	@Autowired
	MatchService matchService;	
	@Autowired
	MatchHistService matchHistService;	
	@Autowired
	CourtroomService courtroomService;
	
	
	
	//----------매치----------//
	//매치 홈
	@GetMapping(value = {"/", "/home"})
	public String matchPage(Model model) {
		List<MatchVO> viewList = matchService.matchRecentView();
		System.out.println(viewList);
		model.addAttribute("viewList", viewList);
		return "match/match";
	}
	
	@GetMapping("matchList")
	@ResponseBody
	public HashMap<String,Object> matchAjax(MatchVO matchVO) {
		List<MatchVO> list = matchService.selectAllMatch(matchVO);		
		HashMap<String,Object> map = new HashMap<>();
		map.put("selectCount", matchService.selectCount(matchVO));
		map.put("matchList", list);
		
		return map;
	}
	
	@GetMapping("matchdetail")  
	public String matchdetailPage(Model model, @RequestParam Integer matchNo) {
		MatchVO matchVO = new MatchVO();
		matchVO.setMatchNo(matchNo);
		model.addAttribute("matchInfo", matchService.selectMatch(matchVO));
		return "match/matchdetail";
	}
	
	//매치 디테일 페이지 신청하기
	@PostMapping("matchDetailInsert")
	@ResponseBody
	public boolean matchDetailListProcess(Model model, MatchHistVO matchHistVO) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		matchHistVO.setMemId(id);
		int n = matchHistService.insertMatchHist(matchHistVO);
		if(n>0) {
			return true;
		} else {
			return false;
		}
	}
	
	//매치 디테일 페이지 삭제하기
	@PostMapping("matchDetailDelete")
	@ResponseBody
	public boolean matchDetailDeleteProcess(Model model, MatchVO matchVO) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		matchVO.setMemId(id);
		int n = matchService.deleteMatch(matchVO);
		if(n>0) {
			return true;
		} else {
			return false;
		}
	}
	
	//매치 등록페이지
	@GetMapping("matchregi")  
	public String matchregiPage(Model model) {		
		return "match/matchregi";
	}
	
	@PostMapping("matchregi")  
	public String matchregiProcess(Model model, MatchVO matchVO) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		matchVO.setMemId(id);
		int n = matchService.insertMatch(matchVO);
		if(n>0) {
			return "redirect:/";
		} else {
			model.addAttribute("msg","등록 실패");
			return "match/matchregi";
		}
	}	
	
	//매치 수정페이지
	@GetMapping("matchupdateregi")
	public String matchupdateregiPage(Model model, MatchVO matchVO) {
		model.addAttribute("matchInfo", matchService.selectMatch(matchVO));
		return "match/matchupdateregi";
	}
	
	@PostMapping("matchupdateregi")
	public String matchupdateregiProcess(Model model, MatchVO matchVO) {
		System.out.println(matchVO);
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		matchVO.setMemId(id);
		int n = matchService.updateMatch(matchVO);
		if(n>0) {
			return "redirect:/";
		} else {
			model.addAttribute("msg","등록실패");
			return "match/matchupdateregi";
		}
	}
	
	
	
	//----------클럽----------//
	//클럽 홈
	@GetMapping("clubmatch")  
	public String clubmatchPage(Model model) {
		List<MatchVO> viewList = matchService.clubRecentView();
		model.addAttribute("viewList", viewList);
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
	
	@PostMapping("clubMatchDetailInsert")
	@ResponseBody
	public boolean clubMatchDetailInsertProcess(Model model, MatchHistVO matchHistVO) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		matchHistVO.setMemId(id);
		int n = matchHistService.insertClubMatchHist(matchHistVO);
		if(n>0) {
			return true;
		} else {
			return false;
		}
	}
	
	@GetMapping("clubmatchdetail")  
	public String clubmatchdetailPage(Model model, @RequestParam Integer matchNo) {
		MatchVO matchVO = new MatchVO();
		matchVO.setMatchNo(matchNo);
		model.addAttribute("clubMatchInfo", matchService.selectClubMatch(matchVO));		
		return "match/clubmatchdetail";
	}
	
	@GetMapping("clubmatchregi")  
	public String clubmatchregiPage(Model model) {		
		return "match/clubmatchregi";
	}
	
	@PostMapping("clubmatchregi")  
	public String clubmatchregiProcess(Model model, MatchVO matchVO) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		matchVO.setMemId(id);
		int n = matchService.insertClubMatch(matchVO);
		if(n>0) {
			return "redirect:/";
		} else {
			model.addAttribute("msg","등록 실패");
			return "match/clubmatchregi";
		}
	}
	
	@GetMapping("clubmatchupdateregi")  
	public String clubmatchupdateregiPage(Model model) {		
		return "match/clubmatchupdateregi";
	}
	
	
	
	//----------대회----------//
	//대회 홈
	@GetMapping("contestmatch")  
	public String contestmatchPage(Model model) {
		List<MatchVO> viewList = matchService.contRecentView();
		model.addAttribute("viewList", viewList);
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
	
	@GetMapping("contestmatchregi")
	public String contmatchregiPage(Model model) {		
		return "match/contestmatchregi";
	}
	
	@PostMapping("contestmatchregi")  
	public String contmatchregiProcess(Model model, MatchVO matchVO) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		matchVO.setMemId(id);
		int n = matchService.insertContMatch(matchVO);
		if(n>0) {
			return "redirect:/";
		} else {
			model.addAttribute("msg","등록 실패");
			return "match/contestmatchregi";
		}
	}
	
	@GetMapping("contestmatchdetail")  
	public String contestmatchdetailPage(Model model, @RequestParam Integer matchNo) { 	
		MatchVO matchVO = new MatchVO();
		matchVO.setMatchNo(matchNo);
		model.addAttribute("contMatchInfo", matchService.selectContMatch(matchVO));		
		return "match/contestmatchdetail";
	}
	
	@GetMapping("contmatchupdateregi")  
	public String contmatchupdateregiPage(Model model) {		
		return "match/contmatchupdateregi";
	}
	
	
	
	//----------스타터----------//
	//스타터 홈
	@GetMapping("startermatch")  
	public String startermatchPage(Model model) {
		List<MatchVO> viewList = matchService.starterRecentView();
		System.out.println(viewList);
		model.addAttribute("viewList", viewList);
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
	
	//스타터 디테일 페이지 신청하기
	@PostMapping("starterMatchDetailInsert")
	@ResponseBody
	public boolean starterMatchDetailInsertProcess(Model model, MatchHistVO matchHistVO) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		matchHistVO.setMemId(id);
		int n = matchHistService.insertStarterMatchHist(matchHistVO);
		if(n>0) {
			return true;
		} else {
			return false;
		}
	}
	
	//스타터 디테일 페이지 삭제하기
		@PostMapping("starterDetailDelete")
		@ResponseBody
		public boolean starterDetailDeleteProcess(Model model, MatchVO matchVO) {
			String id = SecurityContextHolder.getContext().getAuthentication().getName();
			matchVO.setMemId(id);
			int n = matchService.deleteStarterMatch(matchVO);
			if(n>0) {
				return true;
			} else {
				return false;
			}
		}
	
	@GetMapping("startermatchdetail")  
	public String startermatchdetailPage(Model model, @RequestParam Integer matchNo) {
		MatchVO matchVO = new MatchVO();
		matchVO.setMatchNo(matchNo);
		model.addAttribute("starterMatchInfo", matchService.selectStarterMatch(matchVO));
		return "match/startermatchdetail";
	}
	
	@GetMapping("startermatchregi")
	public String startermatchregiPage(Model model) {		
		return "match/startermatchregi";
	}
	
	@PostMapping("startermatchregi")  
	public String startermatchregiProcess(Model model, MatchVO matchVO) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		matchVO.setMemId(id);
		int n = matchService.insertStarterMatch(matchVO);
		if(n>0) {
			return "redirect:/";
		} else {
			model.addAttribute("msg","등록 실패");
			return "match/startermatchregi";
		}
	}
	
	@GetMapping("startermatchupdateregi")  
	public String startermatchupdateregiPage(Model model, MatchVO matchVO) {
		model.addAttribute("starterMatchInfo", matchService.selectStarterMatch(matchVO));
		return "match/startermatchupdateregi";
	}
	
	@PostMapping("startermatchupdateregi")
	public String startermatchupdateregiProcess(Model model, MatchVO matchVO) {
		System.out.println(matchVO);
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		matchVO.setMemId(id);
		int n = matchService.updateStarterMatch(matchVO);
		if(n>0) {
			return "redirect:/";
		} else {
			model.addAttribute("msg","등록실패");
			return "match/startermatchupdateregi";
		}
	}	
}
