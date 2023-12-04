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

import com.tenniswing.project.attach.service.AttachService;
import com.tenniswing.project.attach.service.AttachVO;
import com.tenniswing.project.common.FileUtils;
import com.tenniswing.project.community.service.BrdService;
import com.tenniswing.project.community.service.BrdVO;
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
	
	@Autowired
	BrdService brdService;
	
	@Autowired
	FileUtils fileUtils;
	
	@Autowired
	AttachService attachService;
	
	// SNS
		// SNS 메인
		@GetMapping("sns")
		public String snsListPage(SnsVO snsVO, Model model) {
			//String id = SecurityContextHolder.getContext().getAuthentication().getName();
			//snsVO.setMemId(id);
			//List<SnsVO> list = snsService.selectAllSnsInfo(snsVO);
			snsService.selectAllSnsInfo(snsVO);
//			for(SnsVO tag : list) {
//				if(tag.getSnsTag() != null) {
//					System.out.println("tata" + tag.getSnsTag().split(","));
//				}
//			}
			//snsService.selectAllSnsInfo(snsVO).get(0).getSnsTag();
			//snsService.selectAllSnsInfo()
			//System.out.println("list aaaa ::: " + list);
			model.addAttribute("snsList", snsService.selectAllSnsInfo(snsVO));
			model.addAttribute("memLikeNo", snsService.selectLikeNo(snsVO));
			

			//첨부파일 불러오기
			//List<AttachVO> attachList =  attachService.attachListAllSns();//이거 땡겨오기
			System.out.println("첨부파일 넘버"+snsService.selectAllSnsInfo(snsVO));
			//첨부파일 dom에 전달		
			//model.addAttribute("attachList", attachList); //땡겨온다음에 여기넣기
			//System.out.println("첨부파일찍음"+attachList);
			
			return "community/community3";
		}
		
		// SNS 수정
		@GetMapping("snsEditForm")
		public String snsEditFormPage(SnsVO snsVO, Model model) {
			model.addAttribute("editList",snsService.selectSnsInfo(snsVO));			
			return "community/snsEditForm";
		}
		
		// SNS 수정처리 기능
		@PostMapping("snsEditForm")
		public String updateSnsForm(SnsVO snsVO, RedirectAttributes rttr, Model model) {
			String id = SecurityContextHolder.getContext().getAuthentication().getName();
			snsVO.setMemId(id);
	
			if(id.equals("anonymousUser")) {
				return "redirect:loginform"; 
			}
			
			snsService.updateSns(snsVO);
			
			rttr.addAttribute("snsWrtNo", snsVO.getSnsWrtNo());
			
			
			//사진 등록
			//테이블 구분, 게시글 번호, 파일목록
			List<AttachVO> files = fileUtils.uploadFiles(snsVO.getFiles());
			
			int n = attachService.updateAttach("s", snsVO.getSnsWrtNo(), files);
			
			if(n > 0) {			
				return "redirect:sns";
				
			}else {
				model.addAttribute("message", "파일등록에 실패하였습니다.");
				return "redirect:sns";
			}
			
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
		public String insertSnsForm(SnsVO snsVO, RedirectAttributes rttr, Model model) {
			String id = SecurityContextHolder.getContext().getAuthentication().getName();
			snsVO.setMemId(id);
			
			/*
			 * for(String tag : snsVO.getSnsTag().replaceAll("[**]", "").split(",")) {
			 * System.out.println(tag); }
			 */
			if(id.equals("anonymousUser")) {
				return "redirect:loginform"; 
			}
			
			snsService.insertSns(snsVO);
			
			rttr.addAttribute("snsWrtNo", snsVO.getSnsWrtNo());
			
			
			//사진 등록
			//테이블 구분, 게시글 번호, 파일목록
			List<AttachVO> files = fileUtils.uploadFiles(snsVO.getFiles());
			
			int n = attachService.saveAttach("s", snsVO.getSnsWrtNo(), files);
			
			if(n > 0) {			
				return "redirect:sns";
				
			}else {
				model.addAttribute("message", "파일등록에 실패하였습니다.");
				return "redirect:sns";
			}
			
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
		// 자유게시판 글 등록폼
		@GetMapping("freeBrdForm")
		public String freeBrdFormPage(Model model) {
			return "community/freeBrdForm";
		}
		
		// 공지사항 게시판 메인(리스트 페이지)
		@GetMapping("noticeList")
		public String noticeListPage(BrdVO brdVO, Model model) {
			model.addAttribute("notice", brdService.selectBrdAllInfo(brdVO));
			return "community/noticeList";
		}
		// 공지사항 리스트 뿌려주는 ajax 
		@GetMapping("noticePage")
		@ResponseBody
		public List<BrdVO> noticeListAjax(BrdVO brdVO) {
			
			return brdService.selectBrdAllInfo(brdVO);
		}
		// 공지사항 상세 페이지
		@GetMapping("noticeDetail")
		public String noticeDetailPage(BrdVO brdVO, Model model) {
			model.addAttribute("nbrd", brdService.selectBrdInfo(brdVO));
			return "community/noticeDetail";
		}
		
		// 공지사항 등록 페이지
		@GetMapping("noticeForm")
		public String noticeFormPage(BrdVO brdVO, Model model) {
			
			return "community/noticeForm";
		}
		
		// 공지사항 등록 처리
		@PostMapping("noticeForm")
		public String insertNoticeForm(BrdVO brdVO, RedirectAttributes rttr, Model model) {
			String id = SecurityContextHolder.getContext().getAuthentication().getName();
			brdVO.setMemId(id);
			
			if(id.equals("anonymousUser")) {
				return "redirect:loginform"; 
			}
			
			brdService.insertBrd(brdVO);
			
			rttr.addAttribute("brdWrtNo", brdVO.getBrdWrtNo());
			
			
			//사진 등록
			//테이블 구분, 게시글 번호, 파일목록
			List<AttachVO> files = fileUtils.uploadFiles(brdVO.getFiles());
			
			int n = attachService.saveAttach("s", brdVO.getBrdWrtNo(), files);
			
			if(n > 0) {			
				return "redirect:noticeList";
				
			}else {
				model.addAttribute("message", "파일등록에 실패하였습니다.");
				return "redirect:noticeList";
			}
			
			
		}
		
		
}
