package com.tenniswing.project.shop.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.tenniswing.project.shop.service.CartService;
import com.tenniswing.project.shop.service.CartVO;
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
		return cartService.selectAllCart(cartvo);
	}
	
//	장바구니 추가 처리
	@PostMapping("insertCart")
	@ResponseBody
	public Map<String, Object> insertCart(@RequestBody ProdDetailVO prodDetailVO){
		return cartService.insertCart(prodDetailVO);
	}
	
//	장바구니 수정 처리
	@PostMapping("updateCart")
	@ResponseBody
	public boolean updateCart(@RequestBody CartVO cartvo) {
		return cartService.updateCart(cartvo);
	}

	@GetMapping("checkout")
	public String checkoutPage(Model model, ProdDetailVO prodDetailVO, ProdVO prodVO) {
		/*
		 * ProdVO prodVO = new ProdVO(); prodVO.setProdNo(prodDetailVO.getProdNo());
		 */
		//model.addAttribute("prodDetail", prodDetailVO);
		model.addAttribute("prod", prodService.selectProd(prodVO));
		return "shop/checkout";
	}
}
