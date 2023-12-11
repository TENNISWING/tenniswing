package com.tenniswing.project.admin.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenniswing.project.attach.service.AttachService;
import com.tenniswing.project.attach.service.AttachVO;
import com.tenniswing.project.common.FileUtils;
import com.tenniswing.project.court.service.CourtroomService;
import com.tenniswing.project.match.service.MatchService;
import com.tenniswing.project.match.service.MatchVO;
import com.tenniswing.project.member.service.MemberService;
import com.tenniswing.project.shop.service.OrderService;
import com.tenniswing.project.shop.service.OrderTableVO;
import com.tenniswing.project.shop.service.PayCancelVO;
import com.tenniswing.project.shop.service.ProdDetailService;
import com.tenniswing.project.shop.service.ProdDetailVO;
import com.tenniswing.project.shop.service.ProdService;
import com.tenniswing.project.shop.service.ProdVO;
import com.tenniswing.project.shop.web.PayService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class AdminController {
	@Autowired
	ProdService prodService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	ProdDetailService prodDetailService;
	
	@Autowired
	FileUtils fileUtils;
	
	@Autowired
	AttachService attachService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	MatchService matchService;
	
	@Autowired
	CourtroomService courtroomService;
	
	@Autowired
	PayService payService;

	// 상품 목록
	@GetMapping("admin_Product")
	public String adminProductPage(Model model, ProdVO prodVO) {
		model.addAttribute("prodList", prodService.selectAdminAllProd(prodVO));
		//System.out.println(prodService.selectAllProd(prodVO));
		return "admin/admin_Product";
	}

	// 한건 조회
	@GetMapping("adminDetail_Product")
	public String adminDetailProductPage(Model model, ProdVO prodVO) {
		
		// 첨부파일 불러오기
		List<AttachVO> attachList = attachService.attachList("p", prodVO.getProdNo());
		String path = "";
		
		if(attachList != null && attachList.size() != 0) {
			path = attachList.get(0).getAttachPath();
			model.addAttribute("attachList", path);
		}
		
		model.addAttribute("prod", prodService.selectProd(prodVO));
		model.addAttribute("prodDetailList", prodDetailService.selectAllProdDetail(prodVO));
		model.addAttribute("prodDetail", new ProdDetailVO());
		return "admin/adminDetail_Product";
	}
	
//	상세 상품 등록 - 처리
	@PostMapping("adminAddDetail_Product")
	@ResponseBody
	public Map<String, Object> adminAddDetailProdcutProcess(@RequestBody ProdDetailVO prodDetailVO) {
		return prodDetailService.insertProdDetail(prodDetailVO);
	}
	
	// 등록
	@GetMapping("adminAdd_Product")
	public String adminAddProductPage(Model model) {
		model.addAttribute("prodVO", new ProdVO());
		//log.warn("====================");
		return "admin/adminAdd_Product";
	}

	// 등록 - 처리
	// adminAddProductProcess(ProdVO prodVO, RedirectAttributes rttr)
	/*
	 * @ResponseBody
	 * 
	 * @PostMapping("adminAdd_Product") public int adminAddProductProcess(ProdVO
	 * prodVO) { System.out.println(prodVO.getFiles()); return
	 * prodService.insertProd(prodVO); }
	 */
	
	@ResponseBody
	@RequestMapping(value = "adminAdd_Product", method = RequestMethod.POST)
	public int adminAddProductProcess(ProdVO prodVO) {
		System.out.println("파일" + prodVO.getFiles());
		System.out.println(prodVO);
		return prodService.insertProd(prodVO);
	}


	// 수정
	@GetMapping("adminEdit_Product")
	public String adminEditProductPage(Model model, ProdVO prodVO) {
		model.addAttribute("prod", prodService.selectProd(prodVO));
		return "admin/adminEdit_Product";
	}

	// 수정 - 처리		Ajax : @ResposeBody / 
	@PostMapping("adminEdit_Product")
	@ResponseBody				
	public Map<String, Object> adminEditProductProcess(@RequestBody ProdVO prodVO) {
								// JSON => @RequestBody
		return prodService.updateProd(prodVO);
	}
	
//	상품 상세 수정 - 처리
	@PostMapping("adminEditDetail_Product")
	@ResponseBody
	public Map<String, Object> adminEditDetailProdcutProcess(@RequestBody ProdDetailVO prodDetailVO) {
		return prodDetailService.updateProdDetail(prodDetailVO);
	}

	//
	/*
	 * @GetMapping("adminDetail_Product") public String adminDetailProductPage(Model
	 * model) { return "admin/adminDetail_Product"; }
	 */

	// 삭제
	@GetMapping("adminDelete_Product")
	@ResponseBody
	public Map<String, Object> adminDeleteProductProcess(@RequestParam Integer prodNo) {
		return prodService.deleteProd(prodNo);
	}
	
//	상품 상세 삭제
	@PostMapping("adminDeleteDetail_Product")
	@ResponseBody
	public Map<String, Object> adminDeleteDetailProductProcess(@RequestBody ProdDetailVO prodDetailVO) {
		return prodDetailService.deleteProdDetail(prodDetailVO);
	}
	
	// 주문 목록
	@GetMapping("admin_Order")
	public String adminOrderPage(Model model, OrderTableVO orderTableVO) {
		model.addAttribute("orderList", orderService.selectAdminAllOrder(orderTableVO));
		//log.warn("=====주문목록===="+orderService.selectAdminAllOrder(orderTableVO));
		return "admin/admin_Order";
	}

	// 주문 상세
	@GetMapping("adminDetail_Order")
	public String adminDetailOrderPage(int orderNo, Model model) {
		model.addAttribute("orderSelect", orderService.selectAdminOrder(orderNo));
		model.addAttribute("orderPay", orderService.selectAdminOrderPay(orderNo));
		model.addAttribute("orderProd", orderService.selectAdminOrderProd(orderNo));
		log.warn("");
		return "admin/adminDetail_Order";
	}
	
	// 주문 취소 (환불)
	@PostMapping("adminOrderCancel")
	@ResponseBody
	public Boolean adminOrderCancel(@RequestBody OrderTableVO vo) throws Exception {
		OrderTableVO orderTableVO = new OrderTableVO();
		orderTableVO = orderService.selectOrder(vo.getOrderNo());
		PayCancelVO payCancelVO = new PayCancelVO();
		OrderTableVO updateOrderTableVO = new OrderTableVO();
		
		String token = payService.getToken();
		String refundPrice = String.valueOf(orderTableVO.getOrderTPayAmt());
		String imp_uid = orderTableVO.getImpUid();
		
		payService.payMentCancle(token, imp_uid, refundPrice, "주문취소");
		
		// 결제 취소 테이블에 insert
		payCancelVO.setOrderNo(vo.getOrderNo());
		payCancelVO.setPayCancelAMt(orderTableVO.getOrderTPayAmt());
		payCancelVO.setPayCancelApplyPart("v2");
		int result = orderService.insertPayCancel(payCancelVO);
		
		if(result > 0) {
			// order_table update - orderState
			updateOrderTableVO.setOrderState("s7");
			updateOrderTableVO.setOrderNo(vo.getOrderNo());
			orderService.updateOrderState(updateOrderTableVO);
			
			// 상품 재고 다시 추가
			return true;
		} else {			
			return false;
		}
	}
	
	//회원목록
	@GetMapping("admin_member")
	public String adminMemberPage(Model model) {
		model.addAttribute("memberList", memberService.getMemberAll());
		return "admin/admin_Member";	
	}
	
	//회원 상세페이지
	@GetMapping("adminDetail_Member")
	public String adminMemberDetailPage(Model model, String memId) {
		
		model.addAttribute("member", memberService.memberInfo(memId));
		
		return "admin/adminDetail_Member";
	}
	
	//매치목록
	@GetMapping("admin_match")
	public String adminMatchPage(Model model) {
		model.addAttribute("matchList", matchService.matchAll());
		return "admin/admin_Match";	
	}
	
	//매치 상세페이지
	@GetMapping("adminDetail_Match")
	public String adminMatchDetailPage(Model model, Integer matchNo) {
		
		MatchVO matchVO = new MatchVO();
		matchVO.setMatchNo(matchNo);
		model.addAttribute("match", matchService.selectMatch(matchVO));
		
		return "admin/adminDetail_Match";
	}
	
	//클럽 목록
	@GetMapping("admin_club")
	public String adminClubPage(Model model) {
		model.addAttribute("matchList", matchService.matchAll());
		return "admin/admin_Club";	
	}
	
	//클럽 상세페이지
	@GetMapping("adminDetail_Club")
	public String adminClubDetailPage(Model model, Integer matchNo) {
		
		MatchVO matchVO = new MatchVO();
		matchVO.setMatchNo(matchNo);
		model.addAttribute("match", matchService.selectMatch(matchVO));
		
		return "admin/adminDetail_Club";
	}
	
	//코트 목록
	@GetMapping("admin_court")
	public String adminCourtPage(Model model) {
		model.addAttribute("courtList", courtroomService.courtAdminAll());
		return "admin/admin_Court";	
	}
	
	//코트 승인 필요 목록	
	@GetMapping("admin_court_apply")
	public String adminCourtApplyPage(Model model) {
		model.addAttribute("courtList", courtroomService.courtAdminAll());
		return "admin/admin_CourtApply";	
	}
	
	//코트 상세페이지
	@GetMapping("adminDetail_Court")
	public String adminCourtDetailPage(Model model, Integer matchNo) {
		
		MatchVO matchVO = new MatchVO();
		matchVO.setMatchNo(matchNo);
		model.addAttribute("match", matchService.selectMatch(matchVO));
		
		return "admin/adminDetail_Court";
	}
}
