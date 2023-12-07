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
import com.tenniswing.project.common.PagingVO;
import com.tenniswing.project.community.service.BrdService;
import com.tenniswing.project.community.service.BrdVO;
import com.tenniswing.project.community.service.SnsRepService;
import com.tenniswing.project.community.service.SnsRepVO;
import com.tenniswing.project.community.service.SnsService;
import com.tenniswing.project.community.service.SnsVO;

import groovy.util.logging.Slf4j;

@Controller
@Slf4j
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
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		snsVO.setMemId(id);
		model.addAttribute("snsList", snsService.selectAllSnsInfo(snsVO));
		return "community/community3";
	}

	// sns 등록폼 페이지
	@GetMapping("snsRegister")
	public String snsRegPage(Model model) {
		SnsVO snsVO = new SnsVO();
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		snsVO.setMemId(id);
		// List<SnsVO> list = snsService.그룹셀렉트
		model.addAttribute("snsVO", snsVO);
		model.addAttribute("grpList", snsService.selectGroup(snsVO));
		return "community/snsRegister";
	}

	// sns 등록 처리
	@PostMapping("snsRegister")
	public String insertSnsForm(SnsVO snsVO, RedirectAttributes rttr, Model model) {

		//로그인체크
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		snsVO.setMemId(id);
		if (id.equals("anonymousUser")) {
			return "redirect:loginform";
		}

		// sns 등록
		snsService.insertSns(snsVO);

		// 사진 등록
		// 테이블 구분, 게시글 번호, 파일목록
		List<AttachVO> files = fileUtils.uploadFiles(snsVO.getFiles());
		int n = attachService.saveAttach("s", snsVO.getSnsWrtNo(), files);


		// sns페이지로 이동
		rttr.addFlashAttribute("message", "등록되었습니다"); //플래시는 한번만 뜸 휘발성.
		return "redirect:sns";


	}

	// SNS 수정
	@GetMapping("snsEditForm")
	public String snsEditFormPage(SnsVO snsVO, Model model) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		snsVO.setMemId(id);

		model.addAttribute("editList", snsService.selectSnsInfo(snsVO));
		model.addAttribute("grpList", snsService.selectGroup(snsVO));
		return "community/snsEditForm";
	}

	// SNS 수정처리 기능
	@PostMapping("snsEditForm")
	public String updateSnsForm(SnsVO snsVO, RedirectAttributes rttr, Model model) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		snsVO.setMemId(id);
		System.out.println("==수정VO찍어봄===" + snsVO);
		if (id.equals("anonymousUser")) {
			return "redirect:loginform";
		}

		snsService.updateSns(snsVO);

		// 사진 등록
		// 테이블 구분, 게시글 번호, 파일목록

		List<AttachVO> files = fileUtils.uploadFiles(snsVO.getFiles());
//			if(files!=null && files.size()>0) {
//				//삭제로직
//				attachService.deleteAttachSns(tablePk);
//			}
		int n = attachService.updateAttach("s", snsVO.getSnsWrtNo(), files);
		return "redirect:/sns";


	}

	// SNS 삭제 처리
	@PostMapping("snsDel")
	@ResponseBody
	public boolean snsDelAjax(Integer snsWrtNo) {
		boolean result = snsService.deleteSns(snsWrtNo);
		System.out.println("결과찍어봄" + result);
		return result;
	}
	
	// SNS, 그룹 삭제 처리
	@PostMapping("grpDel")
	@ResponseBody
	public boolean snsDelGrpAjax(Integer snsGrpNo) {
		boolean result = snsService.deleteGrp(snsGrpNo);
		System.out.println("그룹삭제컨트롤러>>"+result);
		return result;
	}
	
	// 그룹에 매칭되는 sns List
	@GetMapping("mySnsList")
	@ResponseBody
	public List<SnsVO> mySnsListAjax(SnsVO snsVO){
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		snsVO.setMemId(id);
		
		return snsService.selectMyGroup(snsVO);
	}
	
	// 그룹 이름 수정
	@PostMapping("editGrpName")
	@ResponseBody
	public Map<String, Object> editGrpNameAjax(SnsVO snsVO){
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		snsVO.setMemId(id);
		
		Map<String, Object> result = snsService.updateGrp(snsVO);
	
		return result;
		
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
		System.out.println("repVO찍어봄" + snsRepVO);
		int result = snsRepService.insertSnsRep(snsRepVO);

		/*
		 * if(result>0) { return "redirect:/sns"; }else { return "member/singup"; }
		 */

		return result;
	}

	// sns 댓글 수정
	@PostMapping("snsReplyEdit")
	@ResponseBody
	public Map<String, Object> EditSnsRepAjax(SnsRepVO snsRepVO) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		snsRepVO.setMemId(id);
		System.out.println("수정아작스컨트롤러 repVO찍어봄" + snsRepVO);
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
	public String snsMyListPage(SnsVO snsVO, Model model) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		snsVO.setMemId(id);
		
		//회원의 그룹 불러오기
		model.addAttribute("grpList", snsService.selectGroup(snsVO));
		
		//그 그룹에 해당하는 sns 불러오기
		model.addAttribute("myGrpList", snsService.selectMyGroup(snsVO));
		return "community/snsMyList";
	}

	// sns 그룹 등록
	@PostMapping("snsGrpInsert")
	@ResponseBody
	public int snsGrpInsertAjax(SnsVO snsVO) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		snsVO.setMemId(id);
		System.out.println("그룹찍어봄" + snsVO);

		return snsService.insertSnsGrp(snsVO);
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
	public String noticeListPage(BrdVO brdVO, Model model, PagingVO pagingVO) {
		//페이징처리
		pagingVO.setTotalRecord(brdService.selectCount(brdVO));
		
		model.addAttribute("notice", brdService.selectBrdAllInfo(brdVO));
		model.addAttribute("paging", pagingVO);
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
		String id = SecurityContextHolder.getContext().getAuthentication().getName(); // 로그인 아닌 권한체크로 변경 
		brdVO.setMemId(id);
		
		
		if (id.equals("anonymousUser")) {
			return "redirect:loginform";
		}

		brdService.insertBrd(brdVO);

		rttr.addFlashAttribute("message", "등록되었습니다.");

		// 사진 등록
		// 테이블 구분, 게시글 번호, 파일목록
		List<AttachVO> files = fileUtils.uploadFiles(brdVO.getFiles());

		int n = attachService.saveAttach("s", brdVO.getBrdWrtNo(), files);

	
		return "redirect:noticeList";


	}

}
