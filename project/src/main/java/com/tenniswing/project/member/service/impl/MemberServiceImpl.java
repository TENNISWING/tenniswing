package com.tenniswing.project.member.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tenniswing.project.attach.service.AttachService;
import com.tenniswing.project.attach.service.AttachVO;
import com.tenniswing.project.common.FileUtils;
import com.tenniswing.project.member.mapper.MemberMapper;
import com.tenniswing.project.member.service.MemberService;
import com.tenniswing.project.member.service.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberMapper memberMapper;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	FileUtils fileUtils;
	
	@Autowired
	AttachService attachService;

	@Override
	public List<MemberVO> getMemberAll() {
		return memberMapper.selectAllMemberInfo();
	}

	@Override
	public MemberVO loginMember(MemberVO memberVO) {
		return memberMapper.memberLogin(memberVO);
	}

	@Override
	public MemberVO memberInfo(String memId) {		
		 MemberVO memberVO = new MemberVO();
		 memberVO.setMemId(memId);
		 
		 memberVO = memberMapper.memberLogin(memberVO);
		 
		 if(memberVO.getMemDiv().equals("ROLE_MEMBER") || memberVO.getMemDiv().equals("ROLE_HOST")) {
			 memberVO = memberMapper.memberInfo(memId);
		 }else {
			 return memberVO;
		}		 		

		if (memberVO.getAttachPath() == null) {
			
			if(memberVO.getGen() == null) {
				memberVO.setAttachPath("/assets/compiled/jpg/architecture1.jpg");	
				return memberVO;
			}
			
			if (memberVO.getGen().equals("남성")) {
				memberVO.setAttachPath("/assets/compiled/jpg/2.jpg");		
			}else {
				memberVO.setAttachPath("/assets/compiled/jpg/3.jpg");		
			}
		} 
	
		return memberVO;
	}
	
	@Override
	public MemberVO memberUpdateInfo(String memId) {
		
		MemberVO memberVO = memberMapper.memberUpdateInfo(memId);		

		if (memberVO.getAttachPath() == null) {
			if (memberVO.getGen().equals("c1")) {
				memberVO.setAttachPath("/assets/compiled/jpg/2.jpg");		
			} else {
				memberVO.setAttachPath("/assets/compiled/jpg/3.jpg");		
			}
		} 
	
		return memberVO;
	}

	@Override
	public int insertMember(MemberVO memberVO) {

		String role = memberVO.getMemDiv();

		if (role.equals("h1")) {
			memberVO.setMemDiv("ROLE_MEMBER");
		} else {
			memberVO.setNick("호스트회원");
			memberVO.setMemDiv("ROLE_HOST");
		}

		memberVO.setPwd(passwordEncoder.encode(memberVO.getPwd()));

		int result = memberMapper.insertMember(memberVO);		

		if (result > 0) {
			
			if(role.equals("ROLE_HOST")) {
				return result;
			}
			
			List<AttachVO> files = fileUtils.uploadFiles(memberVO.getFiles());			
			//테이블 구분, 게시글 번호, 파일목록
			attachService.saveAttach("m", memberVO.getMemNo(), files);
			
			return result;
			
		} else {
			return -1;
		}
	}

	@Override
	public Map<String, Object> updateMemberInfo(MemberVO memberVO) {
		Map<String, Object> map = new HashMap<>();
		
		//pw check
		MemberVO pwvo = memberMapper.checkPw(memberVO.getMemId());
		
		//패스워드 맞는지 확인
		if(!passwordEncoder.matches(memberVO.getPwd(), pwvo.getPwd())) {
			map.put("message", "비밀번호가 일치하지않아 수정에 실패하였습니다.");
			return map;
		}		
		
		if(memberVO.getPwdUpdate() != null && memberVO.getPwdUpdate().length() != 0) {			
			memberVO.setPwdUpdate(passwordEncoder.encode(memberVO.getPwdUpdate()));
		}
		
		int result = 0;
		
		result += memberMapper.updateMemberInfo(memberVO);
		result += memberMapper.updatePrInfo(memberVO);

		if (result > 0) {			
			map.put("message", "프로필을 수정 하였습니다.");
		}
		

		if(memberVO.getFiles().size() != 0) {	
			
			memberVO = memberMapper.memberInfo(memberVO.getMemId());	
			if(memberVO.getAttachPath() != null && memberVO.getAttachPath().length() != 0) {
				System.out.println("업데이트");
				List<AttachVO> files = fileUtils.uploadFiles(memberVO.getFiles());
				attachService.updateAttach("m", memberVO.getMemNo(), files);
			}else {				
				List<AttachVO> files = fileUtils.uploadFiles(memberVO.getFiles());			
				//테이블 구분, 게시글 번호, 파일목록
				attachService.saveAttach("m", memberVO.getMemNo(), files);
			}
			
		}	

		return map;
	}
	
	@Override
	public int profileUpload(MemberVO memberVO) {
		
		MemberVO oldMemberVO = memberMapper.memberUpdateInfo(memberVO.getMemId());
		
	
		
		if(memberVO.getFiles().size() == 0) {
			return 0;
		}
		
		int n = 0;
		
		if(oldMemberVO.getAttachPath() != null && oldMemberVO.getAttachPath().length() != 0) {
			System.out.println("업데이트");
			List<AttachVO> files = fileUtils.uploadFiles(memberVO.getFiles());
			n = attachService.updateAttach("m", oldMemberVO.getMemNo(), files);
		}else {
			System.out.println("인서트=? " + oldMemberVO.getMemNo());
			List<AttachVO> files = fileUtils.uploadFiles(memberVO.getFiles());			
			//테이블 구분, 게시글 번호, 파일목록
			n = attachService.saveAttach("m", oldMemberVO.getMemNo(), files);
		}
		return n;
	}

	@Override
	public boolean deleteMember(MemberVO memberVO) {
		int result = memberMapper.deleteMember(memberVO);

		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean idCheck(String memId) {
		int result = memberMapper.memberIdCheck(memId);

		if (result == 0) {
			return true;
		}

		return false;
	}

	@Override
	public Map<String, Object> myMatchHistory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> myClubList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> myCourtCalc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> myWriteList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> myShopHistory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String searchId(MemberVO memberVO) {
		memberVO = memberMapper.searchId(memberVO);
		String message = "";
		
		if(memberVO == null) {
			message = "가입된 회원이 아닙니다.";
			return message;
		}
		
		message = memberVO.getMemId();
		return message;
	}

	@Override
	public int searchPwUpdate(MemberVO memberVO) {
		memberVO.setPwdUpdate(passwordEncoder.encode(memberVO.getPwd()));		
		int n = memberMapper.searchPwUpdate(memberVO);		
		if(n > 0) {
			return n;
		}		
		return -1;
	}

	@Override
	public List<MemberVO> searchHost() {
		return memberMapper.searchHost();
	}



	

}
