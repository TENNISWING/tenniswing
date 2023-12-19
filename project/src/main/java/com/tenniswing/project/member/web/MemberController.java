package com.tenniswing.project.member.web;

import java.io.IOException;
import java.util.ArrayList;
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
import com.tenniswing.project.match.service.MatchHistVO;
import com.tenniswing.project.member.service.MemberService;
import com.tenniswing.project.member.service.MemberVO;
import com.tenniswing.project.shop.mapper.ProdDetailMapper;
import com.tenniswing.project.shop.mapper.ProdMapper;
import com.tenniswing.project.shop.service.OrderDetailVO;
import com.tenniswing.project.shop.service.OrderService;
import com.tenniswing.project.shop.service.OrderTableVO;
import com.tenniswing.project.shop.service.PayCancelVO;
import com.tenniswing.project.shop.service.ProdDetailVO;
import com.tenniswing.project.shop.service.ProdVO;
import com.tenniswing.project.shop.service.WishService;
import com.tenniswing.project.shop.web.PayService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	
	@Autowired WishService wishService;
	
	@Autowired OrderService orderService;
	
	@Autowired PayService payService;
	
	@Autowired
	ProdDetailMapper prodDetailMapper;

	@Autowired
	ProdMapper prodMapper;

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
		System.out.println(matchHistService.selectAllMyMatchHist(id));
		if (memberVO.getMemDiv().equals("ROLE_ADMIN")) {
			return "redirect:admin";
		} else if (memberVO.getMemDiv().equals("ROLE_HOST")) {
			return "redirect:host";
		}

		return "member/mypage";
	}
	
	// 매치 취소
	@PostMapping("matchDel")
	@ResponseBody
	public int matchDelete(MatchHistVO matchHistVO) {
		
		return matchHistService.deleteMatchHist(matchHistVO);
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
		model.addAttribute("loginId", id);
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
		String memId = SecurityContextHolder.getContext().getAuthentication().getName();
		OrderTableVO orderTableVO = new OrderTableVO();
		orderTableVO.setMemId(memId);
		
		model.addAttribute("orderList", orderService.selectAdminAllOrder(orderTableVO));
		model.addAttribute("wishList", wishService.selectAllWish(memId));
		model.addAttribute("member", memberService.memberInfo(memId));
		model.addAttribute("nowpage", 4);
		return "member/mypage-shop";
	}
	
	// 내 주문 상세
	@GetMapping("mypage-shopDetail")
	public String adminDetailOrderPage(int orderNo, Model model) {
		String memId = SecurityContextHolder.getContext().getAuthentication().getName();
		
		model.addAttribute("orderSelect", orderService.selectAdminOrder(orderNo));
		model.addAttribute("orderPay", orderService.selectAdminOrderPay(orderNo));
		model.addAttribute("orderProd", orderService.selectAdminOrderProd(orderNo));
		model.addAttribute("member", memberService.memberInfo(memId));
		model.addAttribute("nowpage", 4);
		return "member/mypage-shopDetail";
	}
	
	// 주문 취소 (환불)
	@PostMapping("orderCancel")
	@ResponseBody
	public Boolean adminOrderCancel(@RequestBody OrderTableVO vo) throws Exception {
		OrderTableVO orderTableVO = new OrderTableVO();
		orderTableVO = orderService.selectOrder(vo.getOrderNo());
		PayCancelVO payCancelVO = new PayCancelVO();
		OrderTableVO updateOrderTableVO = new OrderTableVO();

		List<OrderDetailVO> orderDetailList = new ArrayList<OrderDetailVO>();
		int orderNo = vo.getOrderNo();

		String token = payService.getToken();
		String refundPrice = String.valueOf(orderTableVO.getOrderTPayAmt());
		String imp_uid = orderTableVO.getImpUid();

		payService.payMentCancle(token, imp_uid, refundPrice, "주문취소");

		// 결제 취소 테이블에 insert
		payCancelVO.setOrderNo(vo.getOrderNo());
		payCancelVO.setPayCancelAMt(orderTableVO.getOrderTPayAmt());
		payCancelVO.setPayCancelApplyPart("v2");
		int result = orderService.insertPayCancel(payCancelVO);

		ProdDetailVO prodDetailVO = new ProdDetailVO();
		if (result > 0) {
			// order_table update - orderState
			updateOrderTableVO.setOrderState("s7");
			updateOrderTableVO.setOrderNo(vo.getOrderNo());
			orderService.updateOrderState(updateOrderTableVO);

			// 상품 재고 다시 추가
			// prod_detail_no별 판매 재고를 가져온다
			// prod_detail_no의 재고를 추가한다.
			orderDetailList = orderService.selectOrderDetail(orderNo);
			log.warn("===orderDetailVO===" + orderDetailList);
			for (OrderDetailVO detVO : orderDetailList) {
				prodDetailVO.setProdDetailNo(detVO.getProdDetailNo());
				prodDetailVO.setProdDetailSto(detVO.getOrderDetailCnt());

				log.warn("====prodDetailVO====" + prodDetailVO);
				// 상품 상세 재고 추가
				prodDetailMapper.updateProdDetailCancel(prodDetailVO);

				// 상품 번호 가져오기
				ProdDetailVO prodDetail = new ProdDetailVO();
				prodDetail = prodDetailMapper.selectProdDetail(prodDetailVO);

				int prodTSto = prodDetailMapper.selectSumOrderProdNo(prodDetail.getProdNo());
				ProdVO prodVO = new ProdVO();
				prodVO.setProdNo(prodDetail.getProdNo());
				prodVO.setProdTSto(prodTSto);
				prodMapper.updateProd(prodVO);
			}
			return true;
		} else {
			return false;
		}
	}
}
