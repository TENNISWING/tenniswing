package com.tenniswing.project.member.service;

import java.util.List;
import java.util.Map;

public interface MemberService {
	// 전체조회
	public List<MemberVO> getMemberAll();

	// 로그인
	public MemberVO loginMember(MemberVO empVO);
	
	// 마이페이지 회원정보 조회
	public MemberVO memberInfo(MemberVO empVO);
	
	// 아이디 중복 체크
	public boolean idCheck(String memId);

	// 회원가입
	public int insertMember(MemberVO empVO);

	// 수정
	public Map<String, Object> updateMemberInfo(MemberVO empVO);

	// 삭제
	public boolean deleteMember(int empId);
}
