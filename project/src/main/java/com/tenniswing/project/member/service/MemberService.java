package com.tenniswing.project.member.service;

import java.util.List;
import java.util.Map;

public interface MemberService {
	// 전체조회
	public List<MemberVO> getMemberAll();

	// 로그인
	public MemberVO loginMember(MemberVO memberVO);
	
	// 마이페이지 회원정보 조회
	public MemberVO memberInfo(String memId);
	
	// 아이디 중복 체크
	public boolean idCheck(String memId);

	// 회원가입
	public int insertMember(MemberVO memberVO);

	// 수정
	public Map<String, Object> updateMemberInfo(MemberVO memberVO);

	// 삭제
	public boolean deleteMember(int memId);
	
	//매치이력
	public Map<String, Object> myMatchHistory();
	
	//나의 클럽 목록
	public Map<String, Object> myClubList();
	
	//코트 예약 목록
	public Map<String, Object> myCourtCalc();
	
	//나의 작성글
	public Map<String, Object> myWriteList();
	
	//쇼핑
	public Map<String, Object> myShopHistory();
}
