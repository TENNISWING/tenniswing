package com.tenniswing.project.court.mapper;

import java.util.List;
import java.util.Map;

import com.tenniswing.project.court.service.CrtReserveVO;
import com.tenniswing.project.court.service.CrtroomVO;
import com.tenniswing.project.court.service.HostDashBoardVO;

public interface CrtReserveMapper {
	// 예약등록
	public int insertCrtReserve(CrtReserveVO crtReserveVO);
	
	// 예약불가능 리스트 가져오기
	public List<CrtReserveVO> impossibleReserveList(CrtReserveVO crtReserveVO);
	
	// 예약시간 구분코드 가져오기
	public List<CrtReserveVO> reserveTimeCodeList();
	

	  //마이페이지 예약 리스트
	  public List<Map<String, Object>> selectMyCourtReverse(String id);
  
	  // 호스트
		// 호스트별 예약 리스트 조회
		public List<CrtReserveVO> selectAllCrtReserve(String hostId);
		
		// 코트별 예약 리스트
		public List<CrtReserveVO> selectAllCrtDeReserve(CrtReserveVO crtReserveVO);
		
		// 호스트별 환불 리스트 조회
		public List<CrtReserveVO> selectAllCrtRefund(String hostId);
		
		// 환불 등록
		public int insertCrtRefund(CrtReserveVO crtReserveVO);
		
		// 호스트별 정산 리스트 조회
		public List<CrtReserveVO> selectAllCrtCalc(String hostId);
		
		// 환불 상태 변경
		public int updateCrtReserveState(CrtReserveVO crtReserveVO);
		
		// 예약 시간 지나면 자동 상태변경
		public int updateState(CrtReserveVO crtReserveVO);
		
		// 대시보드 예약금액, 예약건수
		public HostDashBoardVO hostMonthReserve();
		
		// 대시보드 환불금액, 환불건수
		public HostDashBoardVO hostMonthRefund();
		
		// 오늘의 예약
		public List<CrtReserveVO> todayReserve();
		
		// 올해 월별 예약금액
		public HostDashBoardVO thisYearMonthPrice();
		
		// 최근 리뷰 5개
		public List<CrtroomVO> recentReview(String hostId);
		
		// 호스트 간단정보
		public HostDashBoardVO hostInfo(String hostId);
		
		// 별점 통계
		public HostDashBoardVO hostStarInfo(String hostId);
}
