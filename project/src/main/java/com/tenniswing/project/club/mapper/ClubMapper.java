package com.tenniswing.project.club.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tenniswing.project.attach.service.AttachVO;
import com.tenniswing.project.club.service.ClubVO;

public interface ClubMapper {
	//전체조회
	public List<ClubVO> selectAllClub(ClubVO clubVO);
	
	//단건조회
	public ClubVO selectClub(ClubVO clubVO);
	//public Map<String, Object> selectClub1(ClubVO clubVO);
	public List<ClubVO> selectclubMem(ClubVO clubVO);
	
	//등록
	public int insertClub(ClubVO clubVO);
	public int insertClubHost(ClubVO clubVO);
	
	//수정
	public int updateClub(ClubVO clubVO);
	public int clubMemAdd(ClubVO clubVO);
	
	//삭제
	public int deleteClub(HashMap<String, Long> map);
	public int clubMemDelete(int ClubVO);

	//재은
	public ClubVO selectMatchClub(ClubVO clubVO);
	
	
	//클럽 멤버 체크
	public List<ClubVO> selectCheckMem(ClubVO clubVO);
	
	
	//첨부파일
	public List<AttachVO> attachListALllClub();
	
	//회원 가입 신청(등록)
	public int insertClubMem(ClubVO clubVO);
		
	//회원 리스트(전체조회)
	//public List<ClubVO> selectAllClubMem(ClubVO clubVO);
	

	//페이징
	public int selectCount(ClubVO clubVO);

	//마이클럽 리스트
	public List<Map<String, Object>> selectAllMyClub(String memId);
	
	//어드민 클럽 리스트
	public List<ClubVO> selectAdminAllClub();

	

}
