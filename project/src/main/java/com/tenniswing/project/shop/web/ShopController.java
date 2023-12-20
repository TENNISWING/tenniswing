package com.tenniswing.project.shop.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenniswing.project.attach.service.AttachService;
import com.tenniswing.project.attach.service.AttachVO;
import com.tenniswing.project.common.FileUtils;
import com.tenniswing.project.court.service.CrtroomVO;
import com.tenniswing.project.member.service.MemberService;
import com.tenniswing.project.member.service.MemberVO;
import com.tenniswing.project.shop.service.CartService;
import com.tenniswing.project.shop.service.CartVO;
import com.tenniswing.project.shop.service.OrderDetailVO;
import com.tenniswing.project.shop.service.OrderService;
import com.tenniswing.project.shop.service.OrderTableVO;
import com.tenniswing.project.shop.service.ProdDetailService;
import com.tenniswing.project.shop.service.ProdDetailVO;
import com.tenniswing.project.shop.service.ProdService;
import com.tenniswing.project.shop.service.ProdVO;
import com.tenniswing.project.shop.service.WishService;
import com.tenniswing.project.shop.service.WishVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ShopController {

	@Autowired
	ProdService prodService;
	
	@Autowired
	ProdDetailService prodDetailService;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	FileUtils fileUtils;
	
	@Autowired
	AttachService attachService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	WishService wishService;
	
	// 상품 목록
	@GetMapping("shop")
	public String shopPage(Model model) {
//		model.addAttribute("prodList", prodService.selectAllProd());
		model.addAttribute("popularProd", prodService.selectSwiperProd());
		return "shop/shop";
	}
	
	@GetMapping("shopList")
	@ResponseBody
	public Map<String, Object> prodPage(ProdVO prodVO) {
		/*
		 * System.out.println("!!!! before"+prodVO); List<ProdVO> list =
		 * prodService.selectAllProd(prodVO); System.out.println("!!!! after "+list);
		 */
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("selectCount", prodService.selectCount(prodVO));
		map.put("shopList", prodService.selectAllProd(prodVO));
		return map;
	}

	// 한건 조회
	@GetMapping("shopDetail")
	public String shopDetailPage(Model model, ProdVO prodVO) {
		ProdVO tempVO = new ProdVO();
		
		// 첨부파일 불러오기
		List<AttachVO> attachList = attachService.attachList("p", prodVO.getProdNo());
		String path = "";

		if (attachList != null && attachList.size() != 0) {
			model.addAttribute("attachList", attachList);
			path = attachList.get(0).getAttachPath();
			model.addAttribute("image", path);
		}
		
		
		tempVO = prodService.selectProd(prodVO);
		log.warn("=============="+tempVO);
		model.addAttribute("prod", tempVO);
		model.addAttribute("relatedProd", prodService.relatedSwiperProd(tempVO));
		log.warn("=============="+prodService.relatedSwiperProd(prodVO));
		model.addAttribute("prodDetailList", prodDetailService.selectAllProdDetail(prodVO));
		model.addAttribute("prodDetail", new ProdDetailVO());
		model.addAttribute("prodReview", prodService.selectProdReview(prodVO.getProdNo()));
		model.addAttribute("prodStar", prodService.prodStar(prodVO.getProdNo()));
		return "shop/shopDetail";
	}
	
//	위시리스트 리스트 출력
	/*
	 * @GetMapping("mypage-shop") public String selectAllWish(Model model) { String
	 * memId = SecurityContextHolder.getContext().getAuthentication().getName();
	 * model.addAttribute("wishList", wishService.selectAllWish(memId)); return
	 * "member/mypage-shop"; }
	 */
	
//	위시리스트 한건조회
	@PostMapping("selectWish")
	@ResponseBody
	public boolean selectWish(@RequestBody WishVO wishVO){
		return (wishService.selectOneWish(wishVO));
	}
	
//	위시 등록
	@PostMapping("insertWish")
	@ResponseBody
	public boolean insertWish(@RequestBody WishVO wishVO){
		log.warn("======위시추가====="+wishVO);
		return wishService.insertWish(wishVO);
	}
	
//	위시 삭제
	@PostMapping("deleteWish")
	@ResponseBody
	public boolean deleteWish(@RequestBody WishVO wishVO){
		log.warn("======위시삭제====="+wishVO);
		return wishService.deleteWish(wishVO);
	}
	
	
//	장바구니 리스트 출력
	@GetMapping("selectCartList")
	@ResponseBody
	public List<CartVO> selectAllCart(@RequestParam(value = "cartMemId") String memId) {
		return cartService.selectAllCart(memId);
	}
	
//	장바구니 한건조회
	@PostMapping("selectCart")
	@ResponseBody
	public boolean selectCart(@RequestBody CartVO cartvo){
		return cartService.selectOneCart(cartvo);
	}
	
//	장바구니 추가 처리
	@PostMapping("insertCart")
	@ResponseBody
	public Map<String, Object> insertCart(@RequestBody ProdDetailVO prodDetailVO){
		log.warn("======추가====="+prodDetailVO);
		return cartService.insertCart(prodDetailVO);
	}
	
//	장바구니 수정 처리
	@PostMapping("updateCart")
	@ResponseBody
	public boolean updateCart(@RequestBody CartVO cartvo) {
		return cartService.updateCart(cartvo);
	}
	
//	장바구니 삭제 처리
	@PostMapping("deleteCart")
	@ResponseBody
	public boolean deleteCart(@RequestBody CartVO cartvo) {
		return cartService.deleteCart(cartvo);
	}

	@GetMapping("checkout")
	public String checkoutPage(Model model, ProdDetailVO prodDetailVO, ProdVO prodVO, @RequestParam String type) {
		/*
		 * ProdVO prodVO = new ProdVO(); prodVO.setProdNo(prodDetailVO.getProdNo());
		 */
		//MemberVO memberVO
		//model.addAttribute("prodDetail", prodDetailVO);
		//log.warn("asd"+type);
		String memId = SecurityContextHolder.getContext().getAuthentication().getName();
		List<CartVO> cartList = new ArrayList<CartVO>();
		ProdDetailVO detailVO = new ProdDetailVO();
		detailVO = prodDetailService.selectCartProd(prodDetailVO);
		
		if(type.equals("direct")) {
			log.warn("=======prodVO==="+prodVO);
			log.warn("=======prodDetailVO==="+prodDetailVO);
			model.addAttribute("prod", prodService.selectProd(prodVO));
			model.addAttribute("prodDetailVO", prodDetailVO);
			model.addAttribute("prodDetailNo", detailVO.getProdDetailNo());
			log.warn("=======prodDetailNo==="+detailVO.getProdDetailNo());
			//model.addAttribute("cartList", cartList);
		} else {
			//log.warn("asd"+type);
			cartList = cartService.selectCheckCart(memId, type);
			log.warn("=======cartList==="+cartList);
			model.addAttribute("cartList", cartList);	
		}
		
		//model.addAttribute("prodDetailVO", prodDetailService.selectProdDetail(prodDetailVO));
		//model.addAttribute("mem", memberService.memberInfo(memberVO.getMemId()));
		return "shop/checkout";
	}
	
	@PostMapping("findMemId")
	@ResponseBody
	public MemberVO findMemId(@RequestBody MemberVO memberVO) {
		log.warn("=========memID==="+memberVO.getMemId());
		return memberService.memberInfo(memberVO.getMemId()); 
	}
	
	// 결제 요청
	@PostMapping("orderPay")
	@ResponseBody
	public Map<String, Object> orderPay(@RequestBody OrderTableVO orderTableVO) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<CartVO> cartList = new ArrayList<CartVO>();
		// 바로 결제 들어올 때
		if((orderTableVO.getType()).equals("direct")) {
			log.warn("===== 결제가 성공해서 들어오면===="+orderTableVO);
			result = orderService.insertOrder(orderTableVO);
		} else { // 장바구니에서 결제 들어올 때
			cartList = cartService.selectCheckCart(orderTableVO.getMemId(), orderTableVO.getType());
			log.warn("=======cartList==="+cartList);
			result = orderService.insertCartOrder(orderTableVO, cartList);
		}
		return result;
	}
	
	// 상품 구매한 사람인지 확인
	@PostMapping("confirmOrder")
	@ResponseBody
	public Integer confirmOrder(ProdVO prodVO) {
		String memId = SecurityContextHolder.getContext().getAuthentication().getName();
		if(memId.equals("anonymousUser")){
			return 0;
		}else {
			prodVO.setMemId(memId);
		}
		Integer orderNum = prodService.confirmInsertReview(prodVO);
		if(orderNum == null || orderNum == 0) {
			return 0;
		} else {
			return prodService.confirmInsertReview(prodVO);
		}
	}
	
	// 리뷰 등록하기
	@PostMapping("insertProdReview")
	@ResponseBody
	public Map<String, Object> insertProdReview(ProdVO prodVO, Model model) {
		Map<String, Object> map = new HashMap<>();
		String memId = SecurityContextHolder.getContext().getAuthentication().getName();
		prodVO.setMemId(memId);
		
		Integer orderTableNo = prodService.confirmInsertReview(prodVO);
		if(orderTableNo == null) {
			map.put("result", false);
			map.put("review", "이용 전이거나 예약건이 없습니다.");
			return map;
		}
		prodVO.setOrderTableNo(orderTableNo);
		//사진 등록
		//테이블 구분, 게시글 번호, 파일목록
		//int n = 0;
		boolean isSuccessed = false;
		
		List<AttachVO> files = fileUtils.uploadFiles(prodVO.getFiles());
		int reviewNo = prodService.insertProdReview(prodVO, files);
		
		if(reviewNo != -1) {
			isSuccessed = true;
		}
		
		//CrtroomVO review = prodService.selectReview(reviewNo);
		//map.put("reviews", review);
		map.put("result", isSuccessed);
		map.put("memId", memId);
		return map;
	}
	
	
	// 리뷰 삭제
	@PostMapping("deleteProdReview")
	@ResponseBody
	public boolean deleteReview(@RequestParam Integer prodReviewNo) {
		boolean result = prodService.deleteProdReview(prodReviewNo);
		
		return result;
	}
	
	
}
