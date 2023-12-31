package com.tenniswing.project.host.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tenniswing.project.attach.service.AttachService;
import com.tenniswing.project.attach.service.AttachVO;
import com.tenniswing.project.common.FileUtils;
import com.tenniswing.project.court.service.CourtroomService;
import com.tenniswing.project.court.service.CrtDetailService;
import com.tenniswing.project.court.service.CrtDetailVO;
import com.tenniswing.project.court.service.CrtReserveService;
import com.tenniswing.project.court.service.CrtroomVO;
import com.tenniswing.project.court.service.HostDashBoardVO;

@Controller
public class HostController {

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

	// 호스트
	// 코트장
	// 목록
	@GetMapping("hostCourtList")
	public String hostCourtListPage(Model model) {
		String hostId = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("courtList", courtroomService.selectAllCourtroom(hostId));
		return "host/hostCourtList";
	}

	// 코트들 불러오기
	@PostMapping("hostcourtDetails")
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
		model.addAttribute("review",courtroomService.selectCourtReview(crtroomVO.getCrtroomNo()));
		return "host/hostCourtDetail";
	}

	// 등록 - 페이지
	@GetMapping("hostregisterCourtroom")
	public String insertCourtroomForm(Model model) {
		model.addAttribute("crtroomVO", new CrtroomVO());
		return "host/registerCourtroom";
	}

	// 등록
	@PostMapping("hostregisterCourtroom")
	public String insertCourtroomProccess(CrtroomVO crtroomVO, RedirectAttributes rttr, Model model) {
		String hostId = SecurityContextHolder.getContext().getAuthentication().getName();

		crtroomVO.setHostId(hostId);

		// 사진 등록
		// 테이블 구분, 게시글 번호, 파일목록
		int n = 0;
		List<AttachVO> files = fileUtils.uploadFiles(crtroomVO.getFiles());
		if (!CollectionUtils.isEmpty(files)) {

			n = courtroomService.insertCourtroom(crtroomVO, files);
		}

		if (n > 0) {
			rttr.addAttribute("crtroomNo", crtroomVO.getCrtroomNo());
			return "redirect:hostregisterCourtDetail";
		} else {
			model.addAttribute("message", "파일등록에 실패하였습니다.");
			return "host/registerCourtroom";
		}

	}

	// 수정 - 페이지
	@GetMapping("hosteditCourtroom")
	public String editCourtroomForm(CrtroomVO crtroomVO, Model model) {
		model.addAttribute("crtroomVO", courtroomService.selectCourtroom(crtroomVO));
		return "host/editCourtroom";
	}

	// 수정 - 처리
	@PostMapping("hosteditCourtroom")
	public String editCourtroomProccess(CrtroomVO crtroomVO) {
		courtroomService.updateCourtroom(crtroomVO);
		int crtroomNo = crtroomVO.getCrtroomNo();
		return "redirect:hostCourtDetail?crtroomNo=" + crtroomNo;
	}

	// 삭제
	@PostMapping("hostdeleteCourtroom")
	@ResponseBody
	public Map<String, Object> deleteCourtroomProccess(@RequestParam("crtNo") Integer crtroomNo) {
		Map<String, Object> map = courtroomService.deleteCourtroom(crtroomNo);

		// Map<String, Object> resultmap = new HashMap<>();
		// if(map.get("reserveCount").equals(0)) {
		// 	resultmap.put("result", true);
		// }else {
		// 	resultmap.put("result", false);
		// 	resultmap.put("reserveCount", map.get("reserveCount"));
		// }

		/*
		 * Map<String, Object> resultmap = new HashMap<>();
		 * if(resultmap.get("reserveCount").equals(0)) { resultmap.put("result", true);
		 * }else { resultmap.put("result", false); resultmap.put("reserveCount",
		 * resultmap.get("reserveCount")); }
		 */


		/*
		 * String msg = null; if(result) { msg = "정상적으로 삭제되었습니다. \n삭제대상 : "+crtroomNo;
		 * }else { msg = "정상적으로 삭제되지 않았습니다. \n정보를 확인해주시기바랍니다. \n삭제요청 : "+crtroomNo; }
		 * rttr.addFlashAttribute("result", msg);
		 */

		//return map;
		return map;
	}

	// 코트 상세
	// 등록 - 페이지
	@GetMapping("hostregisterCourtDetail")
	public String insertCourtDetailForm(Model model) {
		model.addAttribute("crtDetailVO", new CrtDetailVO());
		return "host/registerCourtDetail";
	}

	// 등록
	@PostMapping("hostregisterCourtDetail")
	public String insertCourtDetailProccess(CrtDetailVO crtDetailVO, @RequestParam String action,
			RedirectAttributes rttr, Model model) {
		int n = 0;
		List<AttachVO> files = fileUtils.uploadFiles(crtDetailVO.getFiles());
		if (!CollectionUtils.isEmpty(files)) {
			n = crtDetailService.insertCrtDetail(crtDetailVO, files);
		}
		int crtroomNo = crtDetailVO.getCrtroomNo();
		if (n > 0) {
			if (action.equals("complete")) {
				rttr.addAttribute("crtroomNo", crtroomNo);
				return "redirect:hostCourtDetail";
			} else {
				rttr.addAttribute("crtroomNo", crtroomNo);
				return "redirect:hostregisterCourtDetail";
			}
		} else {
			model.addAttribute("message", "파일 등록에 실패했습니다.");
			return "host/registerCourtDetail";
		}
	}

	// 수정 - 페이지
	@GetMapping("hosteditCourtDetail")
	public String editCourtDetailForm(CrtDetailVO crtDetailVO, Model model) {
		model.addAttribute("crtDetailVO", crtDetailService.selectCrtDetail(crtDetailVO));
		return "host/editCourtDetail";
	}

	// 수정 - 처리
	@PostMapping("hosteditCourtDetail")
	public String editCourtDetailProccess(CrtDetailVO crtDetailVO) {
		crtDetailService.updateCrtDetail(crtDetailVO);
		int crtroomNo = crtDetailService.selectCrtDetail(crtDetailVO).getCrtroomNo();
		return "redirect:hostCourtDetail?crtroomNo=" + crtroomNo;
	}

	// 삭제
	@PostMapping("hostdeleteCourtDetail")
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

	// 예약 리스트
	@GetMapping("reserveList")
	public String reserveListPage(Model model) {
		String hostId = SecurityContextHolder.getContext().getAuthentication().getName();
		// model.addAttribute("hostCalcList",
		// courtroomService.selectAllCourtroom(hostId));
		model.addAttribute("reserveList", crtReserveService.selectAllCrtReserve(hostId));
		return "host/reserveList";
	}

	// 환불 리스트
	@GetMapping("reserveCancelList")
	public String reserveCancelListPage(Model model) {
		String hostId = SecurityContextHolder.getContext().getAuthentication().getName();
		// model.addAttribute("hostCalcList",
		// courtroomService.selectAllCourtroom(hostId));
		model.addAttribute("refundList", crtReserveService.selectAllCrtRefund(hostId));
		return "host/reserveCancelList";
	}

	// 정산 리스트
	@GetMapping("hostCalcList")
	public String hostCalcListPage(Model model) {
		String hostId = SecurityContextHolder.getContext().getAuthentication().getName();
		// model.addAttribute("hostCalcList",
		// courtroomService.selectAllCourtroom(hostId));
		model.addAttribute("calcList", crtReserveService.selectAllCrtCalc(hostId));
		return "host/hostCalcList";
	}
	
	
	@GetMapping("/host")
	public String hostPage(Model model) { 		
		String hostId = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("hostMonthReserve", crtReserveService.hostMonthReserve(hostId));
		model.addAttribute("hostMonthRefund", crtReserveService.hostMonthRefund(hostId));
		model.addAttribute("todayReserve", crtReserveService.todayReserve(hostId));
		if(crtReserveService.thisYearMonthPrice(hostId) == null) {
			HostDashBoardVO vo = new HostDashBoardVO();
			vo.setJan(0);
			vo.setFeb(0);
			vo.setMar(0);
			vo.setApr(0);
			vo.setMay(0);
			vo.setJun(0);
			vo.setJul(0);
			vo.setAug(0);
			vo.setOct(0);
			vo.setSep(0);
			vo.setNov(0);
			vo.setDec(0);
			model.addAttribute("yearPrice", vo);
		}else {
			model.addAttribute("yearPrice", crtReserveService.thisYearMonthPrice(hostId));
		}
		model.addAttribute("recentReview", crtReserveService.recentReview(hostId));
		model.addAttribute("hostInfo", crtReserveService.hostInfo(hostId));
		if(crtReserveService.hostStarInfo(hostId) == null) {
			HostDashBoardVO vos = new HostDashBoardVO();
			vos.setOne(0);
			vos.setTwo(0);
			vos.setThree(0);
			vos.setFour(0);
			vos.setFive(0);
			model.addAttribute("hostStar", vos);
		}else {
			model.addAttribute("hostStar", crtReserveService.hostStarInfo(hostId));
			
		}
		
		return "host/dashboard";
	}
}
