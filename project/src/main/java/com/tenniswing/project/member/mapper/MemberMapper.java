package com.tenniswing.project.member.mapper;

import java.util.List;
import java.util.Map;
import com.tenniswing.project.member.service.MemberVO;

public interface MemberMapper {
	// 전체 조회 (다건조회)
	public List<MemberVO> selectAllMemberInfo();

	// 로그인
	public MemberVO memberLogin(MemberVO memberVO);
	
	// 마이페이지 회원정보 조회
	public MemberVO memberInfo(String memId);
	// 수정폼 회원정보 조회
	public MemberVO memberUpdateInfo(String memId);
	
	// 아이디 중복 체크
	public int memberIdCheck(String memId);
	// 패스워드체크
	public MemberVO checkPw(String memId);
	// 아이디 찾기
	public MemberVO searchId(MemberVO memberVO);

	// 등록
	public int insertMember(MemberVO memberVO);

	// 수정
	public int updateMemberInfo(MemberVO memberVO);
	public int updatePrInfo(MemberVO memberVO);
	// 패스워드 수정
	public int searchPwUpdate(MemberVO memberVO);

	// 수정 : Dynamic SQL
	public int updateMemberInfoDynamic(MemberVO memberVO);

	// 삭제 
	public int deleteMember(MemberVO memberVO);
	
	//매치이력
	public Map<String, Object> myMatch(String memId);
	
	//나의클럽
	public Map<String, Object> myClub(String memId);
	
	//나의코트예약
	public Map<String, Object> myCourt(String memId);
	
	//내 작성글
	public Map<String, Object> myWrite(String memId);
	
	//쇼핑
	public Map<String, Object> myShopping(String memId);
}
