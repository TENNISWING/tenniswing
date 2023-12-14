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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tenniswing.project.attach.service.AttachService;
import com.tenniswing.project.attach.service.AttachVO;
import com.tenniswing.project.club.service.ClubService;
import com.tenniswing.project.common.FileUtils;
import com.tenniswing.project.community.service.SnsService;
import com.tenniswing.project.community.service.SnsVO;
import com.tenniswing.project.court.service.CrtReserveService;
import com.tenniswing.project.match.service.MatchHistService;
import com.tenniswing.project.member.service.MemberService;
import com.tenniswing.project.member.service.MemberVO;

@Controller
public class MemberController {

	@Autowired	MemberService memberService;

	@Autowired	ClubService clubService;

	@Autowired	CrtReserveService crtReserveService;

	@Autowired	MatchHistService matchHistService;

	@Autowired	HttpSession httpSession;

	@Autowired	FileUtils fileUtils;

	@Autowired	AttachService attachService;

	@Autowired SnsService snsService;

	// 로그인 폼 이동
	@GetMapping("loginform")
	public String loginPage(@RequestParam(value = "error", required = false) String error,
				@RequestParam(value = "exception", required = false)String exception, Model model) {
				model.addAttribute("error", error);
				model.addAttribute("message", exception);
		return "member/login";
	}

	// 회원가입 폼 이동
	@GetMapping("signup")
	public String memberSignupPage(Model model) {
		return "member/signup";
	}

	// 호스트회원가입 폼 이동
	@GetMapping("signuphost")
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
	public String signupProc(MemberVO memberVO, Model model, RedirectAttributes rttr) throws IOException {

		boolean check = memberService.idCheck(memberVO.getMemId());

		if (!check) {
			rttr.addFlashAttribute("message", "아이디 중복 체크하지 않았습니다");
			return "redirect:signup";
		}
		
		List<AttachVO> files = fileUtils.uploadFiles(memberVO.getFiles());
		
		int result = memberService.insertMember(memberVO, files);

		if (result > 0) {
			rttr.addFlashAttribute("msg", "회원가입을 완료하였습니다.");
			return "redirect:loginform";
		} else {
			rttr.addFlashAttribute("msg", "회원가입에 실패하였습니다.");
			return "redirect:signup";
		}

	}

	// 마이페이지 이동
	@GetMapping("mypage")
	public String mypagePage(Model model) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();

		MemberVO memberVO = memberService.memberInfo(id);

		model.addAttribute("match", matchHistService.selectAllMyMatchHist(id));
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
	public Boolean profileUploadAjax(MemberVO memberVO) {
		int n = memberService.profileUpload(memberVO);

		if (n > 0) {
			return true;
		}
		return false;
	}

	//회원탈퇴
	@PostMapping("memberquit")
	@ResponseBody
	public Boolean memberQuitAjax(@RequestBody MemberVO memeberVO) {
		return memberService.deleteMember(memeberVO);
	}

	// 나의 클럽 목록
	@GetMapping("mypage-club")
	public String clubMyPage(Model model) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();

		model.addAttribute("clubList", clubService.selectAllMyClub(id));
		model.addAttribute("member", memberService.memberInfo(id));
		model.addAttribute("nowpage", 1);
		return "member/mypage-club";
	}

	// 코트 예약 목록
	@GetMapping("mypage-court")
	public String courtMyPage(Model model) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();

		model.addAttribute("court", crtReserveService.selectMyCourtReverse(id));
		model.addAttribute("member", memberService.memberInfo(id));
		model.addAttribute("nowpage", 2);
		return "member/mypage-court";
	}

	// 내 작성 글 목록
	@GetMapping("mypage-sns")
	public String writeMyPage(SnsVO snsVO, Model model) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		snsVO.setMemId(id);
		model.addAttribute("like", snsService.selectMyLike(snsVO));
		model.addAttribute("scrap", snsService.selectMyScrap(snsVO));
		model.addAttribute("member", memberService.memberInfo(id));
		model.addAttribute("nowpage", 3);
		return "member/mypage-sns";
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
