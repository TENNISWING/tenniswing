package com.tenniswing.project.court.service;



import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
public class Api {
	private static final String REQUEST_TOKEN = "https://api.iamport.kr/users/getToken";
	private static final String REQUEST_CANCEL = "https://api.iamport.kr/payments/cancel";
	
	private final RestTemplate restTemplate;
	
	public Api(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public String apiCall() {
		HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.APPLICATION_JSON);

		 //토큰요청객체 생성
		 TokenVO token = new TokenVO();
		 
		 //객체 -> json
		 Gson gson = new Gson();
		 String tokenReq = gson.toJson(token);
		 
		 //헤더랑 바디 넣어서 요청entity만듬
		 HttpEntity<String> requestEntity = new HttpEntity<>(tokenReq, headers);
		 
		 //resttemplate이용해서 요청보내서 응답 받아옴
		 ResponseEntity<String> responseEntity = restTemplate.postForEntity(REQUEST_TOKEN, requestEntity, String.class);
		 
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
		 
		 //객체를 json객체로 바꿈
		 JSONObject jsonObj = (JSONObject)obj;
		 
		 //response값만 빼옴
		 JSONObject jsonObj2 = (JSONObject)jsonObj.get("response");
		 
		 //access_token값만 빼옴
//		 System.out.println((String)jsonObj2.get("access_token"));
		 
		 String tokenStr = (String)jsonObj2.get("access_token");
		 
		 //1. 헤더에 토큰값 추가해주기
		 //2. CancelRequestVO 만들어주기
		 //3. 생성자에 파라미터 4개 다 받기 (화면에서 넘어온 값)
		 //4. 저 요청 entity 다시 만들기
		 //5. 반복
		 
		 return tokenStr;
	}
}
