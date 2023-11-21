package com.tenniswing.project.community.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommunityController {
	// 자유게시판 메인(리스트 페이지)
	@GetMapping("freeboardList")
	public String freeboardListPage(Model model) {
		return "community/freeboardList";
	}
	
	// 공지사항 게시판 메인(리스트 페이지)
	@GetMapping("noticeList")
	public String noticeListPage(Model model) {
		return "community/noticeList";
	}
}
