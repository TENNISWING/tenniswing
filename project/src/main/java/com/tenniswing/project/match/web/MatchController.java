package com.tenniswing.project.match.web;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tenniswing.project.club.service.ClubVO;
import com.tenniswing.project.court.service.CourtroomService;
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

	// ----------매치----------//
	// 매치 홈
	@GetMapping(value = { "/", "/home" })
	public String matchPage(Model model) {
		List<MatchVO> viewList = matchService.matchRecentView();
		model.addAttribute("viewList", viewList);
		return "match/match";
	}

	@GetMapping("matchList")
	@ResponseBody
	public HashMap<String, Object> matchAjax(MatchVO matchVO) {
		List<MatchVO> list = matchService.selectAllMatch(matchVO);
		HashMap<String, Object> map = new HashMap<>();
		map.put("selectCount", matchService.selectCount(matchVO));
		map.put("matchList", list);
		return map;
	}

	@GetMapping("matchdetail")
	public String matchdetailPage(Model model, MatchVO matchVO) {
		model.addAttribute("matchInfo", matchService.selectMatch(matchVO));
		return "match/matchdetail";
	}

	// 매치 신청하기
	@PostMapping("matchDetailInsert")
	@ResponseBody
	public boolean matchDetailListProcess(Model model, MatchHistVO matchHistVO) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		matchHistVO.setMemId(id);
		int n = matchHistService.insertMatchHist(matchHistVO);
		if (n > 0) {
			return true;
		} else {
			return false;
		}
	}

	// 매치 삭제하기
	@PostMapping("matchDetailDelete")
	@ResponseBody
	public boolean matchDetailDeleteProcess(MatchVO matchVO) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		matchVO.setMemId(id);
		int n = matchService.deleteMatch(matchVO);
		if (n > 0) {
			return true;
		} else {
			return false;
		}
	}
	
		// 매치 수정하기
		@PostMapping("matchUpdate")
		public String matchUpdateProcess(MatchVO matchVO, RedirectAttributes rttr) {
			String id = SecurityContextHolder.getContext().getAuthentication().getName();
			matchVO.setMemId(id);
			int n = matchService.updateMatch(matchVO);
			if (n > 0) {
				rttr.addFlashAttribute("message", "수정되었습니다");
				return "redirect:/";
			} else {
				return "redirect:matchUpdate";
			}
		}

	// 매치 등록페이지
	@GetMapping("matchregi")
	public String matchregiPage(Model model) {
		return "match/matchregi";
	}

	@PostMapping("matchregi")
	public String matchregiProcess(Model model, MatchVO matchVO, RedirectAttributes rttr) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		matchVO.setMemId(id);
		int n = matchService.insertMatch(matchVO);
		if (n > 0) {
			rttr.addFlashAttribute("message", "등록되었습니다!");
			return "redirect:/";
		} else {
			model.addAttribute("msg", "등록 실패");
			return "match/matchregi";
		}
	}

	// 매치 수정페이지
	@GetMapping("matchupdateregi")
	public String matchupdateregiPage(Model model, MatchVO matchVO) {
		model.addAttribute("matchInfo", matchService.selectMatch(matchVO));		
		return "match/matchupdateregi";
	}

	@PostMapping("matchupdateregi")
	public String matchupdateregiProcess(Model model, MatchVO matchVO) {
		System.out.println(matchVO);
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		// matchVO.setMemId(id);
		// 본인여부 체크

		int n = matchService.updateMatch(matchVO);
		if (n > 0) {
			return "redirect:/";
		} else {
			model.addAttribute("msg", "등록실패");
			return "match/matchupdateregi";
		}
	}

	// ----------클럽----------//
	// 클럽 홈
	@GetMapping("clubmatch")
	public String clubmatchPage(Model model) {
		List<MatchVO> viewList = matchService.clubRecentView();
		model.addAttribute("viewList", viewList);
		return "match/clubmatch";
	}

	@GetMapping("clubMatchList")
	@ResponseBody
	public HashMap<String, Object> clubMatchAjax(MatchVO matchVO) {
		List<MatchVO> list = matchService.selectAllClubMatch(matchVO);
		HashMap<String, Object> map = new HashMap<>();
		map.put("selectClubCount", matchService.selectClubCount(matchVO));
		map.put("clubMatchList", list);
		return map;
	}

	@GetMapping("clubmatchdetail")
	public String clubmatchdetailPage(Model model, MatchVO matchVO) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("clubList",matchService.selectMyClub(id));
		model.addAttribute("clubMatchInfo", matchService.selectClubMatch(matchVO));
		return "match/clubmatchdetail";
	}

	// 클럽 매치 신청하기
	@PostMapping("clubDetailInsert")
	@ResponseBody
	public boolean clubDetailInsertProcess(Model model, MatchHistVO matchHistVO) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		matchHistVO.setMemId(id);
		int n = matchHistService.insertClubMatchHist(matchHistVO);
		if (n > 0) {
			return true;
		} else {
			return false;
		}
	}

	// 클럽 매치 삭제하기
	@PostMapping("clubDetailDelete")
	@ResponseBody
	public boolean clubDetailDeleteProcess(MatchVO matchVO) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		matchVO.setMemId(id);
		int n = matchService.deleteClubMatch(matchVO);
		if (n > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	// 클럽 매치 수정하기
	@PostMapping("clubMatchUpdate")
	public String clubMatchUpdateProcess(MatchVO matchVO, RedirectAttributes rttr) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		matchVO.setMemId(id);
		int n = matchService.updateClubMatch(matchVO);
		if (n > 0) {
			rttr.addFlashAttribute("message", "수정되었습니다");
			return "redirect:/";
		} else {
			return "redirect:clubMatchUpdate";
		}
	}

	//클럽 매치 등록
	@GetMapping("clubmatchregi")
	public String clubmatchregiPage(Model model) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		List<ClubVO> list = matchService.selectMyOwnerClub(id);
		if(list.size() == 0) {
			
		}
		model.addAttribute("clubList", );
		
		return "match/clubmatchregi";
	}

	@PostMapping("clubmatchregi")
	public String clubmatchregiProcess(Model model, MatchVO matchVO, RedirectAttributes rttr) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		matchVO.setMemId(id);
		int n = matchService.insertClubMatch(matchVO);
		if (n > 0) {
			rttr.addFlashAttribute("message", "등록되었습니다!");
			return "redirect:/clubmatch";
		} else {
			model.addAttribute("msg", "등록 실패");
			return "match/clubmatchregi";
		}
	}

	// 클럽 매치 수정페이지
	@GetMapping("clubmatchupdateregi")
	public String clubupdateregiPage(Model model, MatchVO matchVO) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("clubMatchInfo", matchService.selectClubMatch(matchVO));
		model.addAttribute("clubList",matchService.selectMyClub(id));
		return "match/clubmatchupdateregi";
	}

	@PostMapping("clubmatchupdateregi")
	public String clubupdateregiProcess(Model model, MatchVO matchVO) {
		System.out.println(matchVO);
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		matchVO.setMemId(id);
		int n = matchService.updateClubMatch(matchVO);
		if (n > 0) {
			return "redirect:/clubmatch";
		} else {
			model.addAttribute("msg", "등록실패");
			return "match/clubupdateregi";
		}
	}

	// ----------대회----------//
	// 대회 홈
	@GetMapping("contestmatch")
	public String contestmatchPage(Model model) {
		List<MatchVO> viewList = matchService.contRecentView();
		model.addAttribute("viewList", viewList);
		return "match/contestmatch";
	}

	@GetMapping("contMatchList")
	@ResponseBody
	public HashMap<String, Object> contMatchAjax(MatchVO matchVO) {
		List<MatchVO> list = matchService.selectAllContMatch(matchVO);
		HashMap<String, Object> map = new HashMap<>();
		map.put("selectContCount", matchService.selectContCount(matchVO));
		map.put("contMatchList", list);
		return map;
	}

	// 대회 매치 신청하기
	@PostMapping("contDetailInsert")
	@ResponseBody
	public boolean contDetailInsertProcess(Model model, MatchHistVO matchHistVO) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		matchHistVO.setMemId(id);
		int n = matchHistService.insertContMatchHist(matchHistVO);
		if (n > 0) {
			return true;
		} else {
			return false;
		}
	}

	// 대회 매치 삭제하기
	@PostMapping("contDetailDelete")
	@ResponseBody
	public boolean contDetailDeleteProcess(MatchVO matchVO) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		matchVO.setMemId(id);
		int n = matchService.deleteContMatch(matchVO);
		if (n > 0) {
			return true;
		} else {
			return false;
		}
	}

	// 대회 매치 등록
	@GetMapping("contestmatchregi")
	public String contmatchregiPage(Model model) {
		return "match/contestmatchregi";
	}

	@PostMapping("contestmatchregi")
	public String contmatchregiProcess(Model model, MatchVO matchVO) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		matchVO.setMemId(id);
		int n = matchService.insertContMatch(matchVO);
		if (n > 0) {
			return "redirect:/";
		} else {
			model.addAttribute("msg", "등록 실패");
			return "match/contestmatchregi";
		}
	}

	@GetMapping("contestmatchdetail")
	public String contestmatchdetailPage(Model model, MatchVO matchVO) {
		model.addAttribute("contMatchInfo", matchService.selectContMatch(matchVO));
		return "match/contestmatchdetail";
	}

	// 대회 매치 수정
	@GetMapping("contmatchupdateregi")
	public String contmatchupdateregiPage(Model model, MatchVO matchVO) {
		model.addAttribute("contMatchInfo", matchService.selectContMatch(matchVO));
		return "match/contmatchupdateregi";
	}
	
	@PostMapping("contmatchupdateregi")
	public String contmatchupdateregiProcess(Model model, MatchVO matchVO) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		//matchVO.setMemId(id);
		//본인여부 체크
		
		int n = matchService.updateContMatch(matchVO);
		if (n > 0) {
			return "redirect:/contestmatch";
		} else {
			model.addAttribute("msg","등록 실패");
			return "match/contmatchupdateregi";
		}
	}

	// ----------스타터----------//
	// 스타터 홈
	@GetMapping("startermatch")
	public String startermatchPage(Model model) {
		List<MatchVO> viewList = matchService.starterRecentView();
		System.out.println(viewList);
		model.addAttribute("viewList", viewList);
		return "match/startermatch";
	}

	@GetMapping("starterMatchList")
	@ResponseBody
	public HashMap<String, Object> starterMatchAjax(MatchVO matchVO) {
		List<MatchVO> list = matchService.selectAllStarterMatch(matchVO);
		HashMap<String, Object> map = new HashMap<>();
		map.put("selectStarterCount", matchService.selectStarterCount(matchVO));
		map.put("starterMatchList", list);
		return map;
	}

	// 스타터 디테일 페이지 신청하기
	@PostMapping("starterDetailInsert")
	@ResponseBody
	public boolean starterMatchDetailInsertProcess(Model model, MatchHistVO matchHistVO) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		matchHistVO.setMemId(id);
		int n = matchHistService.insertStarterMatchHist(matchHistVO);
		if (n > 0) {
			return true;
		} else {
			return false;
		}
	}

	// 스타터 매치 페이지 삭제하기
	@PostMapping("starterDetailDelete")
	@ResponseBody
	public boolean starterDetailDeleteProcess(MatchVO matchVO) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		matchVO.setMemId(id);
		int n = matchService.deleteStarterMatch(matchVO);
		if (n > 0) {
			return true;
		} else {
			return false;
		}
	}

	// 스타터 매치 수정하기
		@PostMapping("starterMatchUpdate")
		public String starterMatchUpdateProcess(MatchVO matchVO, RedirectAttributes rttr) {
			String id = SecurityContextHolder.getContext().getAuthentication().getName();
			matchVO.setMemId(id);
			int n = matchService.updateStarterMatch(matchVO);
			if (n > 0) {
				rttr.addFlashAttribute("message", "수정되었습니다");
				return "redirect:/";
			} else {
				return "redirect:starterMatchUpdate";
			}
		}
	
	@GetMapping("startermatchdetail")
	public String startermatchdetailPage(Model model, MatchVO matchVO) {
		model.addAttribute("starterMatchInfo", matchService.selectStarterMatch(matchVO));
		return "match/startermatchdetail";
	}

	//스타터 매치 등록
	@GetMapping("startermatchregi")
	public String startermatchregiPage(Model model) {
		return "match/startermatchregi";
	}

	@PostMapping("startermatchregi")
	public String startermatchregiProcess(Model model, MatchVO matchVO, RedirectAttributes rttr) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		matchVO.setMemId(id);
		int n = matchService.insertStarterMatch(matchVO);
		if (n > 0) {
			rttr.addFlashAttribute("message", "등록되었습니다!");
			return "redirect:/startermatch";
		} else {
			model.addAttribute("msg", "등록 실패");
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
		if (n > 0) {
			return "redirect:/startermatch";
		} else {
			model.addAttribute("msg", "등록실패");
			return "match/startermatchupdateregi";
		}
	}
}
