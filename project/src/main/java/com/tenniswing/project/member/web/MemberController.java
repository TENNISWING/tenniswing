package com.tenniswing.project.member.web;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenniswing.project.member.service.MemberService;
import com.tenniswing.project.member.service.MemberVO;

@Controller
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@Autowired
	HttpSession httpSession;

	// 로그인 폼 이동
	@GetMapping("loginform")
	public String loginPage(Model model) {
		return "member/login";
	}

	// 회원가입 폼 이동
	@GetMapping("signup")
	public String memberSignupPage(Model model) {
		return "member/signup";
	}

	// 호스트회원가입 폼 이동
	@GetMapping("hostsignup")
	public String hostSignupPage(Model model) {
		return "member/hostsignup";
	}
	
	// 아이디찾기 폼 이동
	@GetMapping("forgotid")
	public String forgotIdPage(Model model) {
		return "member/forgotid";
	}
	
	// 패스워드찾기 폼 이동
	@GetMapping("forgotpw")
	public String fortgotPwPage(Model model) {
		return "member/forgotpw";
	}

	// 아이디 중복 체크
	@PostMapping("idcheck")
	@ResponseBody
	public boolean idCheck(@RequestParam("memId") String memId) {
		boolean check = memberService.idCheck(memId);
		return check;
	}

	// 회원가입 처리
	@PostMapping("signup")
	public String signupProc(MemberVO memberVO, Model model) {

		boolean check = memberService.idCheck(memberVO.getMemId());
		if (!check) {
			model.addAttribute("message", "아이디 중복 체크하지 않았습니다");
			return "member/signup";
		}

		int result = memberService.insertMember(memberVO);

		if (result > 0) {
			return "redirect:/loginform";
		} else {
			model.addAttribute("message", "회원가입에 실패하였습니다.");
			return "member/signup";
		}
	}

	// 마이페이지 이동
	@GetMapping("mypage")
	public String mypagePage(Model model) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iter = authorities.iterator();
		GrantedAuthority auth = iter.next();
		String role = auth.getAuthority();
		
		httpSession.setAttribute("member", id);		
		
		model.addAttribute("id", id);
		model.addAttribute("role", role);
		
		if(role.equals("ROLE_ADMIN")) {
			return "redirect:admin";
		}else if(role.equals("ROLE_HOST")) {
			return "redirect:host";
		}
		
		return "member/mypage";
	}

}
