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
import org.springframework.web.bind.annotation.RequestBody;
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

	// 아이디찾기
	@PostMapping("forgotid")
	@ResponseBody
	public String forgotIdAjax(@RequestBody MemberVO memberVO, Model model) {
		System.out.println(memberVO+"aaaa");
		return memberService.searchId(memberVO);
	}

	// 패스워드찾기 폼 이동
	@GetMapping("forgotpw")
	public String fortgotPwPage(Model model) {
		return "member/forgotpw";
	}

	// 패스워드찾아서 업데이트
	@PostMapping("forgotpw")
	@ResponseBody
	public int fortgotPwAjax(Model model, @RequestBody MemberVO memberVO) {		
		return memberService.searchPwUpdate(memberVO);
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

			if (memberVO.getMemDiv().equals("ROLE_HOST")) {
				return "redirect:/loginform";
			}
			List<AttachVO> files = fileUtils.uploadFiles(memberVO.getFiles());

			// 테이블 구분, 게시글 번호, 파일목록
			int n = attachService.saveAttach("m", memberVO.getMemNo(), files);
			model.addAttribute("message", "회원가입을 완료하였습니다.");
			return "redirect:/loginform";
		} else {
			model.addAttribute("message", "회원가입에 실패하였습니다.");
			return "redirect:/signup";
		}

	}

	// 마이페이지 이동
	@GetMapping("mypage")
	public String mypagePage(Model model) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();

		MemberVO memberVO = memberService.memberInfo(id);

		model.addAttribute("member", memberVO);
		model.addAttribute("nowpage", 0);

		if (memberVO.getMemDiv().equals("ROLE_ADMIN")) {
			return "redirect:admin";
		} else if (memberVO.getMemDiv().equals("ROLE_HOST")) {
			return "redirect:host";
		}

		return "member/mypage";
	}

	// 프로필보기
	@GetMapping("mypage-profile")
	public String profileMyPage(Model model) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();

		model.addAttribute("member", memberService.memberUpdateInfo(id));
		model.addAttribute("nowpage", 10);
		return "member/mypage-profile";
	}

	// 회원정보 수정
	@PostMapping("profileUpdate")
	public String profileUpdate(Model model, MemberVO memberVO) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		Map<String, Object> map = new HashMap<>();

		map = memberService.updateMemberInfo(memberVO);

		model.addAttribute("member", memberService.memberUpdateInfo(id));
		model.addAttribute("nowpage", 10);
		model.addAttribute("message", map.get("message"));

		return "member/mypage-profile";
	}

	// 프로필사진 업데이트
	@PostMapping("profileupload")
	@ResponseBody
	public Boolean profileUploadAjax(MemberVO memberVO, Model model) {
		int n = memberService.profileUpload(memberVO);

		if (n > 0) {
			return true;
		}
		return false;
	}

	// 나의 클럽 목록
	@GetMapping("mypage-club")
	public String clubMyPage(Model model) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();

		model.addAttribute("member", memberService.memberInfo(id));
		model.addAttribute("nowpage", 1);
		return "member/mypage-club";
	}

	// 코트 예약 목록
	@GetMapping("mypage-court")
	public String courtMyPage(Model model) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();

		model.addAttribute("member", memberService.memberInfo(id));
		model.addAttribute("nowpage", 2);
		return "member/mypage-court";
	}

	// 내 작성 글 목록
	@GetMapping("mypage-write")
	public String writeMyPage(Model model) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();

		model.addAttribute("member", memberService.memberInfo(id));
		model.addAttribute("nowpage", 3);
		return "member/mypage-write";
	}

	// 내 주문 내역 목록
	@GetMapping("mypage-shop")
	public String shopMyPage(Model model) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();

		model.addAttribute("member", memberService.memberInfo(id));
		model.addAttribute("nowpage", 4);
		return "member/mypage-shop";
	}

}
