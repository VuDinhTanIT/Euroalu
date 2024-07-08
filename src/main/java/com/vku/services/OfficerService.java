package com.vku.services;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vku.Utils.Utils;
import com.vku.models.Officer;

@Service
public class OfficerService {

	private String apiURL;
//	private String apiURL = Utils.LOCAL_STRING+"/admin/api/Officer";
	private RestTemplate restTemplate = new RestTemplate();
//	private AppConfig appConfig;
	private HttpHeaders headers = new HttpHeaders();

//	@Autowired
//	public OfficerService(AppConfig appConfig) {
//		this.appConfig = appConfig;
//	}
	public OfficerService() {
		this.apiURL = Utils.BASE_URL + "/admin/officers";
		this.restTemplate = new RestTemplate();
		this.headers = new HttpHeaders();
		this.headers.setContentType(MediaType.APPLICATION_JSON);
	}

	private List<Officer> listOfficers;

	public List<Officer> getAllOfficers() throws Exception {
		RequestEntity<?> requestEntity = new RequestEntity<>("", HttpMethod.GET, URI.create(apiURL));
		ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
		String json = response.getBody();
		ObjectMapper objectMapper = new ObjectMapper();
		listOfficers = objectMapper.readValue(json, new TypeReference<List<Officer>>() {
		});

		return listOfficers;
	}

	public ResponseEntity<Officer> post(Officer Officer) {
		String api = apiURL;
		headers.setContentType(MediaType.APPLICATION_JSON);
		RequestEntity<?> requestEntity = new RequestEntity<>(Officer, HttpMethod.POST, URI.create(api));
		ResponseEntity<Officer> response = restTemplate.exchange(requestEntity, Officer.class);
//		System.out.println("repose officer: "+ response);
		return response;
	}

	public ResponseEntity<String> Update(Officer Officer, int id) {
		String api = apiURL + "/" + id;

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Officer> requestEntity = new HttpEntity<>(Officer);
		ResponseEntity<String> responseEntity = restTemplate.exchange(api, HttpMethod.PUT, requestEntity, String.class);
		return responseEntity;
	}

	public ResponseEntity<?> createAttendanceQR(String courseId, String lessonContent) {
	    String api = apiURL;
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    
	    // Tạo body request từ courseId và lessonContent
	    Map<String, String> requestBody = new HashMap<>();
	    requestBody.put("courseId", courseId);
	    requestBody.put("lessonContent", lessonContent);
	    
	    // Gửi yêu cầu POST với body request là requestBody
	    RequestEntity<Map<String, String>> requestEntity = new RequestEntity<>(requestBody, headers, HttpMethod.POST, URI.create(api));
	    
	    ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
	    return response;
	}

	public Officer getOfficerById(int id) {
		String api = apiURL + "/" + id;

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<Officer> responseEntity = restTemplate.exchange(api, HttpMethod.GET, requestEntity,
				Officer.class);
		return responseEntity.getBody();
	}
}
