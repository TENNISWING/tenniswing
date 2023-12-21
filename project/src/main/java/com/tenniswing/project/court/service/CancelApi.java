package com.tenniswing.project.court.service;



import java.util.HashMap;
import java.util.Map;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

@Service
public class CancelApi {
	private static final String REQUEST_CANCEL = "https://api.iamport.kr/payments/cancel";	
	
	private final RestTemplate restTemplate;
	
	public CancelApi(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public Map<String, Object> CancelApiCall(RefundAppVO refundAppVO, String token) {
		 HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.APPLICATION_JSON);
		 headers.add("Authorization", "Bearer "+token);
		 
		 //토큰요청객체 생성
		 
		 //객체 -> json
		 Gson gson = new Gson();
		 String cancelReq = gson.toJson(refundAppVO);
		 
		 //헤더랑 바디 넣어서 요청entity만듬
		 HttpEntity<String> requestEntity = new HttpEntity<>(cancelReq, headers);
		 
		 //resttemplate이용해서 요청보내서 응답 받아옴
		 ResponseEntity<String> responseEntity = restTemplate.postForEntity(REQUEST_CANCEL, requestEntity, String.class);
		 
		 //응답의 body 빼옴
		 String responseBody = responseEntity.getBody();
		 
		 //json을 객체로 바꿈
		 JSONParser parser = new JSONParser();
		 Object obj = null;
		 try {
			obj = parser.parse(responseBody);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 Map<String, Object> map = new HashMap<>();
		 
		 
		 map.put("cancel", obj);
		 
		 return map;
	}
}
