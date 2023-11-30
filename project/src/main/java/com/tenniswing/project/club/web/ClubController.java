package com.tenniswing.project.club.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenniswing.project.club.service.ClubService;
import com.tenniswing.project.club.service.ClubVO;

@Controller
public class ClubController {
	
	@Autowired
	ClubService clubService;

// --------------------------------------- 메인	
	@GetMapping("club")  
	public String clubPage(Model model) { 
		//model.addAttribute("clubList", clubService.selectAllClub());
		return "club/club";
	}
	
	
	 @GetMapping("clubList")
	 @ResponseBody 
	 public List<ClubVO> clubPageAjax(ClubVO clubVO) { 
		 return clubService.selectAllClub(clubVO);
		 }
	 
	
	@GetMapping("clubform")  //등록 페이지
	public String clubFormPage(Model model) { 	
	    //처음 입력 폼은 모두 비어져야해서 빈 객체(new new ClubVO()) 전달
		model.addAttribute("clubVO",new ClubVO());
		return "club/clubform";
	}
	
	@PostMapping("clubform") //등록 프로세스
	public String insertClubProcess(ClubVO clubVO) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		
		clubVO.setMemId(id);
		clubService.insertClub(clubVO);
		return "redirect:club";
	}
	
// ---------------------------------------상세페이지
	
	//단건조회
	@GetMapping("clubdetail") 
	public String clubDetailPage(Model model, ClubVO clubVO) { 
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		
		clubVO.setMemId(id);
		model.addAttribute("club",clubService.selectClub(clubVO));
		return "club/clubdetail";
	}
	
	@GetMapping("clubInfo")  //상세페이지 > 탭 > 클럽정보
	public String infoTapPage(Model model, ClubVO clubVO) { 
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		clubVO.setMemId(id);
		model.addAttribute("club",clubService.selectClub(clubVO));
		
		//System.out.println("==================="+ clubVO);
		return "club/clubInfo";
	}
	
	@PostMapping("clubdelete") // 클럽 삭제
	@ResponseBody
	public boolean deleteClubAjax(@RequestParam("paramclubNo") Integer clubNo) {
		boolean result = clubService.deleteClub(clubNo);
		return result;
	}
	
	//클럽 수정
	@GetMapping("clubUpdate")
	@ResponseBody
	public Map<String, Object> clubUpdateFormAjax(ClubVO clubVO) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		clubVO.setMemId(id);
		System.out.println("-----수정아작스컨트롤러 "+clubVO);
		return clubService.updateClub(clubVO);
	}
	
	
	@GetMapping("clubMatchJoin")  //상세페이지 > 탭 > 매치모집
	public String joinTapPage(Model model) { 		
		return "club/clubMatchJoin";
	}
	
	@GetMapping("clubMatchDate")  //상세페이지 > 탭 > 매치일정
	public String DateTapPage(Model model) { 		
		return "club/clubMatchDate";
	}
	
	@GetMapping("clubMatchEnd")  //상세페이지 > 탭 > 매치결과
	public String EndTapPage(Model model) { 		
		return "club/clubMatchEnd";
	}
	
	@GetMapping("clubBoard")  //상세페이지 > 탭 > 자유게시판
	public String boardTapPage(Model model) { 		
		return "club/clubBoard";
	}
	
	@GetMapping("clubMember")  //상세페이지 > 탭 > 멤버
	public String memberTapPage(Model model) { 		
		return "club/clubMember";
	}
	
	@GetMapping("clubApply")  //상세페이지 > 탭 > 클럽가입신청
	public String applyTapPage(Model model) { 		
		return "club/clubApply";
	}
	
	
	
	
}
