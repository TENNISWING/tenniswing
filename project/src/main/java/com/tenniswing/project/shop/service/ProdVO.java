package com.tenniswing.project.shop.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.tenniswing.project.attach.service.AttachVO;

import lombok.Data;

@Data
public class ProdVO {
	// 상품 번호
	private int prodNo;
	// 상품 이름
	private String prodName;
	// 상품 브랜드
	private String prodBrand;
	// 상품 설명
	private String prodExp;
	// 상품 가격
	private int prodPrice;
	// 상품 총 재고
	private int prodTSto;
	// 상품 판매 유무
	private String prodSaleSts;
	// 상품 등록일
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date prodRegiDate;
	// 상품 수정일
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date prodEditDate;
	// 상품 카테고리
	private String cateProd;
	// 상품 성별
	private String cateGen;
	
	private String attachPath;
	
	//attach
	private List<MultipartFile> files = new ArrayList<>();
	
	//이미지 리스트
	private List<AttachVO> attachList;
}
