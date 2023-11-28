package com.tenniswing.project.admin.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import groovyjarjarpicocli.CommandLine.Model;

@Controller
public class AdminController {
	
	@GetMapping("admin_Product")
	public String adminProductPage(Model model) {
		return "admin/admin_Product";
	}
	
	@GetMapping("adminAdd_Product")
	public String adminAddProductPage(Model model) {
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
