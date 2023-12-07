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
import com.tenniswing.project.community.mapper.SnsRepMapper;
import com.tenniswing.project.community.service.SnsService;
import com.tenniswing.project.community.service.SnsVO;

@Service
public class SnsServiceImpl implements SnsService {

	@Autowired
	SnsRepMapper snsRepMapper;

	@Autowired
	SnsMapper snsMapper;

	// 전체조회(다건 조회)
	@Override
	public List<SnsVO> selectAllSnsInfo(SnsVO snsVO) {
		return snsMapper.selectAllSnsInfo(snsVO);
	}

	// 회원당 그룹 조회
	@Override
	public List<SnsVO> selectGroup(SnsVO snsVO) {
		return snsMapper.selectGroup(snsVO);
	}

	// 내 그룹 리스트(sns, snsgroup, like 다 가지고 있음)
	@Override
	public List<SnsVO> selectMyGroup(SnsVO snsVO) {

		return snsMapper.selectMyGroup(snsVO); // 그룹조회한 mapper where memId
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

		return snsMapper.insertSnsGrp(snsVO);
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
	public Map<String, Object> updateSns(SnsVO snsVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;

		ObjectMapper obj = new ObjectMapper();
		String tag = "";
		try {
			Map<String, String>[] list = obj.readValue(snsVO.getSnsTag(), Map[].class);
			if (list != null) {
				for (Map i : list) {
					String str = (String) i.get("value");
					char charAt = str.charAt(0);

					if (charAt != '#') {
						tag += "#" + i.get("value") + ",";
					} else {
						tag += i.get("value") + ",";
					}
					/* tag += "#" + i.get("value") + ","; */
					/*
					 * if(((List<SnsVO>) i.get("value")).contains("#")) { tag += i.get("value")+",";
					 * }else { tag += "#" + i.get("value") + ","; }
					 */

				}

				snsVO.setSnsTag(tag);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		int result = snsMapper.updateSns(snsVO);
		if (result == 1) {
			isSuccessed = true;
			// 그룹 업데이트 snsMapper.updateGrp(snsVO);

		}

		map.put("result", isSuccessed);
		map.put("info", snsVO);

		return map;

	}

	// sns, 댓글, 좋아요 삭제 프로시저
	@Override
	public boolean deleteSns(int snsWrtNo) {

		HashMap<String, Long> map = new HashMap<String, Long>();
		map.put("snsWrtNo", (long) snsWrtNo);
		map.put("delResult", (long) 0);
		snsMapper.deleteSns(map);
		long result = (long) map.get("delResult");
		System.out.println("서비스임플" + result);
		if (result >= 1) {
			return true;
		} else {
			return false;
		}
	}
	
	// sns, 그룹 삭제 프로시저
	@Override
	public boolean deleteGrp(int snsGrpNo) {
		HashMap<String, Long> map = new HashMap<String, Long>();
		map.put("snsGrpNo", (long) snsGrpNo);
		map.put("delGrpResult", (long) 0);
		snsMapper.deleteGrp(map);
		long result = (long) map.get("delGrpResult");
		System.out.println("deleteGrp서비스임플" + result);
		if (result >= 1) {
			return true;
		} else {
			return false;
		}
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
	// 그룹 이름 업데이트
	@Override
	public Map<String, Object> updateGrp(SnsVO snsVO) {
		Map<String, Object> map = new HashMap<>();
		boolean inSuccessed = false;
		
		int result = snsMapper.updateGrp(snsVO);
		if(result == 1) {
			inSuccessed = true;
		}
		map.put("result", inSuccessed);
		map.put("info", snsVO);
		return map;
	}

	

	/*
	 * @Override public List<SnsVO> selectAllSnsInfo() { // TODO Auto-generated
	 * method stub return null; }
	 */

}
