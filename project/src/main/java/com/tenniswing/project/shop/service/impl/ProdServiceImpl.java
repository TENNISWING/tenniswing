package com.tenniswing.project.shop.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenniswing.project.attach.mapper.AttachMapper;
import com.tenniswing.project.attach.service.AttachService;
import com.tenniswing.project.attach.service.AttachVO;
import com.tenniswing.project.common.FileUtils;
import com.tenniswing.project.shop.mapper.ProdMapper;
import com.tenniswing.project.shop.service.ProdService;
import com.tenniswing.project.shop.service.ProdVO;

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
	
//	페이징 전체 갯수 조회
	public int selectCount(ProdVO prodVO) {
		return prodMapper.selectCount(prodVO);
	}
	
//	최근 상품 스와이퍼
	@Override
	public List<ProdVO> selectSwiperProd() {
		return prodMapper.selectSwiperProd();
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
}
