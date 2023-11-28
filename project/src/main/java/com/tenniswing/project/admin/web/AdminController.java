package com.tenniswing.project.admin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tenniswing.project.shop.service.ProdService;
import com.tenniswing.project.shop.service.ProdVO;

@Controller
public class AdminController {
	@Autowired
	ProdService prodService;

	@GetMapping("admin_Product")
	public String adminProductPage(Model model) {
		return "admin/admin_Product";
	}

	// 등록
	@GetMapping("adminAdd_Product")
	public String adminAddProductPage(Model model) {
		model.addAttribute("prodVO", new ProdVO());
		return "admin/adminAdd_Product";
	}

	// 등록 - 처리
	@PostMapping("adminAdd_Product")
	public String adminAddProductProcess(ProdVO prodVO, RedirectAttributes rttr) {
		prodService.insertProd(prodVO);
		
		
		return "admin/adminAdd_Product";
	}

	@GetMapping("adminEdit_Product")
	public String adminEditProductPage(Model model) {
		return "admin/adminEdit_Product";
	}

	@GetMapping("adminDetail_Product")
	public String adminDetailProductPage(Model model) {
		return "admin/adminDetail_Product";
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
