package com.tenniswing.project.court.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tenniswing.project.court.service.CourtroomService;
import com.tenniswing.project.court.service.CrtDetailService;
import com.tenniswing.project.court.service.CrtDetailVO;
import com.tenniswing.project.court.service.CrtroomVO;

@Controller
public class CourtController {

	@Autowired
	CourtroomService courtroomService;

	@Autowired
	CrtDetailService crtDetailService;

	// 메인

		@GetMapping("court")  
		public String courtPage(Model model) { 			
			return "court/court";
		}
		
		@GetMapping("courtDetail")  
		public String courtDetailPage(Model model) { 			
			return "court/courtDetail";
		}
		
		@GetMapping("reserveCourt")  
		public String reserveCourtPage(Model model) { 			
			return "court/reserveCourt";
		}
		
	// 호스트
		// 코트장
			// 목록
			@GetMapping("hostCourtList")
			public String hostCourtListPage(Model model) {
				model.addAttribute("courtList", courtroomService.selectAllCourtroom());
				return "courtHost/hostCourtList";
			}
			
			// 상세보기
			@GetMapping("hostCourtDetail")
			public String hostCourtDetailPage(CrtroomVO crtroomVO, Model model) {
				System.out.println(courtroomService.selectCourtroom(crtroomVO));
				model.addAttribute("courtDetail", courtroomService.selectCourtroom(crtroomVO));
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
			public String insertCourtroomProcess(CrtroomVO crtroomVO, RedirectAttributes rttr) {
				courtroomService.insertCourtroom(crtroomVO);
				
				rttr.addAttribute("crtroomNo", crtroomVO.getCrtroomNo());
				return "redirect:registerCourtDetail";
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
				
				return "redirect:hostCourtList";
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
			public String insertCourtDetailProcess(CrtDetailVO crtDetailVO, @RequestParam String action, RedirectAttributes rttr) {
				crtDetailService.insertCrtDetail(crtDetailVO);
				
				int crtroomNo = crtDetailVO.getCrtroomNo();
				
				if(action.equals("complete")) {
					return "redirect:hostCourtDetail?crtroomNo="+crtroomNo;
				}
				else {
					rttr.addAttribute("crtroomNo", crtroomNo);
					return "redirect:registerCourtDetail";
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
				return "redirect:hostCourtList";
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
