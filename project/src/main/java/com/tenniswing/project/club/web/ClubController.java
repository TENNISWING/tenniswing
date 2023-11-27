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
	
	@GetMapping("clubdetail")  //상세페이지
	public String clubDetailPage(Model model) { 		
		return "club/clubdetail";
	}
	
	@GetMapping("clubInfo")  //상세페이지 > 탭 > 클럽정보
	public String infoTapPage(Model model) { 		
		return "club/clubInfo";
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
