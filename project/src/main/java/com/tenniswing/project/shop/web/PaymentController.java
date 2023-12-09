package com.tenniswing.project.shop.web;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;

import com.siot.IamportRestClient.IamportClient;

public class PaymentController {
	private IamportClient iamportClient;
	
	@Value("${imp.api.key}")
	private String apikey;
	
	@Value("${imp.api.secretkey}")
	private String secretkey;
	
	@PostConstruct
	public void init() {
		this.iamportClient = new IamportClient(apikey, secretkey);
	}
	
}
