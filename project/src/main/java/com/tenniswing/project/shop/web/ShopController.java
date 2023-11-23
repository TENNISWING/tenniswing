package com.tenniswing.project.shop.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopController {
	
	@GetMapping("shop")  
	public String shopPage(Model model) {		
		return "shop/shop";
	}
	
	@GetMapping("shopDetail")
	public String shopDetailPage(Model model) {
		return "shop/shopDetail";
	}
}
