package com.tenniswing.project.community.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenniswing.project.attach.service.AttachVO;
import com.tenniswing.project.community.mapper.SnsMapper;
import com.tenniswing.project.community.service.SnsService;
import com.tenniswing.project.community.service.SnsVO;

@Service
public class SnsServiceImpl implements SnsService {

	@Autowired
	SnsMapper snsMapper;

	// 전체조회(다건 조회)
	@Override
	public List<SnsVO> selectAllSnsInfo(SnsVO snsVO) {
		return snsMapper.selectAllSnsInfo(snsVO);
	}

	// 단건 조회
	@Override
	public SnsVO selectSnsInfo(SnsVO snsVO) {
		return snsMapper.selectSnsInfo(snsVO);
	}

	// 등록
	@Override
	public int insertSns(SnsVO snsVO) {
		ObjectMapper obj = new ObjectMapper();
		String tag = "";
		try {
			Map<String, String>[] list = obj.readValue(snsVO.getSnsTag(), Map[].class);
			if (list != null) {
				for (Map i : list) {
					tag += "#" + i.get("value") + ",";
				}
				snsVO.setSnsTag(tag);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		int result = snsMapper.insertSns(snsVO);

		if (result == 1) {
			return snsVO.getSnsWrtNo();
		} else {
			return -1;
		}
	}
	
	// 그룹등록
	@Override
	public int insertSnsGrp(SnsVO snsVO) {
		return 0;
	}

	// 좋아요 등록
	@Override
	public int insertLike(SnsVO snsVO) {
		return snsMapper.insertLike(snsVO);
	}

	// 스크랩 등록
	@Override
	public int insertSnsScrap(SnsVO snsVO) {
		return 0;
	}

	// SNS 수정
	@Override	
	public int updateSns(SnsVO snsVO) {
	
//		ObjectMapper obj = new ObjectMapper();
//		String tag = "";
//		try {
//			Map<String, String>[] list = obj.readValue(snsVO.getSnsTag(), Map[].class);
//			if (list != null) {
//				for (Map i : list) {
//					tag += "#" + i.get("value") + ",";
//				}
//				snsVO.setSnsTag(tag);
//
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		//int result = snsMapper.updateGrp(snsVO);
		int result = snsMapper.updateSns(snsVO);

		return result;
	
	}

	// 삭제
	@Override
	public int deleteSns(int snsWrtNo) {
		return 0;
	}

	// 좋아요 삭제
	@Override
	public boolean deleteLike(int likeNo) {
		int result = snsMapper.deleteLike(likeNo);
		if (result == 1) {
			return true;
		} else {
			return false;

		}
	}

	@Override
	public SnsVO selectLikeNo(SnsVO snsVO) {
		return snsMapper.selectLikeNo(snsVO);
	}

	@Override
	public List<AttachVO> attachListAllSns() {
		return snsMapper.attachListAllSns();
	}

	/*
	 * @Override public Map<String, Object> updateGrp(SnsVO snsVO) { // TODO
	 * Auto-generated method stub return null; }
	 */

	

	/*
	 * @Override public List<SnsVO> selectAllSnsInfo() { // TODO Auto-generated
	 * method stub return null; }
	 */

}
