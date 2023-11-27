package com.tenniswing.project;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tenniswing.project.match.service.MatchService;
import com.tenniswing.project.match.service.MatchVO;

@Controller
public class HomeController {

	
	
	@GetMapping("/admin")
	public String adminPage(Model model) { 			
		return "admin/dashboard";
	}
	
	@GetMapping("/host")
	public String hostPage(Model model) { 			
		return "host/dashboard";
	}
}

