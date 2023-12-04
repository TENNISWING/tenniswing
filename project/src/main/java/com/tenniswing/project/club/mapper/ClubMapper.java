package com.tenniswing.project.club.mapper;

import java.util.List;

import com.tenniswing.project.attach.service.AttachVO;
import com.tenniswing.project.club.service.ClubVO;

public interface ClubMapper {
	//전체조회
	public List<ClubVO> selectAllClub();
	
	//단건조회
	public ClubVO selectClub(ClubVO clubVO);
	
	//등록
	public int insertClub(ClubVO clubVO);
	
	//수정
	public int updateClub(ClubVO clubVO);
	
	//삭제
	public int deleteClub(int ClubNo);
	
	//첨부파일
	public List<AttachVO> attachListALllClub();
	
}
