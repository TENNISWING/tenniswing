package com.tenniswing.project.court.service;


import org.apache.tomcat.util.json.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

@Service
public class Api {
	private static final String REQUEST_TOKEN = "https://api.iamport.kr/users/getToken";
	
	private final RestTemplate restTemplate;
	
	public Api(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public void apiCall(RefundAppVO vo) {
		HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.APPLICATION_JSON);
		 
		 //토큰요청객체 생성
		 TokenVO token = new TokenVO();
		 
		 //객체 -> json
		 Gson gson = new Gson();
		 String tokenReq = gson.toJson(token);
		 
		 HttpEntity<String> requestEntity = new HttpEntity<>(tokenReq, headers);
		 
		 ResponseEntity<String> responseEntity = restTemplate.postForEntity(REQUEST_TOKEN, requestEntity, String.class);
		 
		 String responseBody = responseEntity.getBody();
		 
		 System.out.println(responseBody);
	}
}
