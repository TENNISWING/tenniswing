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
		
		memberVO = memberMapper.memberInfo(memId);		

		if (memberVO.getAttachPath() == null) {
			if (memberVO.getGen().equals("남성")) {
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

		boolean isSuccessed = false;
		int result = memberMapper.updateMemberInfo(memberVO);

		if (result == 1) {
			isSuccessed = true;
		}

		map.put("result", isSuccessed);
		map.put("target", memberVO);

		return map;
	}

	@Override
	public boolean deleteMember(int memberNo) {
		int result = memberMapper.deleteMember(memberNo);

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

}
