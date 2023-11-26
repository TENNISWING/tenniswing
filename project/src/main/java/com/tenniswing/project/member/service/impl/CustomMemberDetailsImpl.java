package com.tenniswing.project.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tenniswing.project.member.service.MemberDetails;
import com.tenniswing.project.member.service.MemberService;
import com.tenniswing.project.member.service.MemberVO;

@Service
public class CustomMemberDetailsImpl implements UserDetailsService{

	@Autowired
	MemberService memberService;
	
	@Override
	public UserDetails loadUserByUsername(String memId) throws UsernameNotFoundException {
		
		MemberVO memberVO = new MemberVO();
		memberVO.setMemId(memId);		
		memberVO = memberService.loginMember(memberVO);
		
		if (memberVO != null) {
			return new MemberDetails(memberVO);
		}
		
		return null;
	}
	
}
