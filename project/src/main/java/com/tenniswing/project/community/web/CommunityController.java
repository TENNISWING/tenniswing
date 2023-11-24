package com.tenniswing.project.community.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tenniswing.project.community.service.SnsVO;

@Controller
public class CommunityController {
	// SNS 메인
	@GetMapping("sns")
	public String snsListPage(Model model) {
		return "community/community";
	}
	
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
	
	// sns 등록폼
	@GetMapping("snsRegister")
	public String snsRegPage(Model model) {
		return "community/snsRegister";
	}
}
