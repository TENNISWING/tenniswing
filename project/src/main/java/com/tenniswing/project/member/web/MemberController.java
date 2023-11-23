package com.tenniswing.project.member.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.tenniswing.project.member.service.MemberService;
import com.tenniswing.project.member.service.MemberVO;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("loginform")
	public String loginPage(Model model) { 			
		return "member/login";
	}
	
	@PostMapping("loginProc")
	public String loginResultPage(Model model, MemberVO memberVO) {
		
		memberVO =  memberService.getMemberInfo(memberVO);
		return "member/login";
	}
	
	@GetMapping("mypage")  
	public String mypagePage(Model model) { 			
		return "member/mypage";
	}
	
	@GetMapping("signup")  
	public String signupPage(Model model) { 			
		return "member/signup";
	}
	
}
