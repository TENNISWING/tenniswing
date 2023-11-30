package com.tenniswing.project.community.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		public String snsListPage(SnsVO snsVO, Model model) {
			//String id = SecurityContextHolder.getContext().getAuthentication().getName();
			//snsVO.setMemId(id);
			List<SnsVO> list = snsService.selectAllSnsInfo();
			System.out.println("list 찍어봄 ::: " + list);
			model.addAttribute("snsList", snsService.selectAllSnsInfo());
			model.addAttribute("memLikeNo", snsService.selectLikeNo(snsVO));
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
			System.out.println("repVO찍어봄"+snsRepVO);
			int result =  snsRepService.insertSnsRep(snsRepVO);
			
//			if(result>0) {
//				return "redirect:/sns";
//			}else {
//				return "member/singup";
//			}
			
			return result;
		}
		
		// sns 댓글 수정
		@PostMapping("snsReplyEdit")
		@ResponseBody
		public Map<String, Object> EditSnsRepAjax(SnsRepVO snsRepVO) {
			String id = SecurityContextHolder.getContext().getAuthentication().getName();
			snsRepVO.setMemId(id);
			System.out.println("수정아작스컨트롤러 repVO찍어봄"+snsRepVO);
			Map<String, Object> result = snsRepService.updateSnsRep(snsRepVO);
			
			return result;
		}
		
		// sns 댓글 삭제
		@PostMapping("snsReplyDel")
		@ResponseBody
		public boolean deleteRepAjax(@RequestParam("repNo") Integer snsRepNo) {
			boolean result = snsRepService.deleteSnsRep(snsRepNo);
			return result;
		}
		
		// sns 등록폼 페이지
		@GetMapping("snsRegister")
		public String snsRegPage(Model model) {
			model.addAttribute("snsVO", new SnsVO());
			return "community/snsRegister";
		}
		
		// sns 등록 처리
		@PostMapping("snsRegister")
		public String insertSnsForm(SnsVO snsVO, RedirectAttributes rttr) {
			String id = SecurityContextHolder.getContext().getAuthentication().getName();
			snsVO.setMemId(id);

			if(id.equals("anonymousUser")) {
				//return "redirect:loginform";
			}
			snsService.insertSns(snsVO);
			
			rttr.addAttribute("snsWrtNo", snsVO.getSnsWrtNo());
			return "redirect:sns";
			
		}
		
		// sns 좋아요 등록
		@PostMapping("snsLikeInsert")
		@ResponseBody
		public int insertLikeAjax(SnsVO snsVO) {
			String id = SecurityContextHolder.getContext().getAuthentication().getName();
			snsVO.setMemId(id);
			System.out.println(snsVO);
			int result = snsService.insertLike(snsVO);
			
			return result;
		}
		
		// sns 좋아요 삭제
		@PostMapping("snsLikeDel")
		@ResponseBody
		public boolean deleteLikeAjax(@RequestParam("snsLikeNo") Integer likeNo) {
			boolean result = snsService.deleteLike(likeNo);
			return result;
		}
		
		// sns 내가 등록한 게시글 보기
		@GetMapping("snsMyList")
		public String snsMyListPage(Model model) {
			return "community/snsMyList";
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
		
		
		// 자유게시판 글 등록폼
		@GetMapping("freeBrdForm")
		public String freeBrdFormPage(Model model) {
			return "community/freeBrdForm";
		}
}
