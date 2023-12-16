package com.tenniswing.project.club.web;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tenniswing.project.attach.service.AttachService;
import com.tenniswing.project.attach.service.AttachVO;
import com.tenniswing.project.club.service.ClubMatchService;
import com.tenniswing.project.club.service.ClubPostService;
import com.tenniswing.project.club.service.ClubPostVO;
import com.tenniswing.project.club.service.ClubRepService;
import com.tenniswing.project.club.service.ClubRepVO;
import com.tenniswing.project.club.service.ClubService;
import com.tenniswing.project.club.service.ClubVO;
import com.tenniswing.project.common.FileUtils;
import com.tenniswing.project.match.service.MatchService;
import com.tenniswing.project.match.service.MatchVO;
import com.tenniswing.project.member.service.MemberService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class ClubController {

	@Autowired
	ClubService clubService;

	@Autowired
	ClubPostService clubPostService;

	@Autowired
	ClubRepService clubRepService;
	
	@Autowired
	ClubMatchService clubMatchService;
	
	@Autowired
	MatchService matchService;
	
	@Autowired
	MemberService memberService;
	

	@Autowired
	FileUtils fileUtils;

	@Autowired
	AttachService attachService;

// --------------------------------------- 메인	
	// 클럽페이지
	@GetMapping("club")
	public String clubPage(Model model) {
		// model.addAttribute("clubList", clubService.selectAllClub());
		//log.debug("clubList---------------");
		return "club/club";
	}

	// 클럽리스트
	 @GetMapping("clubList")
	 @ResponseBody 
	 public Map<String, Object> clubListPage(ClubVO clubVO) {
		 
		 String id = SecurityContextHolder.getContext().getAuthentication().getName();
	     clubVO.setMemId(id);

		 HashMap<String, Object> map = new HashMap<>();
		 map.put("selectCount", clubService.selectCount(clubVO));
		 map.put("clubList",clubService.selectAllClub(clubVO));
		 map.put("memcheck", clubService.selectCheckMem(clubVO));
		 System.out.println("멤체크"+clubService.selectCheckMem(clubVO));
		 return map;
	 }

	// 등록 페이지
	@GetMapping("clubform")
	public String clubFormPage(Model model) {
		// 처음 입력 폼은 모두 비워져야해서 빈 객체(new new ClubVO()) 전달
		model.addAttribute("clubVO", new ClubVO());
		return "club/clubform";
	}

	// 등록 프로세스
	@PostMapping("clubform")
	public String insertClubProcess(ClubVO clubVO, List<MultipartFile> files) {

		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		clubVO.setMemId(id);

		// 사진 등록
		List<AttachVO> filelist = fileUtils.uploadFiles(files);

		clubService.insertClub(clubVO);
		attachService.saveAttach("cl", clubVO.getClubNo(), filelist); // 구분코드, 번호, 파일목록

		return "redirect:club";

	}
	
	//클럽 가입 신청
	@PostMapping("insertClubMem")
	@ResponseBody
	public String insertClubMemAjax(ClubVO clubVO) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		clubVO.setMemId(id);
		clubService.insertClubMem(clubVO);
		return "redirect:club";
	}


// ---------------------------------------상세페이지

	// 단건(상세)조회
	@GetMapping("clubdetail")
	public String clubDetailPage(Model model, ClubVO clubVO) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		//clubVO.setMemId(id);
		
		model.addAttribute("club", clubService.selectClub(clubVO));
		model.addAttribute("loginId", id);
		System.out.println(id+"=@@@@@===");
		return "club/clubdetail";
	}

	// 상세페이지 > 탭 > 클럽정보
	@GetMapping("clubInfo")
	public String infoTapPage(Model model, ClubVO clubVO) {
		//System.out.println("@@@@@@@@@@@@클럽정보탭에 들어왔는데"+clubVO);
		//String id = SecurityContextHolder.getContext().getAuthentication().getName();
		//clubVO.setMemId(id);
		model.addAttribute("club", clubService.selectClub(clubVO));
		
		return "club/clubInfo";
	}

	// 클럽 삭제
	@PostMapping("clubdelete")
	@ResponseBody
	public boolean deleteClubAjax(@RequestParam("paramclubNo") Integer clubNo) {
		boolean result = clubService.deleteClub(clubNo);
		return result;
	}

	// 클럽 수정
	@PostMapping("clubUpdate")
	@ResponseBody
	public Map<String, Object> clubUpdateFormAjax(ClubVO clubVO) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		Map<String, Object> map = new HashMap<>();
		// 클럽 단건조회(클럽장 조회할려고)
		ClubVO dbClub = clubService.selectClub(clubVO);
		if (dbClub.getMemId().equals(id)) {
			// System.out.println("-----수정아작스컨트롤러 "+clubVO);
			map = clubService.updateClub(clubVO);
		} else {
			map.put("result", false);
			map.put("info", "권한이 없습니다.");
		}
		return map;
	}

	
	// --------------------------------------- 매치 모집
	
	
	// 상세페이지 > 탭 > 매치모집
	@GetMapping("clubMatchJoin")
	public String joinTapPage(Model model, ClubVO clubVO) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		clubVO.setMemId(id);
		model.addAttribute("member", memberService.memberInfo(id));
		return "club/clubMatchJoin";
	}
	
	//매치 모집 리스트
	@GetMapping("recruitList")
	@ResponseBody
	public List<ClubVO> recruitListAjax(ClubVO clubVO) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		clubVO.setMemId(id);
		clubVO.setRecruitState("l2");
		return clubMatchService.selectAllMatchRecruit(clubVO);
	}
	
	// 매치 모집 등록
	@PostMapping("recruitInsert")
	@ResponseBody
	public String MatchRecruitInsertAjax(ClubVO clubVO) {
		clubMatchService.insertMatchRecruit(clubVO);
		return "redirect:clubMatchJoin";
	}
	
	//매치 모집 신청(참여멤 추가)
	@PostMapping("insertRecruitMem")
	@ResponseBody
	public String insertRecruitMemAjax(ClubVO clubVO) {
		clubMatchService.insertRecruitMem(clubVO);
		return "redirect:clubMatchJoin";
	}
	
	//매치 모집 삭제
		@PostMapping("recDelete")
		@ResponseBody
		public boolean deleteRecAjax(@RequestParam("paramRecNo") Integer clubMatchRecruitNo) {
			boolean result = clubMatchService.clubRecDelete(clubMatchRecruitNo);
			return result;
		}
	
	// --------------------------------------- 매치 일정

	// 상세페이지 > 탭 > 매치일정
	@GetMapping("clubMatchDate")
	public String DateTapPage(Model model, ClubVO clubVO) {
		return "club/clubMatchDate";
	}
	
	//매치 일정 리스트
	@GetMapping("selectClubList")
	@ResponseBody
	public List<MatchVO> MatchListAjax(MatchVO matchVO) {
		matchVO.setDiv("bj1");
		return matchService.selectClubList(matchVO).get("before");
	}
	
	//매치 신청 리스트
	@GetMapping("selectHistList")
	@ResponseBody
	public List<MatchVO> histListAjax(MatchVO matchVO) {
		return matchService.selectHistList(matchVO);
	}	
	
	// --------------------------------------- 매치 결과

	// 상세페이지 > 탭 > 매치결과
	@GetMapping("clubMatchEnd")
	public String EndTapPage(ClubVO clubVO ) {
		return "club/clubMatchEnd";
	}
	
	//매치 결과 리스트
		@GetMapping("selectClubEndList")
		@ResponseBody
		public List<MatchVO> MatchEndListAjax(MatchVO matchVO,Model model) {
			model.addAttribute("hist", matchService.selectHistList(matchVO));
			return matchService.selectClubList(matchVO).get("after");
		}
		

	//매치 결과 입력
		@PostMapping("insertResult")
		@ResponseBody
		public String resultAjax(ClubVO clubVO) {
			clubMatchService.insertResult(clubVO);
			return "redirect:clubMatchEnd";
		}
		
	//매치 결과 수정
		@PostMapping("editResult")
		@ResponseBody
		public int editresultAjax(ClubVO clubVO) {
			return clubMatchService.editResult(clubVO);
		}
		
	
	// --------------------------------------- 자유게시판
	
	// 상세페이지 > 탭 > 자유게시판
	@GetMapping("clubPost")
	public String boardTapPage(Model model, ClubVO clubVO) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		clubVO.setMemId(id);
		//model.addAttribute("club", clubService.selectClub(clubVO));

		return "club/clubPost";
	}

	//자유게시판 리스트
	@GetMapping("postList")
	@ResponseBody
	public Map<String, Object> postListPage(ClubPostVO clubPostVO) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("selectCount", clubPostService.selectCount(clubPostVO));
		map.put("clubPost",clubPostService.selectAllPost(clubPostVO));
		return map;
	}

	//자유게시판 등록
	@PostMapping("postInsert")
	@ResponseBody
	public String postInsertAjax(ClubPostVO clubPostVO, RedirectAttributes rttr) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		clubPostVO.setMemId(id);
		clubPostService.insertPost(clubPostVO);

		// 사진 등록
		List<AttachVO> files = fileUtils.uploadFiles(clubPostVO.getFiles());
		int n = attachService.saveAttach("cp", clubPostVO.getClubPostNo(), files); // 구분코드, 번호, 파일목록

		if (n > 0) {
			rttr.addAttribute("clubNo", clubPostVO.getClubNo());
			return "redirect:clubPost";
		} else {
			rttr.addAttribute("message", "파일등록에 실패하였습니다.");
			return "redirect:clubPost";
		}
	}

	//자유게시판 삭제
	@PostMapping("postDelete")
	@ResponseBody
	public boolean deletePostAjax(@RequestParam("paramPostNo") Integer clubPostNo) {
		boolean result = clubPostService.deletePost(clubPostNo);
		return result;
	}

	//자유게시판 수정
	@PostMapping("postUpdate")
	@ResponseBody
	public Map<String, Object> postUpdateFormAjax(ClubPostVO clubPostVO) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println("55555555555555555555555555"+clubPostVO);
		clubPostVO.setMemId(id);
		Map<String, Object> result = clubPostService.updatePost(clubPostVO);

		//List<AttachVO> files = fileUtils.uploadFiles(clubPostVO.getFiles());
		//attachService.updateAttach("cp", clubPostVO.getClubPostNo(), files);
	
		return result;
	}

	// 자유게시판 댓글 조회
	@GetMapping("repList")
	@ResponseBody
	public List<ClubRepVO> repListAjax(ClubRepVO clubRepVO) {
		//업데이트
		clubPostService.updatePostHit(clubRepVO.getClubPostNo());
		return clubRepService.selectAllRep(clubRepVO);
	}
 
	// 자유게시판 댓글 등록
	@PostMapping("repInsert")
	@ResponseBody
	public int insertRepAjax(ClubRepVO clubRepVO, Model model) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		clubRepVO.setMemId(id);

		int result = clubRepService.insertRep(clubRepVO);

		return result;
	}

	// 자유게시판 댓글 삭제
	@PostMapping("repDelete")
	@ResponseBody
	public boolean deleteRepAjax(@RequestParam("paramRepNo") Integer clubRepNo) {
		boolean result = clubRepService.deleteRep(clubRepNo);
		return result;
	}
	
	
	
	
	// --------------------------------------- 멤버
	

	// 상세페이지 > 탭 > 멤버
	@GetMapping("clubMember")
	public String memberTapPage(ClubVO clubVO ,Model model) {
		model.addAttribute("club", clubService.selectClub(clubVO));
		return "club/clubMember";	
	}
	
	// 상세페이지 > 탭 > 진짜멤버 리스트
		@GetMapping("clubMemList")
		@ResponseBody
		public List<ClubVO> memListAjax(ClubVO clubVO, Model model) {
			clubVO.setClubApprove("m2");
			model.addAttribute("realMem", clubService.selectclubMem(clubVO));
			return clubService.selectclubMem(clubVO);
		}
		
	// 상세페이지 > 탭 > 멤버 삭제
	@PostMapping("clubMemDelete")
	@ResponseBody
	public boolean memberDeleteAjax(@RequestParam("paramMemNo") Integer clubMemNo) {
		boolean result = clubService.clubMemDelete(clubMemNo);
		return result;
	}	
	
	
	// --------------------------------------- 가입 수락 페이지(클럽장만)	

	// 상세페이지 > 탭 > 클럽가입 확인 페이지
	@GetMapping("clubApply")
	public String applyTapPage(Model model, ClubVO clubVO) {
		model.addAttribute("club", clubService.selectClub(clubVO));
		return "club/clubApply";
	}
	
	// 상세페이지 > 탭 > 클럽가입 확인(신청) 리스트
	@GetMapping("clubInquiryList")
	@ResponseBody
	public List<ClubVO> InquiryListAjax(ClubVO clubVO, Model model) {
		clubVO.setClubApprove("m3");
		model.addAttribute("mem", clubService.selectclubMem(clubVO));
		return clubService.selectclubMem(clubVO);
	}
	
	// 상세페이지 > 탭 > 클럽가입신청 (승인)모달
		@PostMapping("memFormAjax")
		@ResponseBody
		public Map<String, Object> clubMemFormAjax(@RequestBody ClubVO clubVO) {
			String id = SecurityContextHolder.getContext().getAuthentication().getName();
			clubVO.setMemId(id);
			Map<String, Object> result = clubService.clubMemAdd(clubVO);
			System.out.println("@@@@@@@@@@@@@@@@승인했는데"+ result);
			return result;
		}

}
