package com.tenniswing.project.club.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenniswing.project.attach.service.AttachService;
import com.tenniswing.project.club.mapper.ClubMapper;
import com.tenniswing.project.club.service.ClubService;
import com.tenniswing.project.club.service.ClubVO;
import com.tenniswing.project.common.FileUtils;

@Service
public class ClubServiceImpl implements ClubService {

	@Autowired ClubMapper clubMapper;
	
	@Autowired FileUtils fileUtils;
	
	@Autowired AttachService attachService;

	//클럽 전체조회
	@Override
	public List<ClubVO> selectAllClub(ClubVO clubVO) {
		return clubMapper.selectAllClub(clubVO);
	}
	
	//클럽 페이징 전체 갯수 조회
	@Override
	public int selectCount(ClubVO clubVO) {
		return clubMapper.selectCount(clubVO);
	}

	//클럽 단건조회
	@Override
	public ClubVO selectClub(ClubVO clubVO) {
		
		return clubMapper.selectClub(clubVO);
	}
	
	/*
	 * @Override public Map<String, Object> selectClub1(ClubVO clubVO) {
	 * 
	 * return clubMapper.selectClub1(clubVO); }
	 */
	
	//클럽 멤버 조회
	@Override
	public List<ClubVO> selectclubMem(ClubVO clubVO) {
		return clubMapper.selectclubMem(clubVO);
	}
	
	//클럽 등록
	@Override
	public int insertClub(ClubVO clubVO) {
		
		/*
		 * List<AttachVO> files = fileUtils.uploadFiles(clubVO.getFiles());attachService.saveAttach("",
		 * clubVO.getClubNo(), files);
		 */
		int result = clubMapper.insertClub(clubVO); 
		if(result == 1) {
			return clubVO.getClubNo();
		}else {
			return -1;
		}
	}
	
	//클럽 삭제
	@Override
	public boolean deleteClub(int ClubNo) {
		int result = clubMapper.deleteClub(ClubNo);
		
		if(result ==1) {
			return true;
		}else {
			return false;
		}
	}
	
	//클럽 수정
	@Override
	public Map<String, Object> updateClub(ClubVO clubVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result = clubMapper.updateClub(clubVO);
		
		if(result == 1) {
			isSuccessed = true;
		}
		map.put("result", isSuccessed);
		map.put("info", clubVO);
		return map;
	}
	

	//클럽 가입 신청
	@Override
	public int insertClubMem(ClubVO clubVO) {
		return clubMapper.insertClubMem(clubVO);
	}

	//신청리스트
	//@Override
	//public List<ClubVO> selectAllClubMem(ClubVO clubVO) {
	//	return clubMapper.selectAllClubMem(clubVO);
	//}
	
	//클럽 멤버 승인	
	@Override
	public Map<String, Object> clubMemAdd(ClubVO clubVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result = clubMapper.clubMemAdd(clubVO);
		if(result == 1) {
			isSuccessed = true;
		}
		map.put("result",isSuccessed);
		map.put("info",clubVO);
		
		return map;
	}

	//클럽 멤버 삭제
	@Override
	public boolean clubMemDelete(int ClubNo) {
		int result = clubMapper.clubMemDelete(ClubNo);
		
		if(result ==1) {
			return true;
		}else {
			return false;
		}
	}


	//재은
	@Override
	public ClubVO selectMatchClub(ClubVO clubVO) {
		return clubMapper.selectMatchClub(clubVO);
	}


	

	

	

	
	 
	
}
