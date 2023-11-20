package com.tenniswing.project.member.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
	
	@GetMapping("mypage")  
	public String loginPage(Model model) { 			
		return "member/login";
	}
	
	@GetMapping("signup")  
	public String signupPage(Model model) { 			
		return "member/signup";
	}
	
}
