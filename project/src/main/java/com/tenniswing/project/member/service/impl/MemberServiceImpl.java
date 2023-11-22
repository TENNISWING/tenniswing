package com.tenniswing.project.member.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenniswing.project.member.mapper.MemberMapper;
import com.tenniswing.project.member.service.MemberService;
import com.tenniswing.project.member.service.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberMapper memberMapper;	

	@Override
	public List<MemberVO> getMemberAll() {
		return memberMapper.selectAllMemberInfo();
	}

	@Override
	public MemberVO getMemberInfo(MemberVO memberVO) {
		return memberMapper.selectMemberInfo(memberVO);
	}

	@Override
	public int insertMember(MemberVO memberVO) {
		int result = memberMapper.insertMember(memberVO);
		
		if(result == 1) {
			return memberVO.getEmployeeId();
		}else {
			return -1;
		}
	}

	@Override
	public Map<String, Object> updateMemberInfo(MemberVO memberVO) {
		Map<String, Object> map = new HashMap<>();
		
		boolean isSuccessed = false;
		int result = memberMapper.updateMemberInfo(memberVO);
		
		if(result == 1) {
			isSuccessed = true;			
		}
		
		map.put("result", isSuccessed);
		map.put("target", memberVO);
		
		return map;
	}

	@Override
	public boolean deleteMember(int memberNo) {
		int result = memberMapper.deleteMember(memberNo);
		
		if(result == 1) {
			return true;
		}else {			
			return false;
		}
	}

}
