package com.tenniswing.project.court.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenniswing.project.attach.service.AttachService;
import com.tenniswing.project.attach.service.AttachVO;
import com.tenniswing.project.common.FileUtils;
import com.tenniswing.project.common.PagingVO;
import com.tenniswing.project.court.service.CourtroomService;
import com.tenniswing.project.court.service.CrtDetailService;
import com.tenniswing.project.court.service.CrtRefundVO;
import com.tenniswing.project.court.service.CrtReserveService;
import com.tenniswing.project.court.service.CrtReserveVO;
import com.tenniswing.project.court.service.CrtroomVO;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class CourtController {

	@Autowired
	CourtroomService courtroomService;

	@Autowired
	CrtDetailService crtDetailService;

	@Autowired
	CrtReserveService crtReserveService;

	@Autowired
	FileUtils fileUtils;

	@Autowired
	AttachService attachService;

	// 메인

		@GetMapping("court")  
		public String courtPage(CrtroomVO crtroomVO, PagingVO pagingVO, Model model) {
			//페이징처리
			pagingVO.setTotalRecord(courtroomService.selectCount(crtroomVO));
			
			model.addAttribute("courtList", courtroomService.selectAllCourtroomMain(crtroomVO));
			model.addAttribute("recentCrt", courtroomService.recentRegiCourt());
			model.addAttribute("paging", pagingVO);
			return "court/court";
		}
		
		@GetMapping("courtDetail")  
		public String courtDetailPage(CrtroomVO crtroomVO, Model model) { 
			CrtroomVO courtroom = courtroomService.selectCourtroom(crtroomVO);
			model.addAttribute("courtDetail", courtroom);
			model.addAttribute("reserveInfo", new CrtReserveVO());
			model.addAttribute("reserveTimeList", crtReserveService.reserveTimeCodeList());
			model.addAttribute("refundInf", crtDetailService.refundInf());
			model.addAttribute("nearCourt", courtroomService.nearCrtroom(courtroom));
			model.addAttribute("hostInfo", courtroomService.selectCrtDetailHost(courtroom.getHostId()));
			model.addAttribute("courtReview", courtroomService.selectCourtReview(courtroom.getCrtroomNo()));
			model.addAttribute("crtroomStar", courtroomService.crtroomStar(courtroom.getCrtroomNo()));
			//System.out.println("--------------"+courtroom);
			return "court/courtDetail";
		}
		
		@PostMapping("insertCourtReview")
		@ResponseBody
		public Map<String, Object> insertCourtReview(CrtroomVO crtroomVO, Model model) {
			Map<String, Object> map = new HashMap<>();
			String memId = SecurityContextHolder.getContext().getAuthentication().getName();
			crtroomVO.setMemId(memId);
			
			Integer reserveNo = courtroomService.confirmInsertReview(crtroomVO);
			if(reserveNo == null) {
				map.put("result", false);
				map.put("review", "이용 전이거나 예약건이 없습니다.");
				return map;
			}
			crtroomVO.setReserveNo(reserveNo);
			//사진 등록
			//테이블 구분, 게시글 번호, 파일목록
			int n = 0;
			boolean isSuccessed = true;
			
			List<AttachVO> files = fileUtils.uploadFiles(crtroomVO.getFiles());
			int reviewNo = courtroomService.insertCourtReview(crtroomVO, files);
			
			/*
			 * int crtroomNo = crtroomVO.getCrtroomNo(); List<CrtroomVO> reviewList =
			 * courtroomService.selectCourtReview(crtroomNo);
			 */
			
			log.info("findEmp========");
			
			CrtroomVO review = courtroomService.selectReview(reviewNo);
			map.put("reviews", review);
			map.put("result", isSuccessed);
			return map;
		}
		
		// 예약한 회원인지 확인
		@PostMapping("confirmReserve")
		@ResponseBody
		public Integer confirmReserve(CrtroomVO crtroomVO) {
			String memId = SecurityContextHolder.getContext().getAuthentication().getName();
			if(memId.equals("anonymousUser")){
				return 0;
			}else {
				crtroomVO.setMemId(memId);
			}
			Integer reserveNo = courtroomService.confirmInsertReview(crtroomVO);
			if(reserveNo == null || reserveNo == 0) {
				return 0;
			} else {
				return courtroomService.confirmInsertReview(crtroomVO);
			}
		}
		
		/*
		 * MemberVO member = (MemberVO)
		 * SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 * 
		 * if(member == null) { return "redirect:loginform"; }
		 */
		
		@GetMapping("impossibleReserve")
		@ResponseBody
		public List<CrtReserveVO> impossibleReserve(CrtReserveVO crtReserveVO) {
			return crtReserveService.impossibleReserveList(crtReserveVO);
		}
		
		@GetMapping("reserveCourt")
		public String reserveCourtForm(CrtReserveVO crtReserveVO, Model model) {
			//crtReserveService.insertCrtReserve(crtReserveVO);
			model.addAttribute("reserveInfo",crtReserveVO);
			
			return "court/reserveCourt";
		}
		
		@PostMapping("reserveCourtPost")
		@ResponseBody
		public Map<String, Object> reserveCourtProccess(@RequestBody CrtReserveVO crtReserveVO) {
			//System.out.println(crtReserveVO);
			/*
			 * MemberVO member = (MemberVO)
			 * SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			 * 
			 * if(member == null) { return "redirect:loginform"; }
			 */
			String memId = SecurityContextHolder.getContext().getAuthentication().getName();
			crtReserveVO.setMemId(memId);
			Map<String, Object> map = crtReserveService.insertCrtReserve(crtReserveVO);
			
			return map;
		}
		
		@PostMapping("deleteReview")
		@ResponseBody
		public boolean deleteReview(@RequestParam Integer reviewNo) {
			boolean result = courtroomService.deleteReview(reviewNo);
			/*
			 * String msg = null; if(result) { msg = "정상적으로 삭제되었습니다. \n삭제대상 : "+crtroomNo;
			 * }else { msg = "정상적으로 삭제되지 않았습니다. \n정보를 확인해주시기바랍니다. \n삭제요청 : "+crtroomNo; }
			 * rttr.addFlashAttribute("result", msg);
			 */
			return result;
		}
		
		// 환불 테스트
		@GetMapping("refundTest")
		public String refundCourt(CrtRefundVO crtRefundVO){
			return "court/refundTest";
		}
		
		@PostMapping("refundTestPost")
		@ResponseBody
		public boolean refundCourtProccess(@RequestBody CrtRefundVO crtRefundVO){
			boolean result = true;
			
			return result;
		}

	// 검색
	@GetMapping("searcharea")
	@ResponseBody
	public List<CrtroomVO> searchProcess(@RequestParam(value = "inputVal") String str) {
		System.out.println("=====" + str);

		// System.out.println(courtroomService.courtSearch(str));

		return courtroomService.courtSearch(str);
	}
}
