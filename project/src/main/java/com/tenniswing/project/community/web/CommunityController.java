package com.tenniswing.project.community.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenniswing.project.community.service.SnsRepService;
import com.tenniswing.project.community.service.SnsRepVO;
import com.tenniswing.project.community.service.SnsService;
import com.tenniswing.project.community.service.SnsVO;

@Controller
public class CommunityController {
	
	@Autowired
	SnsService snsService;
//	
	@Autowired
	SnsRepService snsRepService;
	
	// SNS
		// SNS 메인
		@GetMapping("sns")
		public String snsListPage(Model model) {
			List<SnsVO> list = snsService.selectAllSnsInfo();
			System.out.println("list 찍어봄 ::: " + list);
			model.addAttribute("snsList", snsService.selectAllSnsInfo());
			return "community/community3";
		}
		
		// sns댓글List
		@GetMapping("snsRep")
		@ResponseBody
		public List<SnsRepVO> snsRepPageAjax(SnsRepVO snsRepVO) {
			return snsRepService.selectAllSnsRep(snsRepVO);
		}
		
		// sns댓글 등록
		@PostMapping("snsReplyInsert")
		@ResponseBody
		public int insertSnsRepAjax(SnsRepVO snsRepVO, Model model) {
			String id = SecurityContextHolder.getContext().getAuthentication().getName();
			snsRepVO.setMemId(id);
			System.out.println(snsRepVO);
			int result =  snsRepService.insertSnsRep(snsRepVO);
			
			return result;
			
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
		
		// sns 내가 등록한 게시글 보기
		@GetMapping("snsMyList")
		public String snsMyListPage(Model model) {
			return "community/snsMyList";
		}
		// 자유게시판 글 등록폼
		@GetMapping("freeBrdForm")
		public String freeBrdFormPage(Model model) {
			return "community/freeBrdForm";
		}
}
