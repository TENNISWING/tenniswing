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
	
}
