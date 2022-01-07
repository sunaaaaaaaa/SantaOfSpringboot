package com.kh.spring.mountain;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.kh.spring.board.Board;
import com.kh.spring.common.util.file.FileInfo;
import com.kh.spring.common.util.file.FileUtil;
import com.kh.spring.common.util.pagination.Paging;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MountainService {


	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final MountainRepository mountainRepository;
	
	@Transactional
	public String getData(){
		 List<Mountain> updateList = new ArrayList<Mountain>();
		try {
			String urlEncoding = "http://apis.data.go.kr/1400000/service/cultureInfoService/mntInfoOpenAPI?ServiceKey=F7m4klGXJhBQMADAXunThEloyiChhHHy5UZ6AzeaIUNBboB8kd3aI5T%2BGQP931YifdDfQC3kjd5XML%2B2wLbNog%3D%3D&_type=json&numOfRows=3200";

			URI uri = new URI(urlEncoding);
			RestTemplate restTemplate = new RestTemplate();
			
			String response = restTemplate.getForEntity(uri, String.class).getBody();

			 //하나씩 까줌
			 JSONParser parser = new JSONParser(); 
			 JSONObject jsonObject = (JSONObject) parser.parse(response);
			 JSONObject docuArray = (JSONObject) jsonObject.get("response");
			 JSONObject docuArray2 =(JSONObject) docuArray.get("body");
			 JSONObject docuArray3 =(JSONObject) docuArray2.get("items");
			 JSONArray docuArray4 =(JSONArray) docuArray3.get("item");
			 
			 for (int i = 0; i < docuArray4.size(); i++) {
			 
				 Mountain update = new Mountain();
				 
				 JSONObject docuArray5 = (JSONObject) docuArray4.get(i); 
				 
				 update.setMntilistno((Long)docuArray5.get("mntilistno"));
				 update.setMntiname((String)docuArray5.get("mntiname"));
				 update.setMntiadd((String)docuArray5.get("mntiadd"));
				 update.setMntihigh(String.valueOf(docuArray5.get("mntihigh")));
				 update.setMntiadmin((String)docuArray5.get("mntiadmin"));
				 update.setMntiadminnum((String)docuArray5.get("mntiadminnum"));
				 update.setMntidetails((String)docuArray5.get("mntidetails"));
				 // mntilistno; 산코드
				 // mntiname; 산이름
				 // mntiadd; 산위치
				 // mntihigh; 산높이
				 // mntiadmin; 산관리주체
				 // mntiadminnum; 산관리자전화번호
				 // mntidetails; 산정보
				 updateList.add(update);
			 }

			 //이미지가져오기
			String imgURi = "http://apis.data.go.kr/1400000/service/cultureInfoService/mntInfoImgOpenAPI?ServiceKey=F7m4klGXJhBQMADAXunThEloyiChhHHy5UZ6AzeaIUNBboB8kd3aI5T%2BGQP931YifdDfQC3kjd5XML%2B2wLbNog%3D%3D&_type=json&mntiListNo=";

			 for (int i = 0; i < updateList.size(); i++) {
				 mountainRepository.save(updateList.get(i));
				 
				imgURi += updateList.get(i).getMntilistno();
				URI imgOfURI = new URI(imgURi);
				RestTemplate restTemplate2 = new RestTemplate();
				
				String response2 = restTemplate2.getForEntity(imgOfURI, String.class).getBody();

				 //하나씩 까줌
				 JSONParser parserOfImg = new JSONParser(); 
				 JSONObject jsonObjectOfImg = (JSONObject) parserOfImg.parse(response2);
				 JSONObject docuArrayOfImg = (JSONObject) jsonObjectOfImg.get("response");
				 JSONObject docuArrayOfImg2 =(JSONObject) docuArrayOfImg.get("body");
				 JSONObject docuArrayOfImg3 =(JSONObject) docuArrayOfImg2.get("items");
				 JSONArray docuArrayOfImg4 =(JSONArray) docuArrayOfImg3.get("item");
				 
				 for (int j = 0; j < docuArrayOfImg4.size(); j++) {
					 
					 FileInfo updateOfImg = new FileInfo();
					 
					 JSONObject docuArrayOfImg5 = (JSONObject) docuArrayOfImg4.get(j); 
					// updateOfImg.setTypeIdx(updateList.get(i).getMntilistno());
				 }
			 }
			 
		} catch (URISyntaxException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return updateList.toString();
		
	}

	public Map<String, Object> findMountainsByPage(int pageNumber) {

		int cntPerPage = 5;
		Page<Mountain> page = mountainRepository.findAll(PageRequest.of(pageNumber-1, cntPerPage, Direction.DESC, "mntilistno"));
		List<Mountain> mountain = page.getContent();
		Paging pageUtil = Paging.builder()
					.url("/mountain/mountain-list")
					.total((int)mountainRepository.count())
					.cntPerPage(cntPerPage)
					.blockCnt(10)
					.curPage(pageNumber)
					.build();
		
		return Map.of("mountainList", mountain, "paging", pageUtil);
	}
	
}
