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
			public String editCourtroomForm(Model model) {
				model.addAttribute("crtroomVO", new CrtroomVO());
				return "courtHost/editCourtroom";
			}
			
			// 수정 - 처리
			@PostMapping("editCourtroom")
			@ResponseBody
			public Map<String, Object> editCourtroomProccess(@RequestBody CrtroomVO crtroomVO){
				return courtroomService.updateCourtroom(crtroomVO);
			}
			
		// 코트 상세
			// 등록 - 페이지
			@GetMapping("registerCourtDetail")
			public String insertCourtDetailForm(Model model) {
				model.addAttribute("crtDetailVO", new CrtroomVO());
				return "courtHost/registerCourtDetail";
			}
			
			// 등록
			@PostMapping("registerCourtDetail")
			public String insertCourtDetailProcess(CrtroomVO crtroomVO, @RequestParam String action, RedirectAttributes rttr) {
				crtDetailService.insertCrtDetail(crtroomVO);
				
				if(action.equals("complete")) {
					return "redirect:hostCourtList";
				}
				else {
					rttr.addAttribute("crtroomNo", crtroomVO.getCrtroomNo());
					return "redirect:registerCourtDetail";
				}
			}
			
			// 수정 - 페이지
			@GetMapping("editCourtDetail")
			public String editCourtDetailForm(Model model) {
				model.addAttribute("crtDetailVO", new CrtroomVO());
				return "courtHost/editCourtDetail";
			}		
}
