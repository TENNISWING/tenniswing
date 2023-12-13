package com.tenniswing.project.community.service;

import java.util.List;
import java.util.Map;

import com.tenniswing.project.attach.service.AttachVO;
import com.tenniswing.project.club.service.ClubVO;

public interface SnsService {
	// 전체조회
	public List<SnsVO> selectAllSnsInfo(SnsVO snsVO);
	// 회원당 그룹 조회
	public List<SnsVO> selectGroup(SnsVO snsVO);
	// 내 그룹 리스트(sns, snsgroup, like 다 가지고 있음)
	public List<SnsVO> selectMyGroup(SnsVO snsVO);
	// 그룹 값이 NULL인 SNS 리스트 조회
	public List<SnsVO> selectGrpNull(SnsVO snsVO);
	// sns 마이페이지
	public List<SnsVO> selectSnsMyPage(SnsVO snsVO);

	// 단건조회
	public SnsVO selectSnsInfo(SnsVO snsVO);
	// 좋아요번호 단건조회 (회원별 게시글 당)
	public SnsVO selectLikeNo(SnsVO snsVO);
	
	// 등록
	public int insertSns(SnsVO snsVO,  List<AttachVO> files );
	// 그룹등록
	public int insertSnsGrp(SnsVO snsVO);
	// 좋아요 등록
	public int insertLike(SnsVO snsVO);
	// 스크랩 등록
	public int insertScrap(SnsVO snsVO);
		
		
	// 수정
	public Map<String, Object> updateSns(SnsVO snsVO);
	public Map<String, Object> updateGrp(SnsVO snsVO);
	
	// sns, 댓글, 좋아요 삭제 프로시저
	public boolean deleteSns(int snsWrtNo);
	
	// sns, 그룹 삭제
	public boolean deleteGrp(int snsGrpNo);
	
	// 좋아요 삭제
	public boolean deleteLike(int likeNo);
	
	// 스크랩 삭제
	public boolean deleteScrap(int scrapNo);
	
	//sns 첨부파일 조회
	public List<AttachVO> attachListAllSns();
	
	//페이징 전체 갯수
	public int selectCount(SnsVO snsVO);

	//어드민페이지
	public List<SnsVO> snsAllList();

}
