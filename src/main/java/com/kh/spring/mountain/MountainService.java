package com.kh.spring.mountain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MountainService {


	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public String getData(){
		//3000건 데이터 가져오기
		String urlDecoding = "http://apis.data.go.kr/1400000/service/cultureInfoService/mntInfoOpenAPI?ServiceKey=F7m4klGXJhBQMADAXunThEloyiChhHHy5UZ6AzeaIUNBboB8kd3aI5T+GQP931YifdDfQC3kjd5XML+2wLbNog==&_type=json&numOfRows=3000";
		String urlEncoding ="http://apis.data.go.kr/1400000/service/cultureInfoService/mntInfoOpenAPI?ServiceKey=F7m4klGXJhBQMADAXunThEloyiChhHHy5UZ6AzeaIUNBboB8kd3aI5T%2BGQP931YifdDfQC3kjd5XML%2B2wLbNog%3D%3D&_type=json&numOfRows=3000";
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> response = restTemplate.getForEntity(urlDecoding, String.class);
		
		String resultMountain = response.getBody();
		
		//서비스키 30에러..
		//인코딩 디코딩 키가 먹히질 않음.
		//인코딩키는 주소창url으로 받아지긴하지만 스프링 통해서는 아예안됨.
		
		return resultMountain;
		
	}
	
}
