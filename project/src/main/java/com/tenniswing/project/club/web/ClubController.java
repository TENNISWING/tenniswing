package com.tenniswing.project.club.web;

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
import com.tenniswing.project.club.service.ClubPostService;
import com.tenniswing.project.club.service.ClubPostVO;
import com.tenniswing.project.club.service.ClubRepService;
import com.tenniswing.project.club.service.ClubRepVO;
import com.tenniswing.project.club.service.ClubService;
import com.tenniswing.project.club.service.ClubVO;
import com.tenniswing.project.common.FileUtils;

@Controller
public class ClubController {
	
	@Autowired
	ClubService clubService;
	
	@Autowired
	ClubPostService clubPostService;
	
	@Autowired
	ClubRepService clubRepService;
	
	@Autowired
	FileUtils fileUtils;
	
	@Autowired
	AttachService attachService;

// --------------------------------------- 메인	
	//클럽페이지
	@GetMapping("club")  
	public String clubPage(Model model) { 
		//model.addAttribute("clubList", clubService.selectAllClub());
		return "club/club";
	}
	
	//클럽리스트
	 @GetMapping("clubList")
	 @ResponseBody 
	 public List<ClubVO> clubPageAjax(ClubVO clubVO) { 
		 return clubService.selectAllClub(clubVO);
		 }
	 
	//등록 페이지
	@GetMapping("clubform")  
	public String clubFormPage(Model model) { 	
	    //처음 입력 폼은 모두 비워져야해서 빈 객체(new new ClubVO()) 전달
		model.addAttribute("clubVO",new ClubVO());
		return "club/clubform";
	}
	
	//등록 프로세스
	@PostMapping("clubform") 
	public String insertClubProcess(ClubVO clubVO, RedirectAttributes rttr) {
		
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		clubVO.setMemId(id);
		
		clubService.insertClub(clubVO);
		
		//사진 등록
		List<AttachVO> files = fileUtils.uploadFiles(clubVO.getFiles());
		int n = attachService.saveAttach("cl",clubVO.getClubNo(), files); //구분코드, 번호, 파일목록
		
		if(n> 0) {
			rttr.addAttribute("clubNo", clubVO.getClubNo());
			return "redirect:club";
		}else {
			rttr.addAttribute("message", "파일등록에 실패하였습니다.");
			return "redirect:club";
		}
		
	}
	
	//회원 클럽 가입신청(등록) 모달	
	@PostMapping("memFormAjax")
	@ResponseBody
	public int clubMemFormAjax(ClubVO clubVO, Model model) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		clubVO.setMemId(id);
		int result = clubService.insertClubMem(clubVO);
		return result;
	}
	
	
	
	
// ---------------------------------------상세페이지
	
	//단건(상세)조회
	@GetMapping("clubdetail") 
	public String clubDetailPage(Model model, ClubVO clubVO) { 
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		
		clubVO.setMemId(id);
		model.addAttribute("club",clubService.selectClub(clubVO));
		System.out.println(clubService.selectClub(clubVO));
		return "club/clubdetail";
	}
	
	//상세페이지 > 탭 > 클럽정보	
	@GetMapping("clubInfo")  
	public String infoTapPage(Model model, ClubVO clubVO) { 
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		clubVO.setMemId(id);
		model.addAttribute("club",clubService.selectClub(clubVO));
		
		//System.out.println("==================="+ clubVO);
		return "club/clubInfo";
	}
	
	// 클럽 삭제
	@PostMapping("clubdelete") 
	@ResponseBody
	public boolean deleteClubAjax(@RequestParam("paramclubNo") Integer clubNo) {
		boolean result = clubService.deleteClub(clubNo);
		return result;
	}
	
	//클럽 수정
	@PostMapping("clubUpdate")
	@ResponseBody
	public Map<String, Object> clubUpdateFormAjax(ClubVO clubVO) {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		clubVO.setMemId(id);
		//System.out.println("-----수정아작스컨트롤러 "+clubVO);
		Map<String, Object> result = clubService.updateClub(clubVO);
		return result;
	}
	
	//상세페이지 > 탭 > 매치모집
	@GetMapping("clubMatchJoin")  
	public String joinTapPage(Model model) { 		
		return "club/clubMatchJoin";
	}
	
	//상세페이지 > 탭 > 매치일정
	@GetMapping("clubMatchDate")  
	public String DateTapPage(Model model) { 		
		return "club/clubMatchDate";
	}
	
	//상세페이지 > 탭 > 매치결과
	@GetMapping("clubMatchEnd")  
	public String EndTapPage(Model model) { 		
		return "club/clubMatchEnd";
	}
	
	//상세페이지 > 탭 > 자유게시판
	@GetMapping("clubPost")  
	public String boardTapPage(Model model, ClubVO clubVO) { 
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		clubVO.setMemId(id);
		model.addAttribute("club",clubService.selectClub(clubVO));
		
		return "club/clubPost";
	}
		//자유게시판 리스트
		@GetMapping("postList")
		@ResponseBody 
		public List<ClubPostVO> postListAjax(ClubPostVO clubPostVO) { 
			return clubPostService.selectAllPost(clubPostVO);
		 }
	
		//자유게시판 등록
		@PostMapping("postInsert")
		@ResponseBody
		public String postInsertAjax(ClubPostVO clubPostVO, RedirectAttributes rttr) {
			String id = SecurityContextHolder.getContext().getAuthentication().getName();
			clubPostVO.setMemId(id);
			//System.out.println("++++++ clubPostVO 뭔가요 "+clubPostVO);
			clubPostService.insertPost(clubPostVO);
			
			//사진 등록
			List<AttachVO> files = fileUtils.uploadFiles(clubPostVO.getFiles());
			int n = attachService.saveAttach("cp",clubPostVO.getClubPostNo(), files); //구분코드, 번호, 파일목록
			
			if(n> 0) {
				rttr.addAttribute("clubNo", clubPostVO.getClubNo());
				return "redirect:clubPost";
			}else {
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
			clubPostVO.setMemId(id);
			//System.out.println("-----plz "+clubPostVO);
			Map<String, Object> result = clubPostService.updatePost(clubPostVO);
			return result;
		}
		
			//자유게시판 댓글 조회
			@GetMapping("repList")
			@ResponseBody
			public List<ClubRepVO> repListAjax(ClubRepVO clubRepVO){
				System.out.println("000000000000"+clubRepVO);
				return clubRepService.selectAllRep(clubRepVO);
			}
			
			
			//자유게시판 댓글 등록
			@PostMapping("repInsert")
			@ResponseBody
			public int insertRepAjax(ClubRepVO clubRepVO, Model model) {
				String id = SecurityContextHolder.getContext().getAuthentication().getName();
				clubRepVO.setMemId(id);
				
				int result = clubRepService.insertRep(clubRepVO);
				
				return result;
			}
		
			
			//자유게시판 댓글 삭제
			@PostMapping("repDelete")
			@ResponseBody
			public boolean deleteRepAjax(@RequestParam("paramRepNo")Integer clubRepNo) {
				boolean result = clubRepService.deleteRep(clubRepNo);
				return result;
			}
		
	
	//상세페이지 > 탭 > 멤버
	@GetMapping("clubMember")  
	public String memberTapPage(Model model) { 		
		return "club/clubMember";
	}
	
	//상세페이지 > 탭 > 클럽가입신청
	@GetMapping("clubApply")  
	public String applyTapPage(Model model) { 		
		return "club/clubApply";
	}
	
	
	
	
}
