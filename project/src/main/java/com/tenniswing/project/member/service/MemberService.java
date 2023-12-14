package com.tenniswing.project.member.service;

import java.util.List;
import java.util.Map;

import com.tenniswing.project.attach.service.AttachVO;

public interface MemberService {
	// 전체조회
	public List<MemberVO> getMemberAll();

	// 로그인
	public MemberVO loginMember(MemberVO memberVO);
	
	// 마이페이지 회원정보 조회
	public MemberVO memberInfo(String memId);
	// 수정폼 회원정보 조회
	public MemberVO memberUpdateInfo(String memId);
	//프로필 사진 업로드
	public int profileUpload(MemberVO memberVO);
	
	// 아이디 중복 체크
	public boolean idCheck(String memId);
	
	// 아이디 찾기
	public String searchId(MemberVO memberVO);
	// 패스워드 수정
	public int searchPwUpdate(MemberVO memberVO);

	// 회원가입
	public int insertMember(MemberVO memberVO, List<AttachVO> files);

	// 수정
	public Map<String, Object> updateMemberInfo(MemberVO memberVO);

	// 삭제
	public boolean deleteMember(MemberVO memberVO);
	
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
	
	//호스트멤버
	public List<MemberVO> searchHost();
}
