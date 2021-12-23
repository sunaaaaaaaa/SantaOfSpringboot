package com.kh.spring.mountain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Mountain {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	//3000건 데이터 가져오기
	String url = "http://apis.data.go.kr/1400000/service/cultureInfoService/mntInfoOpenAPI?ServiceKey=F7m4klGXJhBQMADAXunThEloyiChhHHy5UZ6AzeaIUNBboB8kd3aI5T%2BGQP931YifdDfQC3kjd5XML%2B2wLbNog%3D%3D&_type=json&numOfRows=3000";
	public void getData(){
		

		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		
		logger.debug(" Batch API 응답코드 : "+response.getStatusCode());
			
	}
	
}
