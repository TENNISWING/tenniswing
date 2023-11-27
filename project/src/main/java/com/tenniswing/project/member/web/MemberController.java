package com.tenniswing.project.member.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenniswing.project.member.service.MemberService;
import com.tenniswing.project.member.service.MemberVO;

@Controller
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	//로그인 폼 이동
	@GetMapping("loginform")
	public String loginPage(Model model) {
		return "member/login";
	}	

	//회원가입 이동
	@GetMapping("signup")
	public String signupPage(Model model) {
		return "member/signup";
	}
	
	//아이디 중복 체크
	@PostMapping("idcheck")
	@ResponseBody
	public String idCheck(Model model,MemberVO memberVO) {
		memberVO = memberService.loginMember(memberVO);
		
		return "있음";
	}
	
	//회원가입 처리
	@PostMapping("signup")
	public String signupProc(MemberVO memberVO, Model model) {		
	
		memberVO.setPwd(passwordEncoder.encode(memberVO.getPwd()));		
		int result = memberService.insertMember(memberVO);
	
		if(result > 0) {
			return "redirect:/loginform";
		}
		else {
			model.addAttribute("message", "회원가입에 실패하였습니다.");
			return "member/signup";
		}		
	}
	
	//마이페이지 이동
	@GetMapping("mypage")
	public String mypagePage(Model model) {
		return "member/mypage";
	}
	
}
