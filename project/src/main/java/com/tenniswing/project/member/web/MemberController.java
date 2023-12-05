package com.tenniswing.project.member.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenniswing.project.attach.service.AttachService;
import com.tenniswing.project.attach.service.AttachVO;
import com.tenniswing.project.common.FileUtils;
import com.tenniswing.project.member.service.MemberService;
import com.tenniswing.project.member.service.MemberVO;

@Controller
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@Autowired
	HttpSession httpSession;
	
	@Autowired
	FileUtils fileUtils;

	@Autowired
	AttachService attachService;

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
	public String signupProc(MemberVO memberVO, Model model) throws IOException {		
						
		boolean check = memberService.idCheck(memberVO.getMemId());
		
		if (!check) {
			model.addAttribute("message", "아이디 중복 체크하지 않았습니다");
			return "member/signup";
		}

		int result = memberService.insertMember(memberVO);	

		if (result > 0) {			
			
			if(memberVO.getMemDiv().equals("ROLE_HOST")) {
				return "redirect:/loginform";
			}
			
			System.out.println("1"+memberVO.getFiles());
			
			List<AttachVO> files = fileUtils.uploadFiles(memberVO.getFiles());
			System.out.println("2"+files);
			
			//테이블 구분, 게시글 번호, 파일목록
			int n = attachService.saveAttach("m", memberVO.getMemNo(), files);
			model.addAttribute("message", "회원가입을 마쳤습니다. <br> 로그인 해주세요.");
			return "redirect:/loginform";
		} else {
			model.addAttribute("message", "회원가입에 실패하였습니다.");
			return "member/signup";
		}			
	
	}

	// 마이페이지 이동
	@GetMapping("mypage")
	public String mypagePage(Model model) {
		//로그인 회원 아이디 불러오기
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		
		MemberVO memberVO = memberService.memberInfo(id);	
		
		// 회원정보 dom에 전달
		model.addAttribute("member", memberVO);
		model.addAttribute("nowpage", 0);
		
		if (memberVO.getMemDiv().equals("ROLE_ADMIN")) {
			return "redirect:admin";
		} else if (memberVO.getMemDiv().equals("ROLE_HOST")) {
			return "redirect:host";
		}
		
		return "member/mypage";
	}
	
	@GetMapping("mypage-profile")
	public String profileMyPage(Model model) {
		//로그인 회원 아이디 불러오기
		String id = SecurityContextHolder.getContext().getAuthentication().getName();	
		
		// 회원정보 dom에 전달
		model.addAttribute("member",  memberService.memberUpdateInfo(id));
		model.addAttribute("nowpage", 10 );
		return "member/mypage-profile";
	}	
	
	@PostMapping("profileUpdate")	
	public String profileUpdate(Model model, MemberVO memberVO){
		//로그인 회원 아이디 불러오기
		String id = SecurityContextHolder.getContext().getAuthentication().getName();	
		
		Map<String, Object> map = new HashMap<>();	
		
		map = memberService.updateMemberInfo(memberVO);
		
		model.addAttribute("member",  memberService.memberUpdateInfo(id));
		model.addAttribute("nowpage", 10 );		
		model.addAttribute("message", map.get("message"));
		
		return "member/mypage-profile";		
	}
	
	@PostMapping("profileupload")
	@ResponseBody
	public Boolean profileUploadAjax(MemberVO memberVO, Model model) {

		int n = memberService.profileUpload(memberVO);
		
		if(n > 0) {
			return true;
		}		
		return false;
	}
	
	@GetMapping("mypage-club")
	public String clubMyPage(Model model) {
		//로그인 회원 아이디 불러오기
		String id = SecurityContextHolder.getContext().getAuthentication().getName();		
		MemberVO memberVO =  memberService.memberInfo(id);	
		
		// 회원정보 dom에 전달
		model.addAttribute("member", memberVO);
		model.addAttribute("nowpage", 1 );
		return "member/mypage-club";
	}
	
	@GetMapping("mypage-court")
	public String courtMyPage(Model model) {
		//로그인 회원 아이디 불러오기
		String id = SecurityContextHolder.getContext().getAuthentication().getName();		
		MemberVO memberVO =  memberService.memberInfo(id);	
		
		// 회원정보 dom에 전달
		model.addAttribute("member", memberVO);
		model.addAttribute("nowpage", 2 );
		return "member/mypage-court";
	}
	
	@GetMapping("mypage-write")
	public String writeMyPage(Model model) {
		//로그인 회원 아이디 불러오기
		String id = SecurityContextHolder.getContext().getAuthentication().getName();		
		MemberVO memberVO =  memberService.memberInfo(id);	
		
		// 회원정보 dom에 전달
		model.addAttribute("member", memberVO);
		model.addAttribute("nowpage", 3 );
		return "member/mypage-write";
	}
	
	@GetMapping("mypage-shop")
	public String shopMyPage(Model model) {
		//로그인 회원 아이디 불러오기
		String id = SecurityContextHolder.getContext().getAuthentication().getName();		
		MemberVO memberVO =  memberService.memberInfo(id);	
		
		// 회원정보 dom에 전달
		model.addAttribute("member", memberVO);
		model.addAttribute("nowpage", 4 );
		return "member/mypage-shop";
	}
	
	

}
