package com.tenniswing.project.court.service;

import lombok.Data;

@Data
public class RefundAppVO {
	String impUid;
	String merchantUid;
	int amount;
	String refundReason;
	
	public RefundAppVO(String reservePayNo, String reserveUid, String reason, int refundPrice) {
		this.impUid = reservePayNo;
		this.merchantUid = reserveUid;
		this.amount = refundPrice;
		this.refundReason = reason;
		
	};
}
