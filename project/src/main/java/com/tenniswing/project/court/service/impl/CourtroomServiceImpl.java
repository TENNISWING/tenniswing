package com.tenniswing.project.court.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenniswing.project.attach.mapper.AttachMapper;
import com.tenniswing.project.attach.service.AttachVO;
import com.tenniswing.project.court.mapper.CourtroomMapper;
import com.tenniswing.project.court.service.CourtroomService;
import com.tenniswing.project.court.service.CrtroomVO;
import com.tenniswing.project.member.service.MemberVO;

@Service
public class CourtroomServiceImpl implements CourtroomService {

	@Autowired CourtroomMapper courtroomMapper;
	@Autowired AttachMapper attachMapper;
	
	// 호스트
	@Override
	@Transactional
	public int insertCourtroom(CrtroomVO crtroomVO, List<AttachVO> files) {
		int result = courtroomMapper.insertCourtroom(crtroomVO);
		
		int crtroomNo = crtroomVO.getCrtroomNo();
		
		int index = 1;
		for (AttachVO file : files) {
			file.setAttachTableDiv("co");
			file.setAttachTablePk(crtroomNo);
			file.setAttachTurn(index);
			index++;
		}		
		attachMapper.saveAttachTurn(files);
		
		if(result == 1) {
			return crtroomNo;		
		}
		else {
			return -1;
		}
	}

	@Override
	public Map<String, Object> updateCourtroom(CrtroomVO crtroomVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result = courtroomMapper.updateCourtroom(crtroomVO);
		if(result == 1) {
			isSuccessed = true;
		}
		map.put("result", isSuccessed);
		map.put("info", crtroomVO);
		
		return map;
	}

	@Override
	public List<CrtroomVO> selectAllCourtroom(String hostId) {
		return courtroomMapper.selectAllCourtroom(hostId);
	}

	@Override
	public CrtroomVO selectCourtroom(CrtroomVO crtroomVO) {
		return courtroomMapper.selectCourtroom(crtroomVO);
	}

	@Override
	public boolean deleteCourtroom(int crtroomNo) {
		int result = courtroomMapper.deleteCourtroom(crtroomNo);
		
		if(result == 1) {
			return true;
		}else {
			return false;
		}
	}

	//사용자
	@Override
	public List<CrtroomVO> selectAllCourtroomMain(CrtroomVO crtroomVO) {
		return courtroomMapper.selectAllCourtroomMain(crtroomVO);
	}

	@Override
	public List<CrtroomVO> recentRegiCourt() {
		return courtroomMapper.recentRegiCourt();
	}

	@Override
	public List<CrtroomVO> nearCrtroom(CrtroomVO crtroomVO) {
		return courtroomMapper.nearCrtroom(crtroomVO);
	}

	@Override
	public MemberVO selectCrtDetailHost(String hostId) {
		return courtroomMapper.selectCrtDetailHost(hostId);
	}

	@Override
	public List<CrtroomVO> courtSearch(String str) {		
		return courtroomMapper.courtSearch(str);
	}

	@Override
	public List<CrtroomVO> selectCourtReview(int crtroomNo) {
		return courtroomMapper.selectCourtReview(crtroomNo);
	}

	@Override
	@Transactional
	public int insertCourtReview(CrtroomVO crtroomVO, List<AttachVO> files) {
		int result = courtroomMapper.insertCourtReview(crtroomVO);
		
		//int crtroomNo = crtroomVO.getCrtroomNo();
		int reviewNo = crtroomVO.getReviewNo();
		
		int index = 1;
		if(files != null && files.size() > 0) {
			for (AttachVO file : files) {
				file.setAttachTableDiv("cr");
				file.setAttachTablePk(reviewNo);
				file.setAttachTurn(index);
				index++;
			}
			attachMapper.saveAttachTurn(files);
		}
		
		if(result == 1) {
			return reviewNo;
		}
		else {
			return -1;
		}
	}

	@Override
	public Integer confirmInsertReview(CrtroomVO crtroomVO) {
		return courtroomMapper.confirmInsertReview(crtroomVO);
	}

	@Override
	public CrtroomVO selectReview(int reviewNo) {
		return courtroomMapper.selectReview(reviewNo);
	}

	@Override
	public boolean deleteReview(int reviewNo) {
		int result = courtroomMapper.deleteReview(reviewNo);
		
		if(result == 1) {
			int resultImg = courtroomMapper.deleteReviewImg(reviewNo);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public CrtroomVO crtroomStar(int crtroomNo) {
		return courtroomMapper.crtroomStar(crtroomNo);
	}

	@Override
	public int selectCount(CrtroomVO crtroomVO) {
		return courtroomMapper.selectCount(crtroomVO);
	}

	@Override
	public List<CrtroomVO> courtAdminAll() {
		return courtroomMapper.courtAdminAll();
	}
}
