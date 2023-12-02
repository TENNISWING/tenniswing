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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tenniswing.project.attach.service.AttachService;
import com.tenniswing.project.attach.service.AttachVO;
import com.tenniswing.project.common.FileUtils;
import com.tenniswing.project.shop.service.ProdDetailService;
import com.tenniswing.project.shop.service.ProdDetailVO;
import com.tenniswing.project.shop.service.ProdService;
import com.tenniswing.project.shop.service.ProdVO;

@Controller
public class AdminController {
	@Autowired
	ProdService prodService;
	
	@Autowired
	ProdDetailService prodDetailService;
	
	@Autowired
	FileUtils fileUtils;
	
	@Autowired
	AttachService attachService;

	// 상품 목록
	@GetMapping("admin_Product")
	public String adminProductPage(Model model) {
		model.addAttribute("prodList", prodService.selectAllProd());
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
	
	@GetMapping("admin_Order")
	public String adminOrderPage(Model model) {
		return "admin/admin_Order";
	}

	@GetMapping("adminDetail_Order")
	public String adminDetailOrderPage(Model model) {
		return "admin/adminDetail_Order";
	}
}
