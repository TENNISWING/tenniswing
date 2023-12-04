package com.tenniswing.project.shop.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenniswing.project.attach.service.AttachService;
import com.tenniswing.project.attach.service.AttachVO;
import com.tenniswing.project.common.FileUtils;
import com.tenniswing.project.shop.service.ProdDetailService;
import com.tenniswing.project.shop.service.ProdDetailVO;
import com.tenniswing.project.shop.service.ProdService;
import com.tenniswing.project.shop.service.ProdVO;

@Controller
public class ShopController {

	@Autowired
	ProdService prodService;
	
	@Autowired
	ProdDetailService prodDetailService;
	
	@Autowired
	FileUtils fileUtils;
	
	@Autowired
	AttachService attachService;
	// 상품 목록
	@GetMapping("shop")
	public String shopPage(Model model) {
		model.addAttribute("prodList", prodService.selectAllProd());
		return "shop/shop";
	}

	// 한건 조회
	@GetMapping("shopDetail")
	public String shopDetailPage(Model model, ProdVO prodVO) {
		// 첨부파일 불러오기
		List<AttachVO> attachList = attachService.attachList("p", prodVO.getProdNo());
		String path = "";

		if (attachList != null && attachList.size() != 0) {
			path = attachList.get(0).getAttachPath();
			model.addAttribute("attachList", path);
		}
		model.addAttribute("prod", prodService.selectProd(prodVO));
		model.addAttribute("prodDetailList", prodDetailService.selectAllProdDetail(prodVO));
		model.addAttribute("prodDetail", new ProdDetailVO());
		return "shop/shopDetail";
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
