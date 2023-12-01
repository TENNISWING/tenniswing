package com.tenniswing.project.community.mapper;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.tenniswing.project.attach.service.AttachVO;
import com.tenniswing.project.community.service.SnsVO;

public interface SnsMapper {
	// 전체조회(다건 조회)
	public List<SnsVO> selectAllSnsInfo();
	
	// 단건 조회
	public SnsVO selectSnsInfo(SnsVO snsVO);
	
	// 등록
	public int insertSns(SnsVO snsVO);
	// 그룹등록
	public int insertSnsGrp(SnsVO snsVO);
	// 좋아요 등록
	public int insertLike(SnsVO snsVO);
	// 스크랩 등록
	public int insertSnsScrap(SnsVO snsVO);
	// 댓글 등록
	public int insertSnsRep(SnsVO snsVO);
	// 대댓글 등록
	public int insertSnsRrep(SnsVO snsVO);
	
	// 수정
	public int updateSns(SnsVO snsVO);
	
	// 삭제
	public int deleteSns(@Param("snsWrtNo")int snsWrtNo );
	
	// 좋아요 삭제
	public int deleteLike(int likeNo);
	
	// 회원별 게시글당 좋아요 번호 조회
	public SnsVO selectLikeNo(SnsVO snsVO);
	
	//sns 첨부파일 
	public List<AttachVO> attachListAllSns();
}
