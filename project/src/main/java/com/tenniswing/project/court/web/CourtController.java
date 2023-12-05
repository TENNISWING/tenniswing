package com.tenniswing.project.court.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenniswing.project.attach.service.AttachService;
import com.tenniswing.project.common.FileUtils;
import com.tenniswing.project.court.service.CourtroomService;
import com.tenniswing.project.court.service.CrtDetailService;
import com.tenniswing.project.court.service.CrtRefundVO;
import com.tenniswing.project.court.service.CrtReserveService;
import com.tenniswing.project.court.service.CrtReserveVO;
import com.tenniswing.project.court.service.CrtroomVO;


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
		// crtReserveService.insertCrtReserve(crtReserveVO);
		model.addAttribute("reserveInfo", crtReserveVO);

		return "court/reserveCourt";
	}

	@PostMapping("reserveCourtPost")
	@ResponseBody
	public Map<String, Object> reserveCourtProccess(@RequestBody CrtReserveVO crtReserveVO) {
		// System.out.println(crtReserveVO);
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
	public String refundCourt(CrtRefundVO crtRefundVO) {
		return "court/refundTest";
	}

	@PostMapping("refundTestPost")
	@ResponseBody
	public boolean refundCourtProccess(@RequestBody CrtRefundVO crtRefundVO) {
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
