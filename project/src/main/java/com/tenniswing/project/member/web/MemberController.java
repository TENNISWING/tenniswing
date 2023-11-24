package com.tenniswing.project.member.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	//로그인 폼 이동
	@GetMapping("loginform")
	public String loginPage(Model model) {
		return "member/login";
	}
	
	//로그인 처리
	@PostMapping("loginProc")
	public String loginProc(Model model, MemberVO memberVO) {
		String rawPwd = memberVO.getPwd();
		String encPwd = passwordEncoder.encode(rawPwd);
		
		String role = "";
		memberVO = memberService.getMemberInfo(memberVO);
		if (memberVO != null) {
			role = memberVO.getMemDiv();			
		}else {
			model.addAttribute("message", "로그인에 실패하였습니다.");
			return "member/login";
		}
		
		if (role.equals("h1")) {
			return "match/match";
		} else if (role.equals("h2")) {
			return "host/dashboard";
		} else {
			model.addAttribute("message", "로그인에 실패하였습니다.");
			return "member/login";
		}
	}
	
	//마이페이지 이동
	@GetMapping("mypage")
	public String mypagePage(Model model) {
		return "member/mypage";
	}
	
	//회원가입 이동
	@GetMapping("signup")
	public String signupPage(Model model) {
		return "member/signup";
	}
	
	//회원가입 처리
	@PostMapping("signup")
	public String signupProc(MemberVO memverVO, Model model) {
		String rawPwd = memverVO.getPwd();
		String encPwd = passwordEncoder.encode(rawPwd);
		
		memverVO.setPwd(encPwd);
		
		int result = memberService.insertMember(memverVO);
		System.out.println(result);
		if(result > 0) {
			return "/";
		}
		else {
			model.addAttribute("message", "회원가입에 실패하였습니다.");
			return "member/signup";
		}		
	}
}
