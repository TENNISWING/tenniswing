package com.tenniswing.project.shop.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.tenniswing.project.shop.service.CartService;
import com.tenniswing.project.shop.service.CartVO;
import com.tenniswing.project.shop.service.OrderDetailVO;
import com.tenniswing.project.shop.service.OrderService;
import com.tenniswing.project.shop.service.OrderTableVO;
import com.tenniswing.project.shop.service.ProdDetailService;
import com.tenniswing.project.shop.service.ProdDetailVO;
import com.tenniswing.project.shop.service.ProdService;
import com.tenniswing.project.shop.service.ProdVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ShopController {

	@Autowired
	ProdService prodService;
	
	@Autowired
	ProdDetailService prodDetailService;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	FileUtils fileUtils;
	
	@Autowired
	AttachService attachService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	OrderService orderService;
	
	// 상품 목록
	@GetMapping("shop")
	public String shopPage(Model model) {
//		model.addAttribute("prodList", prodService.selectAllProd());
		model.addAttribute("popularProd", prodService.selectSwiperProd());
		return "shop/shop";
	}
	
	@GetMapping("shopList")
	@ResponseBody
	public Map<String, Object> prodPage(ProdVO prodVO) {
		/*
		 * System.out.println("!!!! before"+prodVO); List<ProdVO> list =
		 * prodService.selectAllProd(prodVO); System.out.println("!!!! after "+list);
		 */
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("selectCount", prodService.selectCount(prodVO));
		map.put("shopList", prodService.selectAllProd(prodVO));
		return map;
	}

	// 한건 조회
	@GetMapping("shopDetail")
	public String shopDetailPage(Model model, ProdVO prodVO) {
		ProdVO tempVO = new ProdVO();
		
		// 첨부파일 불러오기
		List<AttachVO> attachList = attachService.attachList("p", prodVO.getProdNo());
		//String path = "";

		if (attachList != null && attachList.size() != 0) {
			//path = attachList.get(0).getAttachPath();
			//model.addAttribute("image", path);
			model.addAttribute("attachList", attachList);
		}
		
		tempVO = prodService.selectProd(prodVO);
		log.warn("=============="+tempVO);
		model.addAttribute("prod", tempVO);
		model.addAttribute("relatedProd", prodService.relatedSwiperProd(tempVO));
		log.warn("=============="+prodService.relatedSwiperProd(prodVO));
		model.addAttribute("prodDetailList", prodDetailService.selectAllProdDetail(prodVO));
		model.addAttribute("prodDetail", new ProdDetailVO());
		return "shop/shopDetail";
	}
	
//	장바구니 리스트 출력
	@GetMapping("selectCartList")
	@ResponseBody
	public List<CartVO> selectAllCart(@RequestParam(value = "cartMemId") String memId) {
		return cartService.selectAllCart(memId);
	}
	
//	장바구니 한건조회
	@PostMapping("selectCart")
	@ResponseBody
	public boolean selectCart(@RequestBody CartVO cartvo){
		return cartService.selectOneCart(cartvo);
	}
	
//	장바구니 추가 처리
	@PostMapping("insertCart")
	@ResponseBody
	public Map<String, Object> insertCart(@RequestBody ProdDetailVO prodDetailVO){
		log.warn("======추가====="+prodDetailVO);
		return cartService.insertCart(prodDetailVO);
	}
	
//	장바구니 수정 처리
	@PostMapping("updateCart")
	@ResponseBody
	public boolean updateCart(@RequestBody CartVO cartvo) {
		return cartService.updateCart(cartvo);
	}
	
//	장바구니 삭제 처리
	@PostMapping("deleteCart")
	@ResponseBody
	public boolean deleteCart(@RequestBody CartVO cartvo) {
		return cartService.deleteCart(cartvo);
	}

	@GetMapping("checkout")
	public String checkoutPage(Model model, ProdDetailVO prodDetailVO, ProdVO prodVO, @RequestParam String type) {
		/*
		 * ProdVO prodVO = new ProdVO(); prodVO.setProdNo(prodDetailVO.getProdNo());
		 */
		//MemberVO memberVO
		//model.addAttribute("prodDetail", prodDetailVO);
		//log.warn("asd"+type);
		String memId = SecurityContextHolder.getContext().getAuthentication().getName();
		List<CartVO> cartList = new ArrayList<CartVO>();
		ProdDetailVO detailVO = new ProdDetailVO();
		detailVO = prodDetailService.selectCartProd(prodDetailVO);
		
		if(type.equals("direct")) {
			log.warn("=======prodVO==="+prodVO);
			log.warn("=======prodDetailVO==="+prodDetailVO);
			model.addAttribute("prod", prodService.selectProd(prodVO));
			model.addAttribute("prodDetailVO", prodDetailVO);
			model.addAttribute("prodDetailNo", detailVO.getProdDetailNo());
			log.warn("=======prodDetailNo==="+detailVO.getProdDetailNo());
			//model.addAttribute("cartList", cartList);
		} else {
			//log.warn("asd"+type);
			cartList = cartService.selectCheckCart(memId, type);
			log.warn("=======cartList==="+cartList);
			model.addAttribute("cartList", cartList);	
		}
		
		//model.addAttribute("prodDetailVO", prodDetailService.selectProdDetail(prodDetailVO));
		//model.addAttribute("mem", memberService.memberInfo(memberVO.getMemId()));
		return "shop/checkout";
	}
	
	@PostMapping("findMemId")
	@ResponseBody
	public MemberVO findMemId(@RequestBody MemberVO memberVO) {
		log.warn("=========memID==="+memberVO.getMemId());
		return memberService.memberInfo(memberVO.getMemId()); 
	}
	
	@PostMapping("orderPay")
	@ResponseBody
	public Map<String, Object> orderPay(@RequestBody OrderTableVO orderTableVO) {
		Map<String, Object> result = new HashMap<String, Object>();
		if((orderTableVO.getType()).equals("direct")) {
			log.warn("===== 결제가 성공해서 들어오면===="+orderTableVO);
			result = orderService.insertOrder(orderTableVO);
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
}
