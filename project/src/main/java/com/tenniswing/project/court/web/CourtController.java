package com.tenniswing.project.court.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tenniswing.project.attach.service.AttachService;
import com.tenniswing.project.attach.service.AttachVO;
import com.tenniswing.project.common.FileUtils;
import com.tenniswing.project.court.service.CourtroomService;
import com.tenniswing.project.court.service.CrtDetailService;
import com.tenniswing.project.court.service.CrtDetailVO;
import com.tenniswing.project.court.service.CrtRefundVO;
import com.tenniswing.project.court.service.CrtReserveService;
import com.tenniswing.project.court.service.CrtReserveVO;
import com.tenniswing.project.court.service.CrtroomVO;
import com.tenniswing.project.member.service.MemberVO;

@Controller
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
		public String courtPage(Model model) {
			model.addAttribute("courtList", courtroomService.selectAllCourtroomMain());
			model.addAttribute("recentCrt", courtroomService.recentRegiCourt());
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
			System.out.println(crtReserveService.reserveTimeCodeList());
			return "court/courtDetail";
		}
		
		/*
		 * @PostMapping("courtDetail") public String courtDetailReserve(CrtReserveVO
		 * crtReserveVO, RedirectAttributes rttr) { String memId =
		 * SecurityContextHolder.getContext().getAuthentication().getName();
		 * 
		 * crtReserveVO.setMemId(memId);
		 * crtReserveService.insertCrtReserve(crtReserveVO);
		 * 
		 * rttr.addAttribute("reserveNo", crtReserveVO.getReserveNo()); return
		 * "redirect:reserveCourt"; }
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
			Map<String, Object> map = crtReserveService.insertCrtReserve(crtReserveVO);
			
			return map;
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
		
	// 호스트
		// 코트장
			// 목록
			@GetMapping("hostCourtList")
			public String hostCourtListPage(Model model) {
				String hostId = SecurityContextHolder.getContext().getAuthentication().getName();
				
				/*
				 * if(hostId.equals("anonymousUser")) { return "redirect:loginform"; }
				 */
				
				System.out.println(hostId);
				model.addAttribute("courtList", courtroomService.selectAllCourtroom(hostId));
				return "courtHost/hostCourtList";
			}
			
			// 코트들 불러오기
			@PostMapping("courtDetails")
			@ResponseBody
			public CrtroomVO selectCourtDetails(CrtroomVO crtroomVO) {
				CrtroomVO courtDetails = courtroomService.selectCourtroom(crtroomVO);
				return courtDetails;
			}
			
			// 상세보기
			@GetMapping("hostCourtDetail")
			public String hostCourtDetailPage(CrtroomVO crtroomVO, Model model) {
				System.out.println(courtroomService.selectCourtroom(crtroomVO));
				model.addAttribute("courtDetail", courtroomService.selectCourtroom(crtroomVO));
				model.addAttribute("refundInf", crtDetailService.refundInf());
				return "courtHost/hostCourtDetail";
			}
				
			// 등록 - 페이지
			@GetMapping("registerCourtroom")
			public String insertCourtroomForm(Model model) {
				model.addAttribute("crtroomVO", new CrtroomVO());
				return "courtHost/registerCourtroom";
			}
			
			// 등록
			@PostMapping("registerCourtroom")
			public String insertCourtroomProccess(CrtroomVO crtroomVO, RedirectAttributes rttr, Model model) {
				String hostId = SecurityContextHolder.getContext().getAuthentication().getName();
				
				crtroomVO.setHostId(hostId);
				
				//사진 등록
				//테이블 구분, 게시글 번호, 파일목록
				int n = 0;
				List<AttachVO> files = fileUtils.uploadFiles(crtroomVO.getFiles());
				if(!CollectionUtils.isEmpty(files)) {

					n= courtroomService.insertCourtroom(crtroomVO, files);
				}
				
				if(n > 0) {			
					rttr.addAttribute("crtroomNo", crtroomVO.getCrtroomNo());
					return "redirect:registerCourtDetail";
				}else {
					model.addAttribute("message", "파일등록에 실패하였습니다.");
					return "courtHost/registerCourtroom";
				}
				
			}
			
			// 수정 - 페이지
			@GetMapping("editCourtroom")
			public String editCourtroomForm(CrtroomVO crtroomVO, Model model) {
				model.addAttribute("crtroomVO", courtroomService.selectCourtroom(crtroomVO));
				return "courtHost/editCourtroom";
			}
			
			// 수정 - 처리
			@PostMapping("editCourtroom")
			public String editCourtroomProccess(CrtroomVO crtroomVO){
				courtroomService.updateCourtroom(crtroomVO);
				int crtroomNo = crtroomVO.getCrtroomNo();
				return "redirect:hostCourtDetail?crtroomNo="+crtroomNo;
			}
			
			// 삭제
			@PostMapping("deleteCourtroom")
			@ResponseBody
			public boolean deleteCourtroomProccess(@RequestParam("crtNo") Integer crtroomNo) {
				boolean result = courtroomService.deleteCourtroom(crtroomNo);
				/*
				 * String msg = null; if(result) { msg = "정상적으로 삭제되었습니다. \n삭제대상 : "+crtroomNo;
				 * }else { msg = "정상적으로 삭제되지 않았습니다. \n정보를 확인해주시기바랍니다. \n삭제요청 : "+crtroomNo; }
				 * rttr.addFlashAttribute("result", msg);
				 */
				return result;
			}
			
		// 코트 상세
			// 등록 - 페이지
			@GetMapping("registerCourtDetail")
			public String insertCourtDetailForm(Model model) {
				model.addAttribute("crtDetailVO", new CrtDetailVO());
				return "courtHost/registerCourtDetail";
			}
			
			// 등록
			@PostMapping("registerCourtDetail")
			public String insertCourtDetailProccess(CrtDetailVO crtDetailVO, @RequestParam String action, RedirectAttributes rttr, Model model) {
				int n = 0;
				List<AttachVO> files = fileUtils.uploadFiles(crtDetailVO.getFiles());
				if(!CollectionUtils.isEmpty(files)) {
					n = crtDetailService.insertCrtDetail(crtDetailVO, files);
				}
				int crtroomNo = crtDetailVO.getCrtroomNo();
				if(n > 0) {
					if(action.equals("complete")) {
						rttr.addAttribute("crtroomNo", crtroomNo);
						return "redirect:hostCourtDetail";
					}
					else {
						rttr.addAttribute("crtroomNo", crtroomNo);
						return "redirect:registerCourtDetail";
					}
				} else {
						model.addAttribute("message", "파일 등록에 실패했습니다.");
						return "courtHost/registerCourtDetail";
				}
			}
			
			// 수정 - 페이지
			@GetMapping("editCourtDetail")
			public String editCourtDetailForm(CrtDetailVO crtDetailVO, Model model) {
				model.addAttribute("crtDetailVO", crtDetailService.selectCrtDetail(crtDetailVO));
				return "courtHost/editCourtDetail";
			}
			
			// 수정 - 처리
			@PostMapping("editCourtDetail")
			public String editCourtDetailProccess(CrtDetailVO crtDetailVO) {
				crtDetailService.updateCrtDetail(crtDetailVO);
				int crtroomNo = crtDetailService.selectCrtDetail(crtDetailVO).getCrtroomNo();
				return "redirect:hostCourtDetail?crtroomNo="+crtroomNo;
			}
			
			// 삭제
			@PostMapping("deleteCourtDetail")
			@ResponseBody
			public boolean deleteCourtDetailProccess(@RequestParam("crtDeNo") Integer crtDetailNo) {
				/*
				 * int crtroomNo =
				 * crtDetailService.selectCrtDetailNo(crtDetailNo).getCrtroomNo();
				 */
				boolean result = crtDetailService.deleteCrtDetail(crtDetailNo);
				/*
				 * String msg = null; if(result) { msg = "정상적으로 삭제되었습니다. \n삭제대상 : "+crtDetailNo;
				 * }else { msg = "정상적으로 삭제되지 않았습니다. \n정보를 확인해주시기바랍니다. \n삭제요청 : "+crtDetailNo; }
				 */
				
				/* rttr.addFlashAttribute("result", msg); */
				/* return "redirect:hostCourtDetail?crtroomNo="+crtroomNo; */
				return result;
			}
}
