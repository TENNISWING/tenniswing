package com.tenniswing.project.member.service;

import java.util.List;
import java.util.Map;

public interface MemberService {
	// 전체조회
	public List<MemberVO> getMemberAll();

	// 단건조회
	public MemberVO getMemberInfo(MemberVO empVO);

	// 등록
	public int insertMember(MemberVO empVO);

	// 수정
	public Map<String, Object> updateMemberInfo(MemberVO empVO);

	// 삭제
	public boolean deleteMember(int empId);
}
