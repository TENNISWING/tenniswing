package com.tenniswing.project.court.service;

import lombok.Data;

@Data
public class RefundAppVO {
	String imp_uid;
	String merchant_uid;
	int amount;
	String reason;
	
	public RefundAppVO(String reservePayNo, String reserveUid, String reason, int refundPrice) {
		this.imp_uid = reservePayNo;
		this.merchant_uid = reserveUid;
		this.amount = refundPrice;
		this.reason = reason;
		
	};
}
