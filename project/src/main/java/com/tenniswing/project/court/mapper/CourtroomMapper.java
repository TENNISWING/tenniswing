package com.tenniswing.project.court.mapper;

import java.util.List;

import com.tenniswing.project.court.service.CourtroomVO;

public interface CourtroomMapper {
	// 전체조회
	public List<CourtroomVO> selectAllCourtroom();
	
	// 단건조회
	public CourtroomVO selectCourtroom(CourtroomVO courtroomVO);
	
	// 필터조회(지역, 날짜, 시간대)
	
	// 등록
	public int insertCourtroom(CourtroomVO courtroomVO);
	
	// 수정
	public int updateCourtroom(CourtroomVO courtroomVO);
	
	// 삭제
	public int deleteCourtroom(int courtroomNo);
}
