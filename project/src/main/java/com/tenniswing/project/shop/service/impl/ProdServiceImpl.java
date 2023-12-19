package com.tenniswing.project.shop.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenniswing.project.attach.mapper.AttachMapper;
import com.tenniswing.project.attach.service.AttachService;
import com.tenniswing.project.attach.service.AttachVO;
import com.tenniswing.project.common.FileUtils;
import com.tenniswing.project.shop.mapper.ProdMapper;
import com.tenniswing.project.shop.service.ProdService;
import com.tenniswing.project.shop.service.ProdVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProdServiceImpl implements ProdService{

	@Autowired
	ProdMapper prodMapper;
	
	@Autowired
	FileUtils fileUtils;
	
	@Autowired
	AttachService attachService;
	
	@Autowired
	AttachMapper attachMapper;
	
//	전체 조회
	@Override
	public List<ProdVO> selectAllProd(ProdVO prodVO) {
		return prodMapper.selectAllProd(prodVO);
	}
	
//	전체 조회
	@Override
	public List<ProdVO> selectAdminAllProd(ProdVO prodVO) {
		return prodMapper.selectAdminAllProd(prodVO);
	}
	
	@Override
	public int selectAdminAllCount(ProdVO prodVO) {
		return prodMapper.selectAdminAllCount(prodVO);
	}
	
//	페이징 전체 갯수 조회
	public int selectCount(ProdVO prodVO) {
		return prodMapper.selectCount(prodVO);
	}
	
//	최근 상품 스와이퍼
	@Override
	public List<ProdVO> selectSwiperProd() {
		return prodMapper.selectSwiperProd();
	}
	
//	관련 상품 스와이퍼
	@Override
	public List<ProdVO> relatedSwiperProd(ProdVO prodVO) {
		return prodMapper.relatedSwiperProd(prodVO);
	}

//	단건 조회
	@Override
	public ProdVO selectProd(ProdVO prodVO) {
		prodMapper.updateProdHit(prodVO.getProdNo());
		return prodMapper.selectProd(prodVO);
	}

//	등록
	@Override
	public int insertProd(ProdVO prodVO) {
		System.out.println("서비스"+prodVO);
		System.out.println("상품 상태"+prodVO.getProdSaleSts());
		if(prodVO.getProdSaleSts() == null) {
			prodVO.setProdSaleSts("p2");
		}
		
		List<AttachVO> files = fileUtils.uploadFiles(prodVO.getFiles());
		
		int result = prodMapper.insertProd(prodVO);
		
		int idx = 1;
		for (AttachVO file : files) {
			file.setAttachTableDiv("p");
			file.setAttachTablePk(prodVO.getProdNo());
			file.setAttachTurn(idx);
			idx++;
		}
		attachMapper.saveAttachTurn(files);
		
		if(result == 1) {
			return prodVO.getProdNo(); 
		}
		else 
			return -1;
	}

//	수정
	@Override
	public Map<String, Object> updateProd(ProdVO prodVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		boolean isSuccess = false;
		
		int result = prodMapper.updateProd(prodVO);
		if(result == 1) {
			isSuccess = true;
		}
		map.put("result", isSuccess);
		map.put("target", prodVO);
		return map;
	}

//	삭제
	@Override
	public Map<String, Object> deleteProd(int prodNo) {	
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isSuccess = false;
		
		int result = prodMapper.deleteProd(prodNo);
		if(result == 1) {
			isSuccess = true;
		}
		map.put("result", isSuccess);
		map.put("target", prodNo);
		return map;		
	}

//	조회수
	@Override
	public void updateProdHit(int prodNo) {
		prodMapper.updateProdHit(prodNo);
	}

	// 리뷰 등록 가능 확인
	@Override
	public Integer confirmInsertReview(ProdVO prodVO) {
		return prodMapper.confirmInsertReview(prodVO);
	}

	// 리뷰 등록
	@Override
	@Transactional
	public int insertProdReview(ProdVO prodVO, List<AttachVO> files) {
		int result = prodMapper.insertProdReview(prodVO);

		int reviewNo = prodVO.getProdReviewNo();

		int index = 1;
		if(files != null && files.size() > 0) {
			for (AttachVO file : files) {
				file.setAttachTableDiv("pr");
				file.setAttachTablePk(reviewNo);
				file.setAttachTurn(index);
				index++;
			}
			attachMapper.saveAttachTurn(files);
		}

		if (result == 1) {
			return reviewNo;
		} else {
			return -1;
		}
	}

	// 상품별 리뷰 조회
	@Override
	public List<ProdVO> selectProdReview(int prodNo) {
		return prodMapper.selectProdReview(prodNo);
	}

	// 상품 리뷰 삭제
	@Override
	public boolean deleteProdReview(int prodReviewNo) {
		int result = prodMapper.deleteProdReview(prodReviewNo);

		if (result == 1) {
			prodMapper.deleteReviewImg(prodReviewNo);
			return true;
		} else {
			return false;
		}
	}
	
	// 별점
	@Override
	public ProdVO prodStar(int prodNo) {
		return prodMapper.prodStar(prodNo);
	}

}
