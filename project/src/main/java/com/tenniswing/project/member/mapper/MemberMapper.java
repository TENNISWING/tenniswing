package com.tenniswing.project.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tenniswing.project.member.service.MemberVO;

public interface MemberMapper {
	// 전체 조회 (다건조회)
	public List<MemberVO> selectAllMemberInfo();

	// 로그인
	public MemberVO memberLogin(MemberVO memberVO);
	
	// 아이디 중복 체크
	public MemberVO memberIdCheckk(String memId);

	// 등록
	public int insertMember(MemberVO memberVO);

	// 수정
	public int updateMemberInfo(MemberVO memberVO);

	// 수정 : Dynamic SQL
	public int updateMemberInfoDynamic(MemberVO memberVO);

	// 삭제 //@Param 여러건 보낼 경우 사용
	public int deleteMember(@Param("empid") int employeeId);
}
