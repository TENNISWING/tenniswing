package com.tenniswing.project.member.web;

import java.io.IOException;
import java.util.List;

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
		
		//아이디 셋 후 회원 정보 불러오기
		MemberVO memberVO = new MemberVO();
		memberVO.setMemId(id);		
		memberVO = memberService.memberInfo(memberVO);
		String role = memberVO.getMemDiv();	
		
		//회원정보 dom에 전달		
		model.addAttribute("member", memberVO);
		
		//첨부파일 불러오기
		List<AttachVO> attachList =  attachService.attachList("m", memberVO.getMemNo());	
		
		String path = "";
		
/*
		//첨부파일 dom에 전달		
		model.addAttribute("attachList", attachList.get(0)); //땡겨온다음에 여기넣기
*/
    
		if(attachList != null && attachList.size() != 0) {
			//첨부파일 dom에 전달	
			path = attachList.get(0).getAttachPath();
			model.addAttribute("attachList", path);
		}else {
			if(memberVO.getGen().equals("남성")) {
				path = "/assets/compiled/jpg/2.jpg";
				model.addAttribute("attachList", path);
			}else {		
				path = "/assets/compiled/jpg/3.jpg";
				model.addAttribute("attachList", path);
			}
		}

		
		if(role.equals("ROLE_ADMIN")) {
			return "redirect:admin";
		}else if(role.equals("ROLE_HOST")) {
			return "redirect:host";
		}
		
		return "member/mypage";
	}
	
	@GetMapping("mypage-club")
	public String clubMyPage(Model model) {
		return "member/mypage-club";
	}

}
