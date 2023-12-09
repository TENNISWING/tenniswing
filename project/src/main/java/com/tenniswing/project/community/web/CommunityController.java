package com.tenniswing.project.community.web;

import java.util.HashMap;
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
import com.tenniswing.project.community.service.SnsRrepService;
import com.tenniswing.project.community.service.SnsRrepVO;
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
	
	@Autowired
	SnsRrepService snsRrepService;

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

		// 사진 등록
		// 테이블 구분, 게시글 번호, 파일목록
		List<AttachVO> files = fileUtils.uploadFiles(snsVO.getFiles());
		
		// sns 등록
		snsService.insertSns(snsVO ,files);

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
		if (id.equals("anonymousUser")) {
			return "redirect:loginform";
		}

		snsService.updateSns(snsVO);
		
		return "redirect:/snsMyList";
	}

	// SNS 삭제 처리
	@PostMapping("snsDel")
	@ResponseBody
	public boolean snsDelAjax(Integer snsWrtNo) {
		//파일삭제 나중에 스케줄러 쓸때는 사용여부 컬럼 0 을 넘겨주면 됨
		List<AttachVO> list = attachService.attachList("s", snsWrtNo);
		//파일삭제할때 이 경로 사용
		fileUtils.deleteFiles(list);
		
		//sns삭제
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
	public Map<String, Object> mySnsListAjax(SnsVO snsVO){
		HashMap<String, Object> map = new HashMap<>();
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		snsVO.setMemId(id);
	
		 map.put("selectCount", snsService.selectCount(snsVO));
		 map.put("mySnsGroupList",snsService.selectMyGroup(snsVO));
		
		return map;
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
	// sns 대댓글 List
//	@GetMapping("snsRrep")
//	@ResponseBody
//	public List<SnsRepVO> snsRrepPageAjax(SnsRrepVO snsRrepVO) {
//		return snsRepService.selectAllSnsRep(snsRrepVO);
//	}

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
	
	// sns 대댓글 등록
	@PostMapping("snsRreInsert")
	@ResponseBody
	public int insertSnsRrepAjax(SnsRrepVO snsRrepVO, Model model) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		snsRrepVO.setMemId(id);
		System.out.println("rrepVO찍어봄" + snsRrepVO);
		int result = snsRrepService.insertSnsRrep(snsRrepVO);

		return result;
	}
	
	// sns 대댓글 수정
	@PostMapping("snsRreEdit")
	@ResponseBody
	public Map<String, Object> EditSnsRrepAjax(SnsRrepVO snsRrepVO) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		snsRrepVO.setMemId(id);
		System.out.println("수정아작스컨트롤러 repVO찍어봄" + snsRrepVO);
		Map<String, Object> result = snsRrepService.updateSnsRrep(snsRrepVO);

		return result;
	}
	
	// sns 대댓글 삭제
	@PostMapping("snsRreDel")
	@ResponseBody
	public boolean deleteRrepAjax(Integer snsRrepNo) {
		boolean result = snsRrepService.deleteSnsRrep(snsRrepNo);
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
	public boolean deleteLikeAjax(Integer likeNo) {
		boolean result = snsService.deleteLike(likeNo);
		return result;
	}
	
	//스크랩 등록
	@PostMapping("scrapInsert")
	@ResponseBody
	public int insertScrapAjax(SnsVO snsVO) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		snsVO.setMemId(id);
		int result = snsService.insertScrap(snsVO);
		
		return result;
	}
	//스크랩 삭제 
	@PostMapping("scrapDelete")
	@ResponseBody
	public boolean deleteScrapAjax(Integer scrapNo,SnsVO snsVO) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		snsVO.setMemId(id);
		boolean result = snsService.deleteScrap(scrapNo);
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
		//model.addAttribute("myGrpList", snsService.selectMyGroup(snsVO));
		
		// 그룹이 Null인 sns 불러오기
		//model.addAttribute("nullGrp",snsService.selectGrpNull(snsVO));
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
